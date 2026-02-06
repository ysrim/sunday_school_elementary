package app.psn.std.home.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StdHomeGuildListVO {

	private String mberSn; // 회원_일련번호

	private String mberId; // 회원_아이디

	private String mberNm; // 회원_이름

	private String sexdstn;

	private String ncnm; // 닉네임

	private String occpCode; // 직업_코드

	private String occpNm; // 직업_명

	private Integer level; // 캐릭터_래벨

	private boolean isAccess; // 접속중 여부

	private Integer point; // 캐릭터_래벨

}