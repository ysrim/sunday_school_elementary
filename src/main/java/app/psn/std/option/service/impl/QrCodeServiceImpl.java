package app.psn.std.option.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.StringUtil;

import app.psn.std.option.mapper.QrCodeMapper;
import app.psn.std.option.service.QrCodeService;
import app.psn.std.option.vo.QrCodeMemberInfoVO;
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

        return qrCodeMapper.sltMberSn(StringUtil.decrypt(encryptValue, secretKey));

    }

}