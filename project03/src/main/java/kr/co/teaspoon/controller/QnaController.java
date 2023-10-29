package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Qna;
import kr.co.teaspoon.dto.QnaVo;
import kr.co.teaspoon.service.QnaService;
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
@RequestMapping("/qna/*")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "myQnaList.do", method = RequestMethod.GET)
    public String getMyQnaList(Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {
            List<QnaVo> qnaList = qnaService.qnaList(sid);

            model.addAttribute("qnaList", qnaList);
            model.addAttribute("title", "나의 문의");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            return "/qna/myQna";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public String getMyQnaDetail(@RequestParam("no") int seq, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {
            QnaVo qna = qnaService.getQna(seq);

            model.addAttribute("qna", qna);
            model.addAttribute("title", "나의 문의");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            return "/qna/myQnaDetail";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    public String qnaAdd(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        if(sid != null){
            return "/qna/qnaInsert";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String qnaAddPro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        if(sid != null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Qna qna = new Qna();
            qna.setContent(content);
            qna.setTitle(title);
            qna.setAuthor(sid);
            qnaService.qnaInsert(qna);
            return "redirect:/qna/myQnaList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String qnaUpdate(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int qno = request.getParameter("no") != null ? Integer.parseInt(request.getParameter("no")) : 0;

        if(qno != 0 && sid != null){
            model.addAttribute("title", "나의 문의");
            model.addAttribute("titleSub", "나의 회원정보 및 투표내역을 확인해보세요.");

            QnaVo qna = qnaService.getQna(qno);
            model.addAttribute("qna", qna);

            return "/qna/qnaUpdate";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String qnaUpdatePro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        int qno = request.getParameter("no") != null ? Integer.parseInt(request.getParameter("no")) : 0;

        if(qno != 0 && sid != null){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Qna qna = new Qna();
            qna.setContent(content);
            qna.setTitle(title);
            qna.setQno(qno);
            qnaService.qnaUpdate(qna);
            return "redirect:/qna/detail.do?no=" + qno;
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String qnaDelete(@RequestParam int no, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        QnaVo qna = qnaService.getQna(no);

        if(no != 0 && sid != null && (sid.equals("admin") || sid.equals(qna.getAuthor()))) {
            qnaService.qnaDelete(no);
            if(sid.equals("admin")) {
                return "redirect:/admin/noticeList.do";
            } else {
                return "redirect:/qna/myQnaList.do";
            }
        } else {
            return "redirect:/";
        }

    }

}