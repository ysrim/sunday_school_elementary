package app.psn.std.home.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StdHomeGildInfoVO {

	private Integer guildSn; // 길드_일련번호

	private String guildNm; // 길드_이름

	private Integer guildLevel; // 길드_레벨

	private Integer grade; // 학년

	private Integer cla; // 반

	private String guildImage; // 길드_이미지

	private String rm; // 길드 비고

}