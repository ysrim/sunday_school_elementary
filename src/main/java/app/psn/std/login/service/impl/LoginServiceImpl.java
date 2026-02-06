package app.psn.std.login.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.std.SessionKeyEnum;
import com.base.utl.SessionUtil;

import app.psn.std.login.mapper.LoginMapper;
import app.psn.std.login.service.LoginService;
import app.psn.std.login.vo.LoginVO;
import app.psn.std.login.vo.SessionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("loginService")
@RequiredArgsConstructor
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SessionVO sltMber(LoginVO loginVO) {
        return loginMapper.sltMber(loginVO);
    }

    @Override
    public SessionVO loginAx(LoginVO loginVO) {

        // 1. id로 회원정보 찾기
        SessionVO sessionVO = this.sltMber(loginVO);
        if (sessionVO == null) {
            throw new RuntimeException("일치하는 회원정보가 없습니다.");
        }

        // 2. pwd 검증
        if (!passwordEncoder.matches(loginVO.getPwd(), sessionVO.getPwd())) {
            throw new RuntimeException("패스워드가 틀렸습니다.");
        }

        // 3. session set
        SessionUtil.setAttribute(SessionKeyEnum.MBER_INFO.getKey(), sessionVO);

        return sessionVO;

    }
}