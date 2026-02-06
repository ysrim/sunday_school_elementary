package app.psn.std.option.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.std.login.service.LoginService;
import app.psn.std.login.vo.LoginVO;
import app.psn.std.login.vo.SessionVO;
import app.psn.std.option.mapper.OptionMapper;
import app.psn.std.option.service.OptionService;
import app.psn.std.option.vo.RewardHistoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("optionService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionServiceImpl implements OptionService {

	private final LoginService loginService;

	private final PasswordEncoder passwordEncoder;

	private final OptionMapper optionMapper;

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	@Override
	public String QrCodeStr() {

		try {
			SessionVO sessionVo = SessionUtil.getMberInfo();
			return StringUtil.encrypt(sessionVo.mberSn() + "", secretKey);
		} catch (Exception e) {
			throw new RuntimeException("QR코드 생성중 에러가 발생했습니다.");
		}

	}

	@Override
	public boolean pwChg(String currentPw, String newPw) {

		LoginVO loginVO = new LoginVO();
		loginVO.setMberId(SessionUtil.getMberInfo().mberId());
		loginVO.setPwd(currentPw);

		// 1. id로 회원정보 찾기
		SessionVO sessionVO = loginService.sltMber(loginVO);
		if (sessionVO == null) {
			throw new RuntimeException("일치하는 회원정보가 없습니다.");
		}

		// 2. pwd 검증
		if (!passwordEncoder.matches(loginVO.getPwd(), sessionVO.pwd())) {
			throw new RuntimeException("기존 패스워드가 틀렸습니다.");
		}

		// 3. 패스워드 업데이트
		optionMapper.pwChg(SessionUtil.getMberInfo().mberSn(), passwordEncoder.encode(newPw));

		return true;

	}

	@Override
	public List<RewardHistoryVO> sltRewardHistory() {

		return optionMapper.sltRewardHistory(SessionUtil.getMberInfo().mberSn());

	}

	@Override
	public HashMap<String, Integer> sltWeekStatics() {

		return optionMapper.sltWeekStatics(SessionUtil.getMberInfo().mberSn());

	}

}