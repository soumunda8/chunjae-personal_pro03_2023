package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.service.BoardServiceImpl;
import kr.co.teaspoon.util.BoardPage;
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
@RequestMapping("/fileBoard/**")
public class FileBoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    HttpSession session;

    private final String boardNm = "fileBoard";

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String fileBoardList(HttpServletRequest request, Model model) throws Exception {

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

        List<BoardVo> fileBoardList = boardService.boardList(page);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);
        model.addAttribute("fileBoardList", fileBoardList);

        return "/fileBoard/fileBoardList";
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public String fileBoardDetail(@RequestParam int no, Model model) throws Exception {

        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        if(no != 0) {
            BoardVo fileBoard = boardService.boardDetail(no);
            if(!sid.equals("admin")) {
                boardService.boardUpdateVisited(no);
                fileBoard.setVisited(fileBoard.getVisited() + 1);
            }
            model.addAttribute("fileBoard", fileBoard);
            return "/fileBoard/fileBoardDetail";
        } else {
            return "redirect:/fileBoard/list.do";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String fileBoardInsert(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Board fileBoard = new Board();
            fileBoard.setTitle(request.getParameter("title"));
            fileBoard.setContent(request.getParameter("content"));
            fileBoard.setBoardNm(boardNm);
            fileBoard.setAuthor(sid);
            boardService.boardInsert(fileBoard);

            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String fileBoardUpload(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Board fileBoard = new Board();
            fileBoard.setTitle(request.getParameter("title"));
            fileBoard.setContent(request.getParameter("content"));
            fileBoard.setSeq(Integer.parseInt(request.getParameter("no")));
            boardService.boardUpdate(fileBoard);

            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String fileBoardDelete(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(no != 0 && sid != null && sid.equals("admin")) {
            boardService.boardDelete(no);
            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }


}