package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Qna;
import kr.co.teaspoon.dto.QnaVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaDAOImpl implements QnaDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<QnaVo> qnaAllList() throws Exception {
        return sqlSession.selectList("qna.qnaAllList");
    }

    @Override
    public List<QnaVo> qnaList(String author) throws Exception {
        return sqlSession.selectList("qna.qnaList", author);
    }

    @Override
    public QnaVo getQna(int qno) throws Exception {
        return sqlSession.selectOne("qna.getQna", qno);
    }

    @Override
    public void qnaInsert(Qna qna) throws Exception {
        sqlSession.insert("qna.qnaInsert", qna);
    }

    @Override
    public void qnaUpdate(Qna qna) throws Exception {
        sqlSession.update("qna.qnaUpdate", qna);
    }

    @Override
    public void answerUpdate(Qna qna) throws Exception {
        sqlSession.update("qna.answerUpdate", qna);
    }

    @Override
    public void qnaDelete(int qno) throws Exception {
        sqlSession.delete("qna.qnaDelete", qno);
    }
}