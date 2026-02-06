package app.psn.std.option.service;

import app.psn.std.option.vo.QrCodeMemberInfoVO;

public interface QrCodeService {

    /**
     * QR코드 사용자 정보
     */
    QrCodeMemberInfoVO sltMberSn(String encryptValue);

}