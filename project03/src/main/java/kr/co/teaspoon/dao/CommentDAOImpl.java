package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Comment;
import kr.co.teaspoon.dto.CommentVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CommentVo> commentList(CommentVo commentVo) throws Exception {
        return sqlSession.selectList("comment.commentList", commentVo);
    }

    @Override
    public CommentVo getComment(int cno) throws Exception {
        return sqlSession.selectOne("comment.getComment", cno);
    }

    @Override
    public CommentVo commentLast() throws Exception {
        return sqlSession.selectOne("comment.commentLast");
    }

    @Override
    public void commentInsert(Comment comment) throws Exception {
        sqlSession.insert("comment.commentInsert", comment);
    }

    @Override
    public void commentUpdate(Comment comment) throws Exception {
        sqlSession.update("comment.commentUpdate", comment);
    }

    @Override
    public void commentDelete(int cno) throws Exception {
        sqlSession.delete("comment.commentDelete", cno);
    }

    @Override
    public void commentDeleteAll(Comment comment) throws Exception {
        sqlSession.delete("comment.commentDeleteAll", comment);
    }
}