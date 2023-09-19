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
    public List<Attendance> attendanceAllList(String dateYearMonth) throws Exception {
        return attendanceDAO.attendanceAllList(dateYearMonth);
    }

    @Override
    public List<Attendance> attendanceList(Attendance attendance) throws Exception {
        Member member = new Member();
        member.setId(attendance.getAuthor());
        member.setPt(10);
        memberService.memberUpdatePoint(member);
        return attendanceDAO.attendanceList(attendance);
    }

    @Override
    public int attendanceListCount(Attendance attendance) throws Exception {
        return attendanceDAO.attendanceListCount(attendance);
    }

    @Override
    public void attendanceUserInsert(Attendance attendance) throws Exception {
        attendanceDAO.attendanceUserInsert(attendance);
    }

    // 종료시 한달 다채웠으면 + 1000pt
    @Scheduled(cron = "59 59 23 L * *")
    public void autoUpdateNewMonth() throws Exception {

        LocalDate now = LocalDate.now();
        DateTimeFormatter dateYearMonth = DateTimeFormatter.ofPattern("yyyyMM");
        String yearMonth = now.format(dateYearMonth);
        int day = now.lengthOfMonth();

        List<Member> memberList = memberService.getMemberId();
        for(Member mem : memberList) {
            Attendance attend = new Attendance();
            attend.setAuthor(mem.getId());
            attend.setDateYearMonth(yearMonth);
            int cnt = this.attendanceListCount(attend);
            if(day == cnt) {
                Member member = new Member();
                member.setId(mem.getId());
                member.setPt(20);
                memberService.memberUpdatePoint(member);
            }
        }

    }

}