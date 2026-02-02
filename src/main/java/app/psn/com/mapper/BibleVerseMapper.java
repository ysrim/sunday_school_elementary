package app.psn.com.mapper;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BibleVerseMapper {

	Integer sltVerseByDateCnt(LocalDate date); // 오늘 날짜 데이터 존재 여부 확인

	Integer insRandomVerseForDate(LocalDate date); // 랜덤 성구 배정

}