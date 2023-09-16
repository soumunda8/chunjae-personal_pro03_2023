package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.service.MemberService;
import kr.co.teaspoon.service.NoticeService;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "memberList.do", method = RequestMethod.GET)
    public String getMemberList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        if(sid != null && sid.equals("admin")) {

            Page page = new Page();
            page.setSearchType(type);
            page.setSearchKeyword(keyword);
            int total = memberService.memberAllListCount(page);

            page.makeBlock(curPage, total);
            page.makeLastPageNum(total);
            page.makePostStart(curPage, total);

            List<Member> memberList = memberService.memberAllList(page);

            model.addAttribute("adminNum", 0);
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("curPage", curPage);
            model.addAttribute("memberList", memberList);
            return "/admin/memberList";
        } else {
            return "redirect:/";
        }
    }

}