package app.join.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {

	private String mberSn = ""; // 회원_일련번호[pk]

	private String mberId = ""; // 회원_아이디

	private String mberNm = ""; // 회원_이름

	private String useAt = ""; // 사용_여부 (Y/N)

	private String rm = ""; // 비고

	private String creatDe = ""; // 생성_일자

	private String udtDe = ""; // 수정_일자

}