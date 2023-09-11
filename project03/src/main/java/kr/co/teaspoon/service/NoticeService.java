package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Notice;

import java.util.List;

public interface NoticeService {

    public List<Notice> noticeList() throws Exception;

}