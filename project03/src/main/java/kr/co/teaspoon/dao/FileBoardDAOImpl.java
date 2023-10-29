package kr.co.teaspoon.dao;

import kr.co.teaspoon.dto.FileBoard;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileBoardDAOImpl implements FileBoardDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<FileBoard> fileListByPar(FileBoard fileBoard) throws Exception {
        return sqlSession.selectList("fileBoard.fileListByPar", fileBoard);
    }

    @Override
    public FileBoard fileByFno(int fno) throws Exception {
        return sqlSession.selectOne("fileBoard.fileByFno", fno);
    }

    @Override
    public void filesInsert(FileBoard fileBoard) throws Exception {
        sqlSession.insert("fileBoard.filesInsert", fileBoard);
    }

    @Override
    public void filesDelete(int fno) throws Exception {
        sqlSession.delete("fileBoard.filesDelete", fno);
    }

    @Override
    public void filesDeleteAll(FileBoard fileBoard) throws Exception {
        sqlSession.delete("fileBoard.filesDeleteAll", fileBoard);
    }
}