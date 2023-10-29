package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Attendance;
import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String AttendanceList(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");
        //String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        LocalDate now = LocalDate.now();

        LocalDate monthFirstDate = LocalDate.of(now.getYear(), now.getMonth(), 1);
        String monthFirstDateWeek = String.valueOf(monthFirstDate.getDayOfWeek());

        DateTimeFormatter dateYearMonth = DateTimeFormatter.ofPattern("yyyyMM");
        String yearMonth = now.format(dateYearMonth);
        DateTimeFormatter getDate = DateTimeFormatter.ofPattern("dd");
        String nowDate = now.format(getDate);

        int dayList = now.lengthOfMonth();

        Attendance attendance = new Attendance();
        if(sid == null) {
            attendance.setAuthor("");
        } else {
            attendance.setAuthor(sid);
        }
        attendance.setDateYearMonth(yearMonth);

        List<Attendance> attendanceList = attendanceService.attendanceList(attendance);

        boolean pass = false;
        for(Attendance attend : attendanceList) {
            String nowDay = attend.getNowDay();
            if(nowDate.equals(nowDay)) {
                pass = true;
            }
        }

        DateTimeFormatter pageYear = DateTimeFormatter.ofPattern("yyyy");
        String currentYear = now.format(pageYear);
        DateTimeFormatter pageMonth = DateTimeFormatter.ofPattern("MM");
        String currentMonth = now.format(pageMonth);

        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("dayList", dayList);
        model.addAttribute("pass", pass);
        model.addAttribute("monthFirstDateWeek", monthFirstDateWeek);
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentMonth", currentMonth);

        return "/attendance/attendanceList";

    }

    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    public String AttendanceInsertPro(HttpServletRequest request, Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        LocalDate now = LocalDate.now();

        DateTimeFormatter dateYearMonth = DateTimeFormatter.ofPattern("yyyyMM");
        String yearMonth = now.format(dateYearMonth);
        DateTimeFormatter getDate = DateTimeFormatter.ofPattern("dd");
        String nowDate = now.format(getDate);

        Attendance attendance = new Attendance();
        attendance.setAuthor(sid);
        attendance.setDateYearMonth(yearMonth);

        List<Attendance> attendanceList = attendanceService.attendanceList(attendance);

        boolean check = true;
        for(Attendance attend : attendanceList) {
            String nowDay = attend.getNowDay();
            if(nowDate.equals(nowDay)) {
                check = false;
            }
        }

        if(sid != null && check) {

            attendance.setNowDay(nowDate);

            attendanceService.attendanceUserInsert(attendance);

            return "redirect:/attend/list.do";

        } else if(!check) {

            return "redirect:/attend/list.do";

        } else {

            return "redirect:/";

        }

    }

}