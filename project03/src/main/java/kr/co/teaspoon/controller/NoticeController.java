package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Notice;
import kr.co.teaspoon.service.NoticeServiceImpl;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String noticeList(HttpServletRequest request, Model model) throws Exception {

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        Page page = new Page();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        int total = noticeService.noticeListCount(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<Notice> noticeList = noticeService.noticeList(page);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);
        model.addAttribute("noticeList", noticeList);

        return "/notice/noticeList";
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public String noticeDetail(@RequestParam int no, Model model) throws Exception {

        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        if(no != 0) {
            Notice notice = noticeService.noticeDetail(no);
            if(!sid.equals("admin")) {
                noticeService.noticeUpdateVisited(no);
                notice.setVisited(notice.getVisited() + 1);
            }
            model.addAttribute("notice", notice);
            return "/notice/noticeDetail";
        } else {
            return "redirect:/notice/list.do";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String noticeInsert(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Notice notice = new Notice();
            notice.setTitle(request.getParameter("title"));
            notice.setContent(request.getParameter("content"));
            noticeService.noticeInsert(notice);

            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String noticeUpload(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Notice notice = new Notice();
            notice.setTitle(request.getParameter("title"));
            notice.setContent(request.getParameter("content"));
            notice.setSeq(Integer.parseInt(request.getParameter("no")));
            noticeService.noticeUpdate(notice);

            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String noticeDelete(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(no != 0 && sid != null && sid.equals("admin")) {
            noticeService.noticeDelete(no);
            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }


}