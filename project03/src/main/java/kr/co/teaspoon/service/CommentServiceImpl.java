package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.CommentDAO;
import kr.co.teaspoon.dto.Comment;
import kr.co.teaspoon.dto.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public List<CommentVo> commentList(CommentVo commentVo) throws Exception {
        return commentDAO.commentList(commentVo);
    }

    @Override
    public CommentVo getComment(int cno) throws Exception {
        return commentDAO.getComment(cno);
    }

    @Override
    public CommentVo commentInsert(Comment comment) throws Exception {
        commentDAO.commentInsert(comment);
        return commentDAO.commentLast();
    }

    @Override
    public void commentUpdate(Comment comment) throws Exception {
        commentDAO.commentUpdate(comment);
    }

    @Override
    public void commentDelete(int cno) throws Exception {
        commentDAO.commentDelete(cno);
    }

    @Override
    public void commentDeleteAll(Comment comment) throws Exception {
        commentDAO.commentDeleteAll(comment);
    }
}