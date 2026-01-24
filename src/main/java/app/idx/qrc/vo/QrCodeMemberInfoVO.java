package app.idx.qrc.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QrCodeMemberInfoVO {

	private String mberNm; // 회원_이름

	private String gradeCode; // 등급_코드

	private String ncnm; // 닉네임

	private String occpCode; // 직업_코드

	private int exp; // 경험치

	private int level; // 캐릭터_래벨

	private String guildNm; // 길드_이름

	private String sexdstn; // 성별

	private String grade; // 성별

	private String cla; // 성별

	private String occpNm; // 직업

	private String point; // 달란트

}