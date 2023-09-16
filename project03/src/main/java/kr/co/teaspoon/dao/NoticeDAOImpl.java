package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Notice;
import kr.co.teaspoon.util.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Notice> noticeList(Page page) throws Exception {
        return sqlSession.selectList("notice.noticeList", page);
    }

    @Override
    public int noticeListCount(Page page) throws Exception {
        return sqlSession.selectOne("notice.noticeListCount", page);
    }

    @Override
    public Notice noticeDetail(int seq) throws Exception {
        return sqlSession.selectOne("notice.noticeDetail", seq);
    }

    @Override
    public void noticeInsert(Notice notice) throws Exception {
        sqlSession.insert("notice.noticeInsert", notice);
    }

    @Override
    public void noticeUpdate(Notice notice) throws Exception {
        sqlSession.update("notice.noticeUpdate", notice);
    }

    @Override
    public void noticeUpdateVisited(int seq) throws Exception {
        sqlSession.update("notice.noticeUpdateVisited", seq);
    }

    @Override
    public void noticeDelete(int seq) throws Exception {
        sqlSession.delete("notice.noticeDelete", seq);
    }
}