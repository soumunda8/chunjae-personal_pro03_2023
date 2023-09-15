package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Member memberDetail(String id) throws Exception {
        return sqlSession.selectOne("member.memberDetail", id);
    }

    @Override
    public void memberInsert(Member member) throws Exception {
        sqlSession.insert("member.memberInsert", member);
    }
}