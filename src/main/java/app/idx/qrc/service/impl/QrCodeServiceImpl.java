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
@RequiredArgsConstructor
@Transactional(readOnly = true)
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