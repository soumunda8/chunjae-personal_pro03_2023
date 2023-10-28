package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.util.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<BoardVo> boardList(Page page) throws Exception {
        return sqlSession.selectList("board.boardList", page);
    }

    @Override
    public int boardListCount(Page page) throws Exception {
        return sqlSession.selectOne("board.boardListCount", page);
    }

    @Override
    public BoardVo boardDetail(int seq) throws Exception {
        return sqlSession.selectOne("board.boardDetail", seq);
    }

    @Override
    public void boardInsert(Board board) throws Exception {
        sqlSession.insert("board.boardInsert", board);
    }

    @Override
    public void boardUpdate(Board board) throws Exception {
        sqlSession.update("board.boardUpdate", board);
    }

    @Override
    public void boardUpdateVisited(int seq) throws Exception {
        sqlSession.update("board.boardUpdateVisited", seq);
    }

    @Override
    public void boardDelete(int seq) throws Exception {
        sqlSession.delete("board.boardDelete", seq);
    }
}