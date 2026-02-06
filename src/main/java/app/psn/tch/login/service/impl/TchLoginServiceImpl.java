package app.psn.tch.login.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.com.SessionKeyEnum;
import com.base.utl.SessionUtil;

import app.psn.tch.login.mapper.TchLoginMapper;
import app.psn.tch.login.service.TchLoginService;
import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("loginService")
@RequiredArgsConstructor
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class TchLoginServiceImpl implements TchLoginService {

    private final TchLoginMapper loginMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public TchSessionVO sltMber(TchLoginVO loginVO) {
        return loginMapper.sltMber(loginVO);
    }

    @Override
    public TchSessionVO loginAx(TchLoginVO loginVO) {

        // 1. id로 회원정보 찾기
        TchSessionVO sessionVO = this.sltMber(loginVO);
        if (sessionVO == null) {
            throw new RuntimeException("일치하는 회원정보가 없습니다.");
        }

        // 2. pwd 검증
        if (!passwordEncoder.matches(loginVO.getPwd(), sessionVO.pwd())) {
            throw new RuntimeException("패스워드가 틀렸습니다.");
        }

        // 3. session set
        SessionUtil.setAttribute(SessionKeyEnum.STD_MBER_INFO.getKey(), sessionVO);

        return sessionVO;

    }
}