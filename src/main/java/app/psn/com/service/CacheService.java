package app.psn.com.service;

import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;

public interface CacheService {

	Integer sltPont(Integer mberSn); // 캐쉬생성 달란트

	void evictPont(Integer mberSn); // 캐쉬갱신 달란트

	Integer sltLevel(Integer mberSn); // 캐쉬생성 레벨

	void evictLevel(Integer mberSn); // 캐쉬갱신 레벨

	AvatarLevelVO sltExp(Integer mberSn); // 캐쉬생성 경험치

	void evictExp(Integer mberSn); // 캐쉬갱신 경험치

	TodayBibleVerseVO sltTodayBibleVerse(); // 오늘의 말씀

	boolean addOnlineMber(String mberId);

	boolean checkKeyExists(String cacheName, String key);

}