package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Comment;
import kr.co.teaspoon.dto.CommentVo;

import java.util.List;

public interface CommentService {

    public List<CommentVo> commentList(CommentVo commentVo) throws Exception;
    public CommentVo getComment(int cno) throws Exception;
    public CommentVo commentInsert(Comment comment) throws Exception;
    public void commentUpdate(Comment comment) throws Exception;
    public void commentDelete(int cno) throws Exception;
    public void commentDeleteAll(Comment comment) throws Exception;

}