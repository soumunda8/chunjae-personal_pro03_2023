package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.util.Page;

import java.util.List;

public interface MemberDAO {

    public List<Member> memberAllList(Page page) throws Exception;
    public int memberAllListCount(Page page) throws Exception;
    public Member memberCanLog(String id) throws Exception;
    public Member memberDetail(String id) throws Exception;
    public List<Member> getMemberId() throws Exception;
    public void memberInsert(Member member) throws Exception;
    public void memberUpdateInfo(Member member) throws Exception;
    public void memberUpdatePw(Member member) throws Exception;
    public void memberUpdateVisited(String id) throws Exception;
    public void memberUpdatePoint(Member member) throws Exception;
    public void memberNotUse(String id) throws Exception;

}