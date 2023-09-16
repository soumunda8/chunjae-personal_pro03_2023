package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Notice;
import kr.co.teaspoon.util.Page;

import java.util.List;

public interface NoticeDAO {

    public List<Notice> noticeList(Page page) throws Exception;
    public int noticeListCount(Page page) throws Exception;
    public Notice noticeDetail(int seq) throws Exception;
    public void noticeInsert(Notice notice) throws Exception;
    public void noticeUpdate(Notice notice) throws Exception;
    public void noticeUpdateVisited(int seq) throws Exception;
    public void noticeDelete(int seq) throws Exception;

}
