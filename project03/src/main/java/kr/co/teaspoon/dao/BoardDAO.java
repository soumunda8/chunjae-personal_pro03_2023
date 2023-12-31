package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.util.Page;

import java.util.List;

public interface BoardDAO {

    public List<BoardVo> boardList(Page page) throws Exception;
    public List<BoardVo> boardForMain(String boardNm) throws Exception;
    public int boardListCount(Page page) throws Exception;
    public BoardVo boardDetail(int seq) throws Exception;
    public BoardVo boardDetailLast() throws Exception;
    public void boardInsert(Board notice) throws Exception;
    public void boardUpdate(Board notice) throws Exception;
    public void boardUpdateVisited(int seq) throws Exception;
    public void boardDelete(int seq) throws Exception;

}
