package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Member;
import kr.co.teaspoon.util.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Member> memberAllList(Page page) throws Exception {
        return sqlSession.selectList("member.memberAllList", page);
    }

    @Override
    public int memberAllListCount(Page page) throws Exception {
        return sqlSession.selectOne("member.memberAllListCount", page);
    }

    @Override
    public Member memberCanLog(String id) throws Exception {
        return sqlSession.selectOne("member.memberCanLog", id);
    }

    @Override
    public Member memberDetail(String id) throws Exception {
        return sqlSession.selectOne("member.memberDetail", id);
    }

    @Override
    public List<Member> getMemberId() throws Exception {
        return sqlSession.selectList("member.getMemberId");
    }

    @Override
    public void memberInsert(Member member) throws Exception {
        sqlSession.insert("member.memberInsert", member);
    }

    @Override
    public void memberUpdateInfo(Member member) throws Exception {
        sqlSession.update("member.memberUpdateInfo",member);
    }

    @Override
    public void memberUpdatePw(Member member) throws Exception {
        sqlSession.update("member.memberUpdatePw",member);
    }

    @Override
    public void memberUpdateVisited(String id) throws Exception {
        sqlSession.update("member.memberUpdateVisited", id);
    }

    @Override
    public void memberUpdatePoint(Member member) throws Exception {
        sqlSession.update("member.memberUpdatePoint", member);
    }

    @Override
    public void memberNotUse(String id) throws Exception {
        sqlSession.update("member.memberNotUse", id);
    }
}