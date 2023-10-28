package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user/*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    HttpSession session;

    BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "joinAgree.do", method = RequestMethod.GET)
    public String getAgree(Model model) throws Exception {
        model.addAttribute("title", "회원가입");
        return "/member/agree";
    }

    @RequestMapping(value = "join.do", method = RequestMethod.GET)
    public String getJoin(Model model) throws Exception {
        model.addAttribute("title", "회원가입");
        return "/member/join";
    }

    @RequestMapping(value="checkId.do", method=RequestMethod.POST)
    public void checkId(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {

        String id = request.getParameter("id");
        Member member = memberService.memberDetail(id);

        boolean result = member == null;

        JSONObject json = new JSONObject();
        json.put("result", result);
        PrintWriter out = response.getWriter();
        out.println(json.toString());

    }

    @RequestMapping(value = "joinPro.do", method = RequestMethod.POST)
    public String memberInsertPro(Member member, Model model) throws Exception {

        String pw = pwEncoder.encode(member.getPw());
        member.setPw(pw);
        member.setGrade(2);     // 일반 사용자 - 2
        memberService.memberInsert(member);

        return "redirect:/user/login.do";

    }

    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("title", "로그인");
        String msg = request.getParameter("msg") != null ? request.getParameter("msg") : "";
        if(msg.equals("no")) {
            model.addAttribute("msg", "아이디/비밀번호를 잘못입력했습니다.");
        }
        return "/member/login";
    }

    @RequestMapping(value = "loginPro.do", method = RequestMethod.POST)
    public String getLoginPro(@RequestParam String id, @RequestParam String pw, Model model) throws Exception {

        Member member = memberService.memberCanLog(id);

        if(member != null){
            boolean loginCheck = pwEncoder.matches(pw, member.getPw());
            if(loginCheck){
                memberService.memberUpdateVisited(id);

                if((member.getVisited() + 1) % 100 == 0){
                    Member mem = new Member();
                    mem.setId(id);
                    mem.setPt(5);
                    memberService.memberUpdatePoint(mem);
                }

                session.setAttribute("sid", id);
                return "redirect:/";
            } else {
                return "redirect:/user/login.do?msg=no";
            }
        } else {
            return "redirect:/user/login.do?msg=no";
        }

    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String memberLogout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "myPage.do", method = RequestMethod.GET)
    public String getDetail(Model model) throws Exception {

        String id = (String) session.getAttribute("sid");

        if(id != null) {
            Member member = memberService.memberDetail(id);

            model.addAttribute("member", member);
            model.addAttribute("title", "마이페이지");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            return "/member/myPage";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String updateDetail(Model model) throws Exception {

        String id = (String) session.getAttribute("sid");

        if(id != null) {
            Member member = memberService.memberDetail(id);

            model.addAttribute("member", member);
            model.addAttribute("title", "마이페이지");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            return "/member/updateInfo";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "editPro.do", method = RequestMethod.POST)
    public String updateDetailPro(HttpServletRequest request, Model model) throws Exception {

        String id = (String) session.getAttribute("sid");

        String email = request.getParameter("email");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");
        String postcode = request.getParameter("postcode");
        String tel = request.getParameter("tel");

        if(id != null) {
            Member member = new Member();
            member.setId(id);
            member.setEmail(email);
            member.setTel(tel);
            member.setAddr1(addr1);
            member.setAddr2(addr2);
            member.setPostcode(postcode);

            memberService.memberUpdateInfo(member);

            return "redirect:/user/myPage.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "editPw.do", method = RequestMethod.GET)
    public String updatePw(Model model) throws Exception {

        String id = (String) session.getAttribute("sid");

        if(id != null) {
            model.addAttribute("title", "마이페이지");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            return "/member/updatePw";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value="checkPw.do", method=RequestMethod.POST)
    public void checkPw(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {

        String id = (String) session.getAttribute("sid");
        String pw = request.getParameter("pw");

        Member member = memberService.memberDetail(id);

        boolean result = pwEncoder.matches(pw, member.getPw());

        JSONObject json = new JSONObject();
        json.put("result", result);
        PrintWriter out = response.getWriter();
        out.println(json.toString());

    }

    @RequestMapping(value = "editPwPro.do", method = RequestMethod.POST)
    public String updatePwPro(HttpServletRequest request, Model model) throws Exception {

        String id = (String) session.getAttribute("sid");
        String pwNew = request.getParameter("pwNew");

        if(id != null) {
            Member member = new Member();
            member.setId(id);

            String pw = pwEncoder.encode(pwNew);
            member.setPw(pw);

            memberService.memberUpdatePw(member);

            return "redirect:/user/myPage.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "deletePro.do", method = RequestMethod.GET)
    public String updateMemberState(@RequestParam String id, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {
            memberService.memberNotUse(id);
            if(!sid.equals("admin")) {
                session.invalidate();
                return "redirect:/";
            } else {
                return "redirect:/admin/memberList.do";
            }
        } else {
            return "redirect:/";
        }

    }

}