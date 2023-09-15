package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Member;

public interface MemberDAO {

    public Member memberDetail(String id) throws Exception;
    public void memberInsert(Member member) throws Exception;

}