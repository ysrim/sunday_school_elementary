package app.psn.com.service;

import app.psn.com.vo.TodayBibleVerseVO;

public interface CacheService {

	int sltPont(int mberSn); // 캐쉬데이터 달란트

	int sltLv(int mberSn); // 캐쉬데이터 레벨

	int sltExp(int mberSn); // 캐쉬데이터 경험치

	TodayBibleVerseVO sltTodayBibleVerse(); // 오늘의 말씀

	boolean addOnlineMber(String mberId);

	void delOnlineMber(String mberId);

	boolean checkKeyExists(String cacheName, String key);

}