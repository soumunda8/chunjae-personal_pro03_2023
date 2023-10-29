package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Qna;
import kr.co.teaspoon.dto.QnaVo;

import java.util.List;

public interface QnaService {

    public List<QnaVo> qnaAllList() throws Exception;
    public List<QnaVo> qnaList(String author) throws Exception;
    public QnaVo getQna(int qno) throws Exception;
    public void qnaInsert(Qna qna) throws Exception;
    public void qnaUpdate(Qna qna) throws Exception;
    public void answerUpdate(Qna qna) throws Exception;
    public void qnaDelete(int qno) throws Exception;

}