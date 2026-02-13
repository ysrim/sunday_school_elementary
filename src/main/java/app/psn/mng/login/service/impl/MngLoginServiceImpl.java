package app.psn.mng.login.service.impl;

import app.psn.mng.login.mapper.MngLoginMapper;
import app.psn.mng.login.service.MngLoginService;
import app.psn.mng.login.vo.MngLoginVO;
import app.psn.mng.login.vo.MngSessionVO;
import com.base.enumm.com.SessionKeyEnum;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("mngLoginService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngLoginServiceImpl implements MngLoginService {

	private final MngLoginMapper mngLoginMapper;

	private final PasswordEncoder passwordEncoder;

	@Override
	public MngSessionVO sltMber(MngLoginVO loginVO) {

		return mngLoginMapper.sltMber(loginVO);

	}

	@Override
	public MngSessionVO loginAx(MngLoginVO loginVO) {

		// 1. id로 회원정보 찾기
		MngSessionVO sessionVO = this.sltMber(loginVO);
		if (sessionVO == null) {
			throw new RuntimeException("일치하는 관리자 정보가 없습니다.");
		}

		// 2. pwd 검증
		if (!passwordEncoder.matches(loginVO.getPwd(), sessionVO.pwd())) {
			throw new RuntimeException("패스워드가 틀렸습니다.");
		}

		// 3. session set
		SessionUtil.setAttribute(SessionKeyEnum.MNG_MBER_INFO.getKey(), sessionVO);

		return sessionVO;

	}

}