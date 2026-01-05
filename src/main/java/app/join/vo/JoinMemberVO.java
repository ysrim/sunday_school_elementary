package app.join.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinMemberVO {

	// 회원정보
	@NotBlank(message = "아이디를 입력해주세요!")
	@Size(min = 5, message = "아이디는 5자 이상이어야 합니다.")
	private String mberId = ""; // 회원_아이디

	@NotBlank(message = "이름을 입력해주세요!")
	private String mberNm = ""; // 회원_이름

	@NotBlank(message = "패스워드를 입력해주세요!")
	private String pwd = ""; // 패스워드

	// 아바타 정보
	private String ncnm = ""; // 닉네임

	private String occpCode = ""; // 직업_코드

}