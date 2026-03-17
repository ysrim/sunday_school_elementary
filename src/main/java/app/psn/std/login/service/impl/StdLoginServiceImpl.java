package app.psn.std.login.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.enumm.com.SessionKeyEnum;
import com.base.utl.JwtTokenProvider;
import com.base.utl.SessionUtil;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.mapper.StdLoginMapper;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdLoginService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdLoginServiceImpl implements StdLoginService {

	private final StdLoginMapper stdLoginMapper;

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public StdSessionVO sltMber(LoginVO loginVO) {
		return stdLoginMapper.sltMber(loginVO);
	}

	@Override
	public StdSessionVO loginAx(LoginVO loginVO) {

		// 1. id로 회원정보 찾기
		StdSessionVO stdSessionVO = this.sltMber(loginVO);
		if (stdSessionVO == null) {
			throw new RuntimeException("일치하는 회원정보가 없습니다.");
		}

		// 2. pwd 검증
		if (!passwordEncoder.matches(loginVO.getPwd(), stdSessionVO.pwd())) {
			throw new RuntimeException("패스워드가 틀렸습니다.");
		}

		// 3. session set
		SessionUtil.setAttribute(SessionKeyEnum.STD_MBER_INFO.getKey(), stdSessionVO);

		// 4. 자동 로그인을 위한 Refresh Token 발급
		if (loginVO.isAutoLogin()) {

			String refreshToken = jwtTokenProvider.createRefreshToken(loginVO.getMberId());
			// 쿠키에 리프레쉬토큰 저장
			this.saveRefreshTokenInCookie(refreshToken);
			// DB에도 리프레쉬토큰 저장
			this.regRefreshToken(stdSessionVO.mberId(), refreshToken);

		}

		return stdSessionVO;

	}

	@Override
	public boolean refreshTokenValid(String refreshToken) {

		// 1. 토큰이 유효 확인
		if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {

			// 1. id로 회원정보 찾기
			StdSessionVO stdSessionVO = this.sltTokenMber(refreshToken);

			if (stdSessionVO == null || !stdSessionVO.mberId().equals(jwtTokenProvider.getUserId(refreshToken))) {
				return false;
			}

			// 2. session set
			SessionUtil.setAttribute(SessionKeyEnum.STD_MBER_INFO.getKey(), stdSessionVO);

			return true;

		}

		return false;

	}

	@Override
	public StdSessionVO sltTokenMber(String refreshToken) {
		return stdLoginMapper.sltTokenMber(refreshToken);
	}

	@Override
	public void regRefreshToken(String mberId, String refreshToken) {
		stdLoginMapper.regRefreshToken(mberId, refreshToken);
	}

	private void saveRefreshTokenInCookie(String refreshToken) {

		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		if (attributes != null) {

			HttpServletResponse response = attributes.getResponse();
			ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken).httpOnly(true).path("/").maxAge(60 * 60 * 24 * 365 * 100) // 100년
				.sameSite("Lax") // CSRF 방어
				.build();
			response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

		}

	}

}