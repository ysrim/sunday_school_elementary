package app.idx.qrc.service.impl;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.idx.qrc.mapper.QrCodeMapper;
import app.idx.qrc.service.QrCodeService;
import app.idx.qrc.vo.QrCodeMemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("qrCodeService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class QrCodeServiceImpl implements QrCodeService {

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	private final QrCodeMapper qrCodeMapper;

	@Override
	public QrCodeMemberInfoVO sltMberSn(String encryptValue) {
		String mberSn = "";
		try {
			mberSn = StringUtil.decrypt(encryptValue, secretKey);
		} catch (Exception e) {
			throw new RuntimeException("잘못된 정보입니다.");
		}
		return qrCodeMapper.sltMberSn(mberSn);
	}

}