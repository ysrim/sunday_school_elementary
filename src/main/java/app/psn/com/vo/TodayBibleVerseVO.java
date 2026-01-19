package app.psn.com.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TodayBibleVerseVO {

	private int verseSn;

	private String bookName;

	private String chapter;

	private String verseStart;

	private String verseEnd;

	private String content;

}