package app.psn.std.option.service.impl;

import app.psn.std.option.mapper.StdQrCodeMapper;
import app.psn.std.option.service.StdQrCodeService;
import app.psn.std.option.vo.StdQrCodeMemberInfoVO;
import com.base.utl.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("qrCodeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdQrCodeServiceImpl implements StdQrCodeService {

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	private final StdQrCodeMapper stdQrCodeMapper;

	@Override
	public StdQrCodeMemberInfoVO sltMberSn(String encryptValue) {

		return stdQrCodeMapper.sltMberSn(CommonUtil.decrypt(encryptValue, secretKey));

	}

}