package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Attendance;
import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/attend/*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    HttpSession session;

    @RequestMapping(value = "getMyAttend.do", method = RequestMethod.GET)
    public String AttendanceInsertPro(Member member, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null) {

            LocalDate now = LocalDate.now();

            DateTimeFormatter dateYearMonth = DateTimeFormatter.ofPattern("yyyyMM");
            String yearMonth = now.format(dateYearMonth);
            int day = now.lengthOfMonth();

            Attendance attendance = new Attendance();
            attendance.setDateYearMonth(yearMonth);
            attendance.setAuthor(sid);

            List<Attendance> attendanceList = attendanceService.attendanceList(attendance);
            model.addAttribute("attendanceList", attendanceList);
            model.addAttribute("day", day);

            return "/attendance/attendanceList";

        } else {

            return "redirect:/";

        }

    }

}