package app.psn.std.quest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.quest.vo.QuestListVO;
import app.psn.std.quest.vo.QuestPendingVO;

@Mapper
public interface QuestMapper {

    /**
     * 퀘스트 목록
     */
    List<QuestListVO> sltQuestList(String mberSn);

    /**
     * 퀘스트 수행 요청
     */
    Integer questDo(QuestPendingVO questVO);

    /**
     * 퀘스트 완료 체크
     */
    Integer questCompleteChk(QuestPendingVO questVO);

}