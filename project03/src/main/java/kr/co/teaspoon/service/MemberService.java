package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Member;

public interface MemberService {

    public Member memberDetail(String id) throws Exception;
    public void memberInsert(Member member) throws Exception;

}