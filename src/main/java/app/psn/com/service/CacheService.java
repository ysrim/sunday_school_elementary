package app.psn.com.service;

import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;

public interface CacheService {

	/**
	 * 캐쉬생성 - 달란트
	 */
	Integer sltPont(Integer mberSn);

	/**
	 * 캐쉬갱신 -  달란트
	 */
	void evictPont(Integer mberSn);

	/**
	 * 캐쉬생성 - 레벨
	 */
	Integer sltLevel(Integer mberSn);

	/**
	 * 캐쉬갱신 - 레벨
	 */
	void evictLevel(Integer mberSn);

	/**
	 * 캐쉬생성 - 경험치
	 */
	AvatarLevelVO sltExp(Integer mberSn);

	/**
	 * 캐쉬갱신 - 경험치
	 */
	void evictExp(Integer mberSn);

	/**
	 * 캐쉬생성 - 오늘의 말씀
	 */
	TodayBibleVerseVO sltTodayBibleVerse();

	/**
	 * 캐쉬생성 - 온라인 맴버 추가
	 */
	void addOnlineMber(String mberId);

	/**
	 * 캐쉬 갱신
	 */
	boolean checkKeyExists(String cacheName, String key);

}