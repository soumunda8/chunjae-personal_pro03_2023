package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.NoticeDAOImpl;
import kr.co.teaspoon.dto.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDAOImpl noticeDAO;

    @Override
    public List<Notice> noticeList() throws Exception {
        return noticeDAO.noticeList();
    }

}
