package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.AttendanceDAO;
import kr.co.teaspoon.dao.MemberDAO;
import kr.co.teaspoon.dao.MemberDAOImpl;
import kr.co.teaspoon.dto.Attendance;
import kr.co.teaspoon.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDAO attendanceDAO;

    @Autowired
    private MemberService memberService;

    @Override
    public List<Attendance> attendanceList(Attendance attendance) throws Exception {
        return attendanceDAO.attendanceList(attendance);
    }

    @Override
    public void attendanceUserInsert(Attendance attendance) throws Exception {
        attendanceDAO.attendanceUserInsert(attendance);
    }

    @Override
    public void updateNewUser(String id) throws Exception {

        LocalDate now = LocalDate.now();

        DateTimeFormatter dateYearMonth = DateTimeFormatter.ofPattern("yyyyMM");
        String yearMonth = now.format(dateYearMonth);
        String day = String.valueOf(now.lengthOfMonth());

        Attendance attendance = new Attendance();
        attendance.setAuthor(id);
        attendance.setDateYearMonth(yearMonth);
        attendance.setDateDay(day);
        this.attendanceUserInsert(attendance);

    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void autoUpdateNewMonth() throws Exception {

        List<Member> memberList = memberService.getMemberId();
        for(Member mem : memberList) {
            this.updateNewUser(mem.getId());
        }

    }

}