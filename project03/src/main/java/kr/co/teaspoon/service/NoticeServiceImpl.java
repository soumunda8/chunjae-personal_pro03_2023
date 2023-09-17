package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.NoticeDAOImpl;
import kr.co.teaspoon.dto.Notice;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDAOImpl noticeDAO;

    @Override
    public List<Notice> noticeList(Page page) throws Exception {
        return noticeDAO.noticeList(page);
    }

    @Override
    public int noticeListCount(Page page) throws Exception {
        return noticeDAO.noticeListCount(page);
    }

    @Override
    public Notice noticeDetail(int seq) throws Exception {
        return noticeDAO.noticeDetail(seq);
    }

    @Override
    public void noticeInsert(Notice notice) throws Exception {
        noticeDAO.noticeInsert(notice);
    }

    @Override
    public void noticeUpdate(Notice notice) throws Exception {
        noticeDAO.noticeUpdate(notice);
    }

    @Override
    public void noticeUpdateVisited(int seq) throws Exception {
        noticeDAO.noticeUpdateVisited(seq);
    }

    @Override
    public void noticeDelete(int seq) throws Exception {
        noticeDAO.noticeDelete(seq);
    }
}
