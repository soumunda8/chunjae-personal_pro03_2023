package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.FileBoardDAO;
import kr.co.teaspoon.dto.FileBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileBoardServiceImpl implements FileBoardService {

    @Autowired
    private FileBoardDAO fileBoardDAO;

    @Override
    public List<FileBoard> fileListByPar(FileBoard fileBoard) throws Exception {
        return fileBoardDAO.fileListByPar(fileBoard);
    }

    @Override
    public FileBoard fileByFno(int fno) throws Exception {
        return fileBoardDAO.fileByFno(fno);
    }

    @Override
    public void filesInsert(FileBoard fileBoard) throws Exception {
        fileBoardDAO.filesInsert(fileBoard);
    }

    @Override
    public void filesDelete(int fno) throws Exception {
        fileBoardDAO.filesDelete(fno);
    }

    @Override
    public void filesDeleteAll(int seq) throws Exception {
        fileBoardDAO.filesDeleteAll(seq);
    }

}