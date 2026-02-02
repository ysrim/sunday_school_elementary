package app.psn.stu.service.impl;

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
@RequiredArgsConstructor
@Transactional(readOnly = true)
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