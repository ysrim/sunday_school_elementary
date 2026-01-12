package app.idx.reg.vo;

import org.hibernate.validator.constraints.Range;

import com.base.annotation.XssCheck;
import com.base.enumm.MberGrdEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JoinMemberVO {

	private String mberSn = ""; // 회원_일련번호

	@NotEmpty(message = "아이디를 입력해주세요!")
	@Size(min = 5, message = "아이디는 5자 이상이어야 합니다.")
	@XssCheck
	private String mberId = ""; // 회원_아이디

	@NotEmpty(message = "이름을 입력해주세요!")
	@Size(min = 2, message = "이름은 2자 이상이어야 합니다.")
	private String mberNm = ""; // 회원_이름

	@NotEmpty(message = "패스워드를 입력해주세요!")
	@Size(min = 4, message = "패스워드는 4자 이상이어야 합니다.")
	private String pwd = ""; // 패스워드

	@NotEmpty(message = "닉네임을 입력해주세요!")
	@Size(min = 3, message = "닉네임은 3자 이상이어야 합니다.")
	private String ncnm = ""; // 닉네임

	@NotEmpty(message = "캐릭터를 선택해주세요!")
	private String occpCode = ""; // 직업_코드

	@NotEmpty(message = "학년 입력해주세요!")
	@Range(min = 4, max = 6, message = "학년은 4학년부터 6학년까지만 선택 가능합니다.")
	private String grade = ""; // 학년

	@NotEmpty(message = "반을 입력해주세요!")
	@Range(min = 1, max = 10, message = "반은 1에서 99까지 선택 가능합니다.")
	private String cls = ""; // 반

	private String gradeCode = MberGrdEnum.STD.getCode();

	private String guildSn = ""; // 길드_시퀀스

}