package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.MemberDAO;
import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private AttendanceService attendanceService;

    @Override
    public List<Member> memberAllList(Page page) throws Exception {
        return memberDAO.memberAllList(page);
    }

    @Override
    public int memberAllListCount(Page page) throws Exception {
        return memberDAO.memberAllListCount(page);
    }

    @Override
    public Member memberCanLog(String id) throws Exception {
        return memberDAO.memberCanLog(id);
    }

    @Override
    public Member memberDetail(String id) throws Exception {
        return memberDAO.memberDetail(id);
    }

    @Override
    public List<Member> getMemberId() throws Exception {
        return memberDAO.getMemberId();
    }

    @Override
    public void memberInsert(Member member) throws Exception {
        memberDAO.memberInsert(member);
    }

    @Override
    public void memberUpdateInfo(Member member) throws Exception {
        memberDAO.memberUpdateInfo(member);
    }

    @Override
    public void memberUpdatePw(Member member) throws Exception {
        memberDAO.memberUpdatePw(member);
    }

    @Override
    public void memberUpdateVisited(String id) throws Exception {
        memberDAO.memberUpdateVisited(id);
    }

    @Override
    public void memberUpdatePoint(Member member) throws Exception {
        memberDAO.memberUpdatePoint(member);
    }

    @Override
    public void memberNotUse(String id) throws Exception {
        memberDAO.memberNotUse(id);
    }
}