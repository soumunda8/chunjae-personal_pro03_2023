package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Attendance;

import java.util.List;

public interface AttendanceService {
    public List<Attendance> attendanceAllList(String dateYearMonth) throws Exception;
    public List<Attendance> attendanceList(Attendance attendance) throws Exception;
    public int attendanceListCount(Attendance attendance) throws Exception;
    public void attendanceUserInsert(Attendance attendance) throws Exception;
}