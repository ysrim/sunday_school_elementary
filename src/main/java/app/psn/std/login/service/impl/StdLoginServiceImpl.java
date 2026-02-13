package app.psn.std.login.service.impl;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.mapper.StdLoginMapper;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
import com.base.enumm.com.SessionKeyEnum;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("stdLoginService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdLoginServiceImpl implements StdLoginService {

	private final StdLoginMapper stdLoginMapper;

	private final PasswordEncoder passwordEncoder;

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

		return stdSessionVO;

	}
}