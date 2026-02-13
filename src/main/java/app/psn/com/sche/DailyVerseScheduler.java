package app.psn.com.sche;

import app.psn.com.mapper.BibleVerseMapper;
import com.base.enumm.std.CacheKeys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service("dailyVerseScheduler")
@RequiredArgsConstructor
public class DailyVerseScheduler {

	private final BibleVerseMapper bibleVerseMapper;

	/**
	 * 매일 자정 (00:00:00) 실행
	 * 표현식: 초 분 시 일 월 요일
	 */
	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = CacheKeys.TodayBibleVerseEnum, allEntries = true)
	@Transactional
	public void rotateDailyVerse() {

		LocalDate today = LocalDate.now();
		log.info("[DailyVerseScheduler] {} 오늘의 말씀 로테이션 작업 시작", today);

		try {

			// 1. 오늘 날짜(SCHEDULE_DATE)에 이미 할당된 성구가 있는지 확인 (관리자 수동 지정 등)
			Integer count = bibleVerseMapper.sltVerseByDateCnt(today);

			if (count > 0) {
				log.warn("오늘 지정된 성구가 이미 존재합니다. (SKIP)");
				return;
			}

			// 2. 없다면 랜덤으로 하나 선택하여 INSERT
			Integer result = bibleVerseMapper.insRandomVerseForDate(today);
			log.warn(result > 0 ? "새로운 오늘의 말씀이 등록되었습니다." : "등록 가능한 성구 데이터가 부족합니다 (DB 확인 필요).");

		} catch (Exception e) {
			log.error("스케줄러 실행 중 오류 발생: ", e);
			// 필요 시 관리자에게 알림(Slack, Email) 전송 로직 추가
		}

	}
}