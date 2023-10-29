package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.BoardDAOImpl;
import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAOImpl boardDAO;

    @Override
    public List<BoardVo> boardList(Page page) throws Exception {
        return boardDAO.boardList(page);
    }

    @Override
    public List<BoardVo> boardForMain(String boardNm) throws Exception {
        return boardDAO.boardForMain(boardNm);
    }

    @Override
    public int boardListCount(Page page) throws Exception {
        return boardDAO.boardListCount(page);
    }

    @Override
    public BoardVo boardDetail(int seq) throws Exception {
        return boardDAO.boardDetail(seq);
    }

    @Override
    public BoardVo boardDetailLast() throws Exception {
        return boardDAO.boardDetailLast();
    }

    @Override
    public void boardInsert(Board board) throws Exception {
        boardDAO.boardInsert(board);
    }

    @Override
    public void boardUpdate(Board board) throws Exception {
        boardDAO.boardUpdate(board);
    }

    @Override
    public void boardUpdateVisited(int seq) throws Exception {
        boardDAO.boardUpdateVisited(seq);
    }

    @Override
    public void boardDelete(int seq) throws Exception {
        boardDAO.boardDelete(seq);
    }
}
