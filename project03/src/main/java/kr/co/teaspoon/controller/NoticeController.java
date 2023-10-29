package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.service.BoardService;
import kr.co.teaspoon.service.BoardServiceImpl;
import kr.co.teaspoon.util.BoardPage;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

    @Autowired
    private BoardService boardService;

    @Autowired
    HttpSession session;

    private final String boardNm = "notice";

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String noticeList(HttpServletRequest request, Model model) throws Exception {

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        BoardPage page = new BoardPage();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        page.setBoardNm(boardNm);
        int total = boardService.boardListCount(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<BoardVo> noticeList = boardService.boardList(page);

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
            BoardVo notice = boardService.boardDetail(no);
            if(!sid.equals("admin")) {
                boardService.boardUpdateVisited(no);
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
            Board notice = new Board();
            notice.setTitle(request.getParameter("title"));
            notice.setContent(request.getParameter("content"));
            notice.setBoardNm(boardNm);
            notice.setAuthor(sid);
            boardService.boardInsert(notice);

            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String noticeUpload(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Board notice = new Board();
            notice.setTitle(request.getParameter("title"));
            notice.setContent(request.getParameter("content"));
            notice.setSeq(Integer.parseInt(request.getParameter("no")));
            boardService.boardUpdate(notice);

            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String noticeDelete(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(no != 0 && sid != null && sid.equals("admin")) {
            boardService.boardDelete(no);
            return "redirect:/admin/noticeList.do";
        } else {
            return "redirect:/";
        }

    }


}