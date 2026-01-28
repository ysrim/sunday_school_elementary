package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.RewardVO;
import app.psn.com.vo.TodayBibleVerseVO;

@Mapper
public interface RewardMapper {

	int updateReward(RewardVO reward); // 아바타 정보 리워드 정보 갱신

}