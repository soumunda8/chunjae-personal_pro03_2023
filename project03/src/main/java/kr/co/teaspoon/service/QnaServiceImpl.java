package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.QnaDAO;
import kr.co.teaspoon.dto.Qna;
import kr.co.teaspoon.dto.QnaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaDAO qnaDAO;

    @Override
    public List<QnaVo> qnaAllList() throws Exception {
        return qnaDAO.qnaAllList();
    }

    @Override
    public List<QnaVo> qnaList(String author) throws Exception {
        return qnaDAO.qnaList(author);
    }

    @Override
    public QnaVo getQna(int qno) throws Exception {
        return qnaDAO.getQna(qno);
    }

    @Override
    public void qnaInsert(Qna qna) throws Exception {
        qnaDAO.qnaInsert(qna);
    }

    @Override
    public void qnaUpdate(Qna qna) throws Exception {
        qnaDAO.qnaUpdate(qna);
    }

    @Override
    public void answerUpdate(Qna qna) throws Exception {
        qnaDAO.answerUpdate(qna);
    }

    @Override
    public void qnaDelete(int qno) throws Exception {
        qnaDAO.qnaDelete(qno);
    }

}