package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;

@Mapper
public interface DomainMapper {

	QuestVO sltQuest(int questSn);

	AvatarVO sltAvatar(int mberSn);

}