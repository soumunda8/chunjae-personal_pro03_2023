package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Notice;

import java.util.List;

public interface NoticeDAO {

    public List<Notice> noticeList() throws Exception;

}
