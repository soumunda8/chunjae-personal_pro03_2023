package kr.co.teaspoon.service;

import kr.co.teaspoon.dto.Vote;
import kr.co.teaspoon.dto.VoteList;
import kr.co.teaspoon.dto.VoteUser;
import kr.co.teaspoon.util.Page;
import kr.co.teaspoon.vo.VoteCount;

import java.util.List;

public interface VoteService {

    public List<Vote> voteAllList(Page page) throws Exception;
    public int totalCount(Page page) throws Exception;
    public List<Vote> voteAllListForAdmin(Page page) throws Exception;
    public int totalCountForAdmin(Page page) throws Exception;
    public Vote voteDetail(int vno) throws Exception;
    public void voteInsert(Vote vote) throws Exception;
    public void voteDelete(int vno) throws Exception;
    public void voteUpdateState(int vno) throws Exception;
    public void voteUpdateUse(int vno) throws Exception;
    public void voteEdit(Vote vote) throws Exception;
    public void voteVisitCount(int vno) throws Exception;
    public void voteFinalInsert(VoteCount voteCount) throws Exception;

    public List<VoteList> voteAnswerList(int vno) throws Exception;
    public void voteAnswerInsert(VoteList voteList) throws Exception;
    public void voteAnswerDelete(int lno) throws Exception;
    public void voteAllAnswerDelete(int vno) throws Exception;
    public void voteAnswerEdit(VoteList voteList) throws Exception;

    public List<Vote> voteMyList(String sid) throws Exception;
    public VoteUser voteUserList(VoteUser voteUser) throws Exception;
    public int voteUserCnt(int lno) throws Exception;
    public void voteUserInsert(VoteUser voteUser) throws Exception;
    public void voteUserDelete(VoteUser voteUser) throws Exception;

    public List<VoteCount> voteCountList(int vno) throws Exception;
    public VoteCount voteMaxCountList(int vno) throws Exception;
    public int voteCountCnt(int vno) throws Exception;

}