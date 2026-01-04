package app.join.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvatarVO {

	private String mberSn = ""; // 회원_일련번호 [PK][MBER_INFO FK]

	private String ncnm = ""; // 닉네임

	private String level = ""; // 레벨

	private String exp = ""; // 경험치

	private String occpCode = ""; // 직업_코드

	private String creatDe = ""; // 생성_일자

	private String udtDe = ""; // 수정_일자

}