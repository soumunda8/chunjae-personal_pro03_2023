package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.util.Page;

import java.util.List;

public interface BoardService {

    public List<BoardVo> boardList(Page page) throws Exception;
    public int boardListCount(Page page) throws Exception;
    public BoardVo boardDetail(int seq) throws Exception;
    public void boardInsert(Board board) throws Exception;
    public void boardUpdate(Board board) throws Exception;
    public void boardUpdateVisited(int seq) throws Exception;
    public void boardDelete(int seq) throws Exception;

}