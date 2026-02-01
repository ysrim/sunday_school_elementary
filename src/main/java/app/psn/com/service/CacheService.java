package app.psn.com.service;

import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;

public interface CacheService {

	int sltPont(int mberSn); // 캐쉬생성 달란트

	void evictPont(int mberSn); // 캐쉬갱신 달란트

	int sltLevel(int mberSn); // 캐쉬생성 레벨

	void evictLevel(int mberSn); // 캐쉬갱신 레벨

	AvatarLevelVO sltExp(int mberSn); // 캐쉬생성 경험치

	void evictExp(int mberSn); // 캐쉬갱신 경험치

	TodayBibleVerseVO sltTodayBibleVerse(); // 오늘의 말씀

	boolean addOnlineMber(String mberId);

	void delOnlineMber(String mberId);

	boolean checkKeyExists(String cacheName, String key);

	int checkKeySize(String cacheName);

}