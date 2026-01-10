package app.login.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.base.annotation.XssCheck;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginVO {

	@NotBlank(message = "아이디를 입력해주세요!")
	@Size(min = 5, message = "아이디는 5자 이상이어야 합니다.")
	@XssCheck
	private String mberId = ""; // 회원_아이디

	@NotBlank(message = "패스워드를 입력해주세요!")
	@Size(min = 4, message = "패스워드는 4자 이상이어야 합니다.")
	@XssCheck
	private String pwd = ""; // 패스워드

}