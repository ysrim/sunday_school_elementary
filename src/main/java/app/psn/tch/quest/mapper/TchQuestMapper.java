package app.psn.tch.quest.mapper;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import app.psn.tch.quest.vo.StdQuestListVO;
import app.psn.tch.quest.vo.StdQuestPendingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TchQuestMapper {

    /**
     * 퀘스트 목록
     */
    List<StdQuestListVO> sltQuestList(String guildSn);

    /**
     * 퀘스트 승인
     */
    void questProc(ReqQuestProcVO reqQuestProcVO);

}