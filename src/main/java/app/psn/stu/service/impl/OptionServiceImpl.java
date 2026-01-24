package app.psn.stu.service.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.idx.lgn.vo.SessionVO;
import app.psn.stu.mapper.OptionMapper;
import app.psn.stu.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("optionService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class OptionServiceImpl implements OptionService {

	private final OptionMapper optionMapper;

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	@Override
	public String QrCodeStr() {
		try {
			SessionVO sessionVo = SessionUtil.getMberInfo();
			return StringUtil.encrypt(sessionVo.getMberSn() + "", secretKey);
		} catch (Exception e) {
			throw new RuntimeException("QR코드 생성중 에러가 발생했습니다.");
		}
	}
}