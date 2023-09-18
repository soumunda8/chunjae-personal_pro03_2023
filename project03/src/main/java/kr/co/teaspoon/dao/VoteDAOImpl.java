package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Vote;
import kr.co.teaspoon.dto.VoteList;
import kr.co.teaspoon.dto.VoteUser;
import kr.co.teaspoon.util.Page;
import kr.co.teaspoon.dto.VoteCountVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteDAOImpl implements VoteDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Vote> voteAllList(Page page) throws Exception {
        return sqlSession.selectList("vote.voteAllList", page);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return sqlSession.selectOne("vote.totalCount", page);
    }

    @Override
    public List<Vote> voteAllListForAdmin(Page page) throws Exception {
        return sqlSession.selectList("vote.voteAllListForAdmin", page);
    }

    @Override
    public int totalCountForAdmin(Page page) throws Exception {
        return sqlSession.selectOne("vote.totalCountForAdmin", page);
    }

    @Override
    public Vote voteDetail(int vno) throws Exception {
        return sqlSession.selectOne("vote.voteDetail", vno);
    }

    @Override
    public void voteInsert(Vote vote) throws Exception {
        sqlSession.insert("vote.voteInsert", vote);
    }

    @Override
    public void voteDelete(int vno) throws Exception {
        sqlSession.delete("vote.voteDelete", vno);
    }

    @Override
    public void voteUpdateState(int vno) throws Exception {
        sqlSession.update("vote.voteUpdateState", vno);
    }

    @Override
    public void voteUpdateUse(int vno) throws Exception {
        sqlSession.update("vote.voteUpdateUse", vno);
    }

    @Override
    public void voteEdit(Vote vote) throws Exception {
        sqlSession.update("vote.voteEdit", vote);
    }

    @Override
    public void voteVisitCount(int vno) throws Exception {
        sqlSession.update("vote.voteVisitCount", vno);
    }

    @Override
    public void voteFinalInsert(VoteCountVo voteCount) throws Exception {
        sqlSession.update("vote.voteFinalInsert", voteCount);
    }

    @Override
    public List<VoteList> voteAnswerList(int vno) throws Exception {
        return sqlSession.selectList("vote.voteAnswerList", vno);
    }

    @Override
    public void voteAnswerInsert(VoteList voteList) throws Exception {
        sqlSession.insert("vote.voteAnswerInsert", voteList);
    }

    @Override
    public void voteAnswerDelete(int lno) throws Exception {
        sqlSession.delete("vote.voteAnswerDelete", lno);
    }

    @Override
    public void voteAllAnswerDelete(int vno) throws Exception {
        sqlSession.delete("vote.voteAllAnswerDelete", vno);
    }

    @Override
    public void voteAnswerEdit(VoteList voteList) throws Exception {
        sqlSession.update("vote.voteAnswerEdit", voteList);
    }

    @Override
    public VoteUser voteUserList(VoteUser voteUser) throws Exception {
        return sqlSession.selectOne("vote.voteUserList", voteUser);
    }

    @Override
    public int voteUserCnt(int lno) throws Exception {
        return sqlSession.selectOne("vote.voteUserCnt", lno);
    }

    @Override
    public void voteUserInsert(VoteUser voteUser) throws Exception {
        sqlSession.insert("vote.voteUserInsert", voteUser);
    }

    @Override
    public List<Vote> voteMyList(String sid) throws Exception {
        return sqlSession.selectList("vote.voteMyList", sid);
    }

    @Override
    public void voteUserDelete(VoteUser voteUser) throws Exception {
        sqlSession.insert("vote.voteUserDelete", voteUser);
    }

    @Override
    public List<VoteCountVo> voteCountList(int vno) throws Exception {
        return sqlSession.selectList("vote.voteCountList", vno);
    }

    @Override
    public VoteCountVo voteMaxCountList(int vno) throws Exception {
        return sqlSession.selectOne("vote.voteMaxCountList", vno);
    }

    @Override
    public int voteCountCnt(int vno) throws Exception {
        return sqlSession.selectOne("vote.voteCountCnt", vno);
    }

}