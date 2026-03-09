package app.psn.mng.home.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MngHomeMapper {

	Map<String, String> getAtndCnt();

	Map<String, String> getQestCnt();

	Map<String, String> getWeekPoint();

	Map<String, String> getSumPoint();

	List<Map<String, String>> getAtndList();

	List<Map<String, String>> getAvatarLevel();

	List<Map<String, String>> getTimeVisit();

	List<Map<String, String>> getGradeClaAtndCnt();

	List<Map<String, String>> getExcellentMber();

}