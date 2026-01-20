package app.psn.stu.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HomeGuildInfoVO {

	private int guildSn; // 길드_일련번호

	private String guildNm; // 길드_이름

	private int guildLevel; // 길드_레벨

	private int grade; // 학년

	private int cla; // 반

	private String guildImage; // 길드_이미지

}