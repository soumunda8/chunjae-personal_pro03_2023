package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Attendance;

import java.util.List;

public interface AttendanceDAO {

    public List<Attendance> attendanceList(Attendance attendance) throws Exception;

    public void attendanceUserInsert(Attendance attendance) throws Exception;

}