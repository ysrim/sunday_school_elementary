package app.psn.com.mapper;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BibleVerseMapper {

	int sltVerseByDateCnt(LocalDate date); // 오늘 날짜 데이터 존재 여부 확인

	int insRandomVerseForDate(LocalDate date); // 랜덤 성구 배정

}