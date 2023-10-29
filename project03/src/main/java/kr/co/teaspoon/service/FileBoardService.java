package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.FileBoard;

import java.util.List;

public interface FileBoardService {

    public List<FileBoard> fileListByPar(FileBoard fileBoard) throws Exception;
    public FileBoard fileByFno(int fno) throws Exception;
    public void filesInsert(FileBoard fileBoard) throws Exception;
    public void filesDelete(int fno) throws Exception;
    public void filesDeleteAll(FileBoard fileBoard) throws Exception;

}