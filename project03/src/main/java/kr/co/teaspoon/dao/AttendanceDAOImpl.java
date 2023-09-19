package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Attendance;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Attendance> attendanceAllList(String dateYearMonth) throws Exception {
        return sqlSession.selectList("attendance.attendanceAllList", dateYearMonth);
    }

    @Override
    public List<Attendance> attendanceList(Attendance attendance) throws Exception {
        return sqlSession.selectList("attendance.attendanceList", attendance);
    }

    @Override
    public int attendanceListCount(Attendance attendance) throws Exception {
        return sqlSession.selectOne("attendance.attendanceListCount", attendance);
    }

    @Override
    public void attendanceUserInsert(Attendance attendance) throws Exception {
        sqlSession.insert("attendance.attendanceUserInsert", attendance);
    }

}
