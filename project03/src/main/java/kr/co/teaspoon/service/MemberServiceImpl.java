package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.MemberDAO;
import kr.co.teaspoon.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member memberDetail(String id) throws Exception {
        return memberDAO.memberDetail(id);
    }

    @Override
    public void memberInsert(Member member) throws Exception {
        memberDAO.memberInsert(member);
    }
}