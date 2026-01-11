package app.idx.lgn.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.base.enumm.SessionKeyEnum;
import com.base.utl.SessionUtil;

import app.idx.lgn.service.LoginService;
import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	@Resource(name = "loginDAO")
	private LoginDAO loginDAO;

	@PostConstruct
	public void serviceInit() {
		// do something
	}

	private SessionVO sltMber(LoginVO loginVO) {
		return loginDAO.sltMber(loginVO);
	}

	@Override
	public SessionVO loginAx(LoginVO loginVO) {

		// 1. id로 회원정보 찾기
		SessionVO sessionVO = this.sltMber(loginVO);
		if (sessionVO == null) {
			throw new RuntimeException("일치하는 회원정보가 없습니다.");
		}

		// 2. pwd 검증
		if (!loginVO.getPwd().equals(sessionVO.getPwd())) {
			throw new RuntimeException("패스워드가 틀렸습니다.");
		}

		// 3. session set
		SessionUtil.setAttribute(SessionKeyEnum.MBERINFO.name(), sessionVO);

		return sessionVO;

	}
}