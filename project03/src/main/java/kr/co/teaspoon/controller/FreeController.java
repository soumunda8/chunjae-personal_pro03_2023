package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.dto.Comment;
import kr.co.teaspoon.dto.CommentVo;
import kr.co.teaspoon.service.BoardService;
import kr.co.teaspoon.service.BoardServiceImpl;
import kr.co.teaspoon.service.CommentService;
import kr.co.teaspoon.util.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/freeBoard/**")
public class FreeController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    HttpSession session;

    private final String boardNm = "freeBoard";

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String freeBoardList(HttpServletRequest request, Model model) throws Exception {

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

        List<BoardVo> freeBoardList = boardService.boardList(page);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);
        model.addAttribute("freeBoardList", freeBoardList);

        return "/freeBoard/freeBoardList";
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public String freeBoardDetail(@RequestParam int no, Model model) throws Exception {

        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        if(no != 0) {
            BoardVo freeBoard = boardService.boardDetail(no);
            if(!sid.equals("admin")) {
                boardService.boardUpdateVisited(no);
                freeBoard.setVisited(freeBoard.getVisited() + 1);
            }
            model.addAttribute("freeBoard", freeBoard);

            CommentVo commentVo = new CommentVo();
            commentVo.setBoardNm(boardNm);
            commentVo.setBoardNo(no);
            List<CommentVo> commentList = commentService.commentList(commentVo);
            String originNm = "";
            for(CommentVo comment : commentList) {
                originNm = comment.getName();
                if (!originNm.equals("관리자")) {
                    String nm = originNm.substring(0, 1);
                    for (int i = 0; i < originNm.length() - 2; i++) {
                        nm += "*";
                    }
                    nm += originNm.substring(originNm.length() - 1);
                    comment.setName(nm);
                }
            }
            model.addAttribute("commentList", commentList);

            return "/freeBoard/freeBoardDetail";
        } else {
            return "redirect:/freeBoard/list.do";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    public String freeBoardInsert(Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {
            return "/freeBoard/freeBoardInsert";
        } else {
            return "redirect:/freeBoard/list.do";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String freeBoardInsertPro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {
            Board freeBoard = new Board();
            freeBoard.setTitle(request.getParameter("title"));
            freeBoard.setContent(request.getParameter("content"));
            freeBoard.setBoardNm(boardNm);
            freeBoard.setAuthor(sid);
            boardService.boardInsert(freeBoard);

            return "redirect:/freeBoard/list.do";
        } else {
            return "redirect:/freeBoard/list.do";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String freeBoardUpload(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        BoardVo freeBoard = boardService.boardDetail(no);

        if(sid != null && (sid.equals("admin") || sid.equals(freeBoard.getAuthor()))) {
            model.addAttribute("freeBoard", freeBoard);
            return "/freeBoard/freeBoardUpdate";
        } else {
            return "redirect:/freeBoard/detail.do?no=" + no;
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String freeBoardUploadPro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int seq = Integer.parseInt(request.getParameter("no"));

        BoardVo free = boardService.boardDetail(seq);

        if(sid != null && (sid.equals("admin") || sid.equals(free.getAuthor()))) {
            Board freeBoard = new Board();
            freeBoard.setTitle(request.getParameter("title"));
            freeBoard.setContent(request.getParameter("content"));
            freeBoard.setSeq(seq);
            boardService.boardUpdate(freeBoard);

        }

        return "redirect:/freeBoard/detail.do?no=" + seq;

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String freeBoardDelete(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        BoardVo freeBoard = boardService.boardDetail(no);

        if(no != 0 && sid != null && (sid.equals("admin") || sid.equals(freeBoard.getAuthor()))) {
            Comment comment = new Comment();
            comment.setSeq(no);
            comment.setBoardNm(boardNm);
            commentService.commentDeleteAll(comment);
            boardService.boardDelete(no);
            return "redirect:/freeBoard/list.do";
        } else {
            return "redirect:/freeBoard/detail.do?no=" + no;
        }

    }

    @RequestMapping(value = "commentAdd.do", method = RequestMethod.POST)
    @ResponseBody
    public CommentVo commentInsert(@RequestParam("par") int par, @RequestParam("content") String content, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";
        Comment comment = new Comment();
        comment.setSeq(par);
        comment.setBoardNm(boardNm);
        comment.setAuthor(sid);
        comment.setContent(content);
        CommentVo commentVO = commentService.commentInsert(comment);

        String originNm = commentVO.getName();
        if (!originNm.equals("관리자")) {
            String nm = originNm.substring(0, 1);
            for (int i = 0; i < originNm.length() - 2; i++) {
                nm += "*";
            }
            nm += originNm.substring(originNm.length() - 1);
            commentVO.setName(nm);
        }
        return commentVO;
    }

    @RequestMapping(value = "commentRemove.do", method = RequestMethod.POST)
    @ResponseBody
    public boolean commentDelete(@RequestParam("cno") int cno) throws Exception {
        boolean result = false;
        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";
        CommentVo commentVO = commentService.getComment(cno);
        if(commentVO.getAuthor().equals(sid) || sid.equals("admin")) {
            commentService.commentDelete(commentVO.getCno());
            result = true;
        }
        return result;
    }

}