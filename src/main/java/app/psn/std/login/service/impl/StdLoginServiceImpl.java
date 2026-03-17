package app.psn.std.login.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.enumm.com.SessionKeyEnum;
import com.base.utl.CommonUtil;
import com.base.utl.JwtTokenProvider;
import com.base.utl.SessionUtil;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.mapper.StdLoginMapper;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
import jakarta.servlet.http.HttpServletRequest;
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

		// 2. Access Token 생성
		String accessToken = jwtTokenProvider.createAccessToken(loginVO.getMberId());

		// 3. 자동 로그인 체크 시 Refresh Token 발급
		if (loginVO.isAutoLogin()) {

			String refreshToken = jwtTokenProvider.createRefreshToken(loginVO.getMberId());

			this.saveRefreshTokenInCookie(refreshToken);

			// DB에도 저장 (나중에 로그아웃 시키기 위함)
			// tokenMapper.saveToken(req.getMberId(), refreshToken);

		}

		return stdSessionVO;

	}

	@Override
	public String refreshToken(HttpServletRequest request) {

		// 1. 쿠키에서 Refresh Token 추출
		String refreshToken = CommonUtil.getCookieValue(request, "refreshToken");

		// 2. 토큰이 유효하고 DB에 있는지 확인
		if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {

			LoginVO loginVO = new LoginVO();
			loginVO.setMberId(jwtTokenProvider.getUserId(refreshToken));

			// 1. id로 회원정보 찾기
			StdSessionVO stdSessionVO = this.sltMber(loginVO);
			if (stdSessionVO == null) {
				throw new RuntimeException("일치하는 회원정보가 없습니다.");
			}

			// 2. session set
			SessionUtil.setAttribute(SessionKeyEnum.STD_MBER_INFO.getKey(), stdSessionVO);

			// 3. Access Token 생성
			String newAccessToken = jwtTokenProvider.createAccessToken(loginVO.getMberId());

			return newAccessToken;

		}

		return null;

	}

	private void saveRefreshTokenInCookie(String refreshToken) {

		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		if (attributes != null) {

			HttpServletResponse response = attributes.getResponse();
			ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
				.httpOnly(true)
				.path("/")
				.maxAge(60 * 60 * 24 * 365 * 100) // 100년
				.sameSite("Lax") // CSRF 방어
				.build();
			response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

		}

	}

}