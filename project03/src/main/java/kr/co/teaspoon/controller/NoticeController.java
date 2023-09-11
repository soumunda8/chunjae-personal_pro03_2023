package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Notice;
import kr.co.teaspoon.service.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String noticeList(Model model) throws Exception {
        List<Notice> noticeList = noticeService.noticeList();
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("title", "공지사항 목록");
        return "/notice/noticeList";
    }

}