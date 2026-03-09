package app.psn.mng.home.service;

import java.util.List;
import java.util.Map;

public interface MngHomeService {

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