package app.psn.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.stu.vo.QuestListVO;
import app.psn.stu.vo.QuestVO;

@Mapper
public interface QuestMapper {

	List<QuestListVO> sltQuestList(String mberSn);

	int questDo(QuestVO questVO);

}