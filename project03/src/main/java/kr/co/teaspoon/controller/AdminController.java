package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.*;
import kr.co.teaspoon.service.*;
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
@RequestMapping("/admin/*")
public class AdminController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileBoardService fileBoardService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private QnaService qnaService;

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

    @RequestMapping(value = "noticeList.do", method = RequestMethod.GET)
    public String getNoticeList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        if(sid != null && sid.equals("admin")) {
            BoardPage page = new BoardPage();
            page.setSearchType(type);
            page.setSearchKeyword(keyword);
            page.setBoardNm("notice");
            int total = boardService.boardListCount(page);

            page.makeBlock(curPage, total);
            page.makeLastPageNum(total);
            page.makePostStart(curPage, total);

            List<BoardVo> noticeList = boardService.boardList(page);

            model.addAttribute("adminNum", 1);
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("curPage", curPage);
            model.addAttribute("noticeList", noticeList);

            return "/admin/noticeList";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "getNotice.do", method = RequestMethod.GET)
    public String getNoticeDetail(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 1);
            if(no != 0) {
                BoardVo notice = boardService.boardDetail(no);
                model.addAttribute("notice", notice);
                return "/admin/noticeDetail";
            } else {
                return "redirect:/admin/noticeList.do";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "noticeAdd.do", method = RequestMethod.GET)
    public String noticeInsert(Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 1);
            return "/admin/noticeInsert";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "noticeEdit.do", method = RequestMethod.GET)
    public String noticeUpdate(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            BoardVo notice = boardService.boardDetail(no);
            model.addAttribute("notice", notice);
            model.addAttribute("adminNum", 1);
            return "/admin/noticeUpdate";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "fileBoardList.do", method = RequestMethod.GET)
    public String getFileBoardList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        if(sid != null && sid.equals("admin")) {
            BoardPage page = new BoardPage();
            page.setSearchType(type);
            page.setSearchKeyword(keyword);
            page.setBoardNm("fileBoard");
            int total = boardService.boardListCount(page);

            page.makeBlock(curPage, total);
            page.makeLastPageNum(total);
            page.makePostStart(curPage, total);

            List<BoardVo> fileBoardList = boardService.boardList(page);

            model.addAttribute("adminNum", 2);
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("curPage", curPage);
            model.addAttribute("fileBoardList", fileBoardList);

            return "/admin/fileBoardList";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "getFileBoard.do", method = RequestMethod.GET)
    public String getFileBoardDetail(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 2);
            if(no != 0) {
                BoardVo fileBoard = boardService.boardDetail(no);
                model.addAttribute("fileBoard", fileBoard);

                FileBoard fileDTO = new FileBoard();
                fileDTO.setSeq(no);
                fileDTO.setBoardNm("fileBoard");
                List<FileBoard> fileList = fileBoardService.fileListByPar(fileDTO);
                if(fileList != null) {
                    model.addAttribute("fileList", fileList);
                }
                return "/admin/fileBoardDetail";
            } else {
                return "redirect:/admin/fileBoardList.do";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "fileBoardAdd.do", method = RequestMethod.GET)
    public String FileBoardInsert(Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 2);
            return "/admin/fileBoardInsert";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "fileBoardEdit.do", method = RequestMethod.GET)
    public String FileBoardUpdate(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            BoardVo fileBoard = boardService.boardDetail(no);
            model.addAttribute("fileBoard", fileBoard);

            FileBoard fileDTO = new FileBoard();
            fileDTO.setSeq(no);
            fileDTO.setBoardNm("fileBoard");
            List<FileBoard> fileList = fileBoardService.fileListByPar(fileDTO);
            if(fileList != null) {
                model.addAttribute("fileList", fileList);
            }

            model.addAttribute("adminNum", 2);
            return "/admin/fileBoardUpdate";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "qnaList.do", method = RequestMethod.GET)
    public String getQnaList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {

            List<QnaVo> qnaList = qnaService.qnaAllList();

            model.addAttribute("adminNum", 3);
            model.addAttribute("qnaList", qnaList);

            return "/admin/qnaList";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "getQna.do", method = RequestMethod.GET)
    public String getQnaDetail(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 3);
            if(no != 0) {
                QnaVo qna = qnaService.getQna(no);
                model.addAttribute("qna", qna);
                return "/admin/qnaDetail";
            } else {
                return "redirect:/admin/qnaList.do";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "addAnswer.do", method = RequestMethod.GET)
    public String getQnaAddAnswer(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 3);
            if(no != 0) {
                QnaVo qna = qnaService.getQna(no);
                model.addAttribute("qna", qna);
                return "/admin/qnaAnswerUpdate";
            } else {
                return "redirect:/admin/getQna.do?no="+no;
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "addAnswer.do", method = RequestMethod.POST)
    public String getQnaAddAnswerPro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int qno = request.getParameter("no") != null ? Integer.parseInt(request.getParameter("no")) : 0;

        if(qno != 0 && sid != null && sid.equals("admin")) {
            Qna qna = new Qna();
            qna.setQno(qno);
            qna.setAnswer(request.getParameter("answer"));
            qnaService.answerUpdate(qna);
            return "redirect:/admin/getQna.do?no="+qno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "voteList.do", method = RequestMethod.GET)
    public String getVoteList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        if(sid != null && sid.equals("admin")) {
            Page page = new Page();
            page.setSearchType(type);
            page.setSearchKeyword(keyword);
            int total = voteService.totalCountForAdmin(page);

            page.makeBlock(curPage, total);
            page.makeLastPageNum(total);
            page.makePostStart(curPage, total);

            List<Vote> voteList = voteService.voteAllListForAdmin(page);

            model.addAttribute("adminNum", 4);
            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("curPage", curPage);
            model.addAttribute("voteList", voteList);

            return "/admin/voteList";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "getVote.do", method = RequestMethod.GET)
    public String getVoteDetail(@RequestParam int vno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 4);
            if(vno != 0) {
                Vote vote = voteService.voteDetail(vno);
                model.addAttribute("vote", vote);

                if(!vote.isUseYn()) {
                    List<VoteList> voteAnswerList = voteService.voteAnswerList(vno);
                    model.addAttribute("voteAnswerList", voteAnswerList);
                } else {
                    int cnt = voteService.voteCountCnt(vno);
                    model.addAttribute("cnt", cnt);

                    VoteCountVo getMaxLno = voteService.voteMaxCountList(vno);
                    model.addAttribute("getMaxLno", getMaxLno);

                    List<VoteCountVo> voteCountList = voteService.voteCountList(vno);
                    model.addAttribute("voteCountList", voteCountList);
                }

                return "/admin/voteDetail";
            } else {
                return "redirect:/admin/voteList.do";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "voteAdd.do", method = RequestMethod.GET)
    public String voteInsert(Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            model.addAttribute("adminNum", 4);
            return "/admin/voteInsert";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "voteEdit.do", method = RequestMethod.GET)
    public String voteUpdate(@RequestParam int vno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Vote vote = voteService.voteDetail(vno);
            model.addAttribute("vote", vote);
            model.addAttribute("adminNum", 4);
            return "/admin/voteUpdate";
        } else {
            return "redirect:/";
        }

    }

}