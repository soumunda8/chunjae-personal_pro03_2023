package kr.co.teaspoon.service;

import kr.co.teaspoon.dao.VoteDAO;
import kr.co.teaspoon.dto.Vote;
import kr.co.teaspoon.dto.VoteList;
import kr.co.teaspoon.dto.VoteUser;
import kr.co.teaspoon.util.Page;
import kr.co.teaspoon.dto.VoteCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDAO voteDAO;

    @Override
    public List<Vote> voteAllList(Page page) throws Exception {
        return voteDAO.voteAllList(page);
    }

    @Override
    public int totalCount(Page page) throws Exception {
        return voteDAO.totalCount(page);
    }

    @Override
    public List<Vote> voteAllListForAdmin(Page page) throws Exception {
        return voteDAO.voteAllListForAdmin(page);
    }

    @Override
    public int totalCountForAdmin(Page page) throws Exception {
        return voteDAO.totalCountForAdmin(page);
    }

    @Override
    public Vote voteDetail(int vno) throws Exception {
        return voteDAO.voteDetail(vno);
    }

    @Override
    public void voteInsert(Vote vote) throws Exception {
        voteDAO.voteInsert(vote);
    }

    @Override
    public void voteDelete(int vno) throws Exception {
        Vote vote = voteDAO.voteDetail(vno);
        if(vote.isUseYn()) {
            List<VoteList> voteList = voteDAO.voteAnswerList(vno);
            for (VoteList voteAnswer: voteList) {
                int lno = voteAnswer.getLno();
                int cnt = voteDAO.voteUserCnt(lno);
                if(cnt > 0){
                    VoteUser voteUser = new VoteUser();
                    voteUser.setVno(vno);
                    voteUser.setLno(lno);
                    voteDAO.voteUserDelete(voteUser);
                }
            }
            voteDAO.voteAllAnswerDelete(vno);
        }
        voteDAO.voteDelete(vno);
    }

    @Override
    public void voteUpdateState(int vno) throws Exception {
        voteDAO.voteUpdateState(vno);
    }

    @Override
    public void voteUpdateUse(int vno) throws Exception {
        voteDAO.voteUpdateUse(vno);
    }

    @Override
    public void voteEdit(Vote vote) throws Exception {
        voteDAO.voteEdit(vote);
    }

    @Override
    public void voteVisitCount(int vno) throws Exception {
        voteDAO.voteVisitCount(vno);
    }

    @Override
    public void voteFinalInsert(VoteCountVo voteCount) throws Exception {
        voteDAO.voteFinalInsert(voteCount);
    }

    @Override
    public List<VoteList> voteAnswerList(int vno) throws Exception {
        return voteDAO.voteAnswerList(vno);
    }

    @Override
    public void voteAnswerInsert(VoteList voteList) throws Exception {
        voteDAO.voteAnswerInsert(voteList);
    }

    @Override
    public void voteAnswerDelete(int lno) throws Exception {
        voteDAO.voteAnswerDelete(lno);
    }

    @Override
    public void voteAllAnswerDelete(int vno) throws Exception {
        voteDAO.voteAllAnswerDelete(vno);
    }

    @Override
    public void voteAnswerEdit(VoteList voteList) throws Exception {
        voteDAO.voteAnswerEdit(voteList);
    }

    @Override
    public List<Vote> voteMyList(String sid) throws Exception {
        return voteDAO.voteMyList(sid);
    }

    @Override
    public VoteUser voteUserList(VoteUser voteUser) throws Exception {
        return voteDAO.voteUserList(voteUser);
    }

    @Override
    public int voteUserCnt(int lno) throws Exception {
        return voteDAO.voteUserCnt(lno);
    }

    @Override
    public void voteUserInsert(VoteUser voteUser) throws Exception {
        voteDAO.voteUserInsert(voteUser);
    }

    @Override
    public void voteUserDelete(VoteUser voteUser) throws Exception {
        voteDAO.voteUserDelete(voteUser);
    }

    @Override
    public List<VoteCountVo> voteCountList(int vno) throws Exception {
        return voteDAO.voteCountList(vno);
    }

    @Override
    public VoteCountVo voteMaxCountList(int vno) throws Exception {
        return voteDAO.voteMaxCountList(vno);
    }

    @Override
    public int voteCountCnt(int vno) throws Exception {
        return voteDAO.voteCountCnt(vno);
    }

}