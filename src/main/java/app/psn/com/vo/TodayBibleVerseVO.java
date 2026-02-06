package app.psn.com.vo;

/**
 * 오늘의 성구 정보
 */
public record TodayBibleVerseVO( //
								 Integer verseSn,   // 성구_순번
								 String bookName,   // 성경책
								 String chapter,    // 장
								 String verseStart, // 시작절
								 String verseEnd,   // 끝절
								 String words,      // 말씀
								 String opinion,    // 부가정보
								 Integer seq        // 순번
) {
}