package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/")
    public String index(Locale locale, Model model) throws Exception {

        model.addAttribute("title", "티스푼");

        List<BoardVo> noticeList = boardService.boardForMain("notice");
        model.addAttribute("noticeList", noticeList);

        List<BoardVo> fileBoardList = boardService.boardForMain("fileBoard");
        model.addAttribute("fileBoardList", fileBoardList);

        List<BoardVo> freeBoardList = boardService.boardForMain("freeBoard");
        model.addAttribute("freeBoardList", freeBoardList);

        return "/index";
    }

}