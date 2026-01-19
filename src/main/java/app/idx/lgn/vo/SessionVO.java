package app.idx.lgn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SessionVO {

	private int mberSn; // 회원_일련번호

	private String mberId; // 회원_아이디

	private String mberNm; // 회원_이름

	private String pwd; // 패스워드

	private String gradeCode; // 등급_코드

	private String ncnm; // 닉네임

	private String occpCode; // 직업_코드

	private int exp; // 경험치

	private int level; // 캐릭터_래벨

	private String guildNm; // 길드_이름

	private String sexdstn; // 성별

	private int guildSn; // 길드_일련번호

}