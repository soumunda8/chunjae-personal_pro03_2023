package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.*;
import kr.co.teaspoon.service.MemberService;
import kr.co.teaspoon.service.VoteService;
import kr.co.teaspoon.util.Page;
import kr.co.teaspoon.dto.VoteCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/vote/*")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private MemberService memberService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String voteList(HttpServletRequest request, Model model) throws Exception {

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        Page page = new Page();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        int total = voteService.totalCount(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<Vote> voteList = voteService.voteAllList(page);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);
        model.addAttribute("voteList", voteList);

        return "/vote/voteList";
    }

    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    public String voteDetail(HttpServletRequest req, Model model) throws Exception{

        int vno = req.getParameter("vno") != null ? Integer.parseInt(req.getParameter("vno")) : 0;
        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        if(vno == 0) {
            return "redirect:/vote/list.do";
        } else {
            Vote vote = voteService.voteDetail(vno);
            model.addAttribute("vote", vote);

            if(!sid.equals("admin")) {
                voteService.voteVisitCount(vno);
            }

            int cnt = voteService.voteCountCnt(vno);
            model.addAttribute("cnt", cnt);

            VoteUser voteUser = new VoteUser();
            voteUser.setVno(vno);
            voteUser.setAuthor(sid);
            VoteUser voteUserInfo = voteService.voteUserList(voteUser);

            List<VoteCountVo> voteList = voteService.voteCountList(vno);
            model.addAttribute("voteList", voteList);

            VoteCountVo getMaxLno = voteService.voteMaxCountList(vno);
            model.addAttribute("getMaxLno", getMaxLno);

            if(!sid.equals("") && (sid.equals("admin") || voteUserInfo != null)) {
                model.addAttribute("voteYn", true);
                if(!sid.equals("admin")) {
                    model.addAttribute("voteUserInfo", voteUserInfo);
                }
                List<VoteCountVo> voteCountList = voteService.voteCountList(vno);
                model.addAttribute("voteCountList", voteCountList);

            } else {
                model.addAttribute("voteYn", false);
            }

            return "/vote/voteDetail";
        }

    }

    @RequestMapping(value = "addVote.do", method = RequestMethod.POST)
    @ResponseBody
    public String voteUserInsert(@RequestParam int vno, @RequestParam int lno, @RequestParam int pt, HttpServletRequest req, HttpServletResponse res) throws Exception{

        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";
        String result = "";

        VoteUser voteUser = new VoteUser();
        voteUser.setVno(vno);
        voteUser.setLno(lno);
        voteUser.setAuthor(sid);

        Member member = new Member();
        member.setId(sid);
        member.setPt(pt);

        if(!sid.equals("") && !sid.equals("admin")){

            memberService.memberUpdatePoint(member);
            voteService.voteUserInsert(voteUser);
            result = "success";
        } else {
            result = "error";
        }
        return result;

    }

    // ▽ 관리자 투표 관련
    
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String voteInsert(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Vote vote = new Vote();
            vote.setTitle(request.getParameter("title"));
            vote.setContent(request.getParameter("content"));
            vote.setStartDate(request.getParameter("startDate"));
            vote.setFinishDate(request.getParameter("finishDate"));
            voteService.voteInsert(vote);

            return "redirect:/admin/voteList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String voteUpdate(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Vote vote = new Vote();
            vote.setTitle(request.getParameter("title"));
            vote.setContent(request.getParameter("content"));
            vote.setStartDate(request.getParameter("startDate"));
            vote.setFinishDate(request.getParameter("finishDate"));
            vote.setVno(Integer.parseInt(request.getParameter("vno")));
            voteService.voteEdit(vote);

            return "redirect:/admin/voteList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "voteAnswerEdit.do", method = RequestMethod.GET)
    public String voteFinalUpdate(@RequestParam int vno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {

            VoteCountVo getMaxLno = voteService.voteMaxCountList(vno);

            VoteCountVo voteCount = new VoteCountVo();
            voteCount.setVno(vno);
            voteCount.setLno(getMaxLno.getLno());
            voteService.voteFinalInsert(voteCount);
            voteService.voteUpdateState(vno);

            return "redirect:/admin/getVote.do?vno=" + vno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "editUse.do", method = RequestMethod.GET)
    public String voteUseUpdate(@RequestParam int vno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Vote vote = new Vote();
            voteService.voteUpdateUse(vno);

            return "redirect:/admin/getVote.do?vno=" + vno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String voteDelete(@RequestParam int vno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(vno != 0 && sid != null && sid.equals("admin")) {
            voteService.voteDelete(vno);
            return "redirect:/admin/voteList.do";
        } else {
            return "redirect:/";
        }

    }

    // ▽ 관리자 투표 내역 관련

    @RequestMapping(value = "addAnswer.do", method = RequestMethod.POST)
    public String voteAnswerInsert(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int vno = Integer.parseInt(request.getParameter("vno"));

        if(sid != null && sid.equals("admin")) {
            VoteList voteAnswer = new VoteList();
            voteAnswer.setTitle(request.getParameter("title"));
            voteAnswer.setColorNum(request.getParameter("colorNum"));
            voteAnswer.setVno(vno);
            voteService.voteAnswerInsert(voteAnswer);

            return "redirect:/admin/getVote.do?vno=" + vno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "editAnswer.do", method = RequestMethod.POST)
    public String voteAnswerUpdate(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int vno = Integer.parseInt(request.getParameter("vno"));

        if(sid != null && sid.equals("admin")) {
            VoteList voteAnswer = new VoteList();
            voteAnswer.setTitle(request.getParameter("title"));
            voteAnswer.setLno(Integer.parseInt(request.getParameter("lno")));
            voteService.voteAnswerEdit(voteAnswer);

            return "redirect:/admin/getVote.do?vno=" + vno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delAnswerPro.do", method = RequestMethod.GET)
    public String voteAnswerDelete(@RequestParam int vno, @RequestParam int lno, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(vno != 0 && sid != null && sid.equals("admin")) {
            voteService.voteAnswerDelete(lno);
            return "redirect:/admin/getVote.do?vno=" + vno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "getMyList.do", method = RequestMethod.GET)
    public String voteMyList(HttpServletRequest request, Model model) throws Exception{

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {

            List<Vote> voteList = voteService.voteMyList(sid);

            model.addAttribute("title", "나의 투표 내역");
            model.addAttribute("voteList", voteList);
            return "/vote/voteMyList";
        } else {
            return "redirect:/";
        }
    }
}