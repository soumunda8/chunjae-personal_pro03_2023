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

    @RequestMapping(value = "join.do", method = RequestMethod.POST)
    public String getAgree(Model model) throws Exception {
        return "/member/join";
    }

    @RequestMapping(value="checkId.do", method=RequestMethod.POST)
    public void checkId(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {

        String id = request.getParameter("id");
        Member member = memberService.memberDetail(id);
        boolean result = false;
        if(member != null){
            result = false;
        } else {
            result = true;
        }
        JSONObject json = new JSONObject();
        json.put("result", result);
        PrintWriter out = response.getWriter();
        out.println(json.toString());

    }

    @RequestMapping(value = "joinPro.do", method = RequestMethod.POST)
    public String memberInsertPro(Member member, Model model) throws Exception {

        String pw = pwEncoder.encode(member.getPw());
        member.setPw(pw);
        memberService.memberInsert(member);

        return "redirect:/";

    }

}