package app.psn.std.option.service.impl;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
import app.psn.std.option.mapper.StdOptsMapper;
import app.psn.std.option.service.StdOptsService;
import app.psn.std.option.vo.StdRewardHistoryVO;
import com.base.utl.CommonUtil;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service("optionService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdOptsServiceImpl implements StdOptsService {

	private final StdLoginService stdLoginService;

	private final PasswordEncoder passwordEncoder;

	private final StdOptsMapper stdOptsMapper;

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	@Override
	public String QrCodeStr() {

		try {
			StdSessionVO stdSessionVo = SessionUtil.getStdMberInfo();
			return CommonUtil.encrypt(stdSessionVo.mberSn() + "", secretKey);
		} catch (Exception e) {
			throw new RuntimeException("QR코드 생성중 에러가 발생했습니다.");
		}

	}

	@Override
	public void pwChg(String currentPw, String newPw) {

		LoginVO loginVO = new LoginVO();
		loginVO.setMberId(SessionUtil.getStdMberInfo().mberId());
		loginVO.setPwd(currentPw);

		// 1. id로 회원정보 찾기
		StdSessionVO stdSessionVO = stdLoginService.sltMber(loginVO);
		if (stdSessionVO == null) {
			throw new RuntimeException("일치하는 회원정보가 없습니다.");
		}

		// 2. pwd 검증
		if (!passwordEncoder.matches(loginVO.getPwd(), stdSessionVO.pwd())) {
			throw new RuntimeException("기존 패스워드가 틀렸습니다.");
		}

		// 3. 패스워드 업데이트
		stdOptsMapper.pwChg(SessionUtil.getStdMberInfo().mberSn(), passwordEncoder.encode(newPw));

	}

	@Override
	public List<StdRewardHistoryVO> sltRewardHistory() {

		return stdOptsMapper.sltRewardHistory(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public HashMap<String, Integer> sltWeekStatics() {

		return stdOptsMapper.sltWeekStatics(SessionUtil.getStdMberInfo().mberSn());

	}

}