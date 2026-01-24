package app.idx.qrc.service;

import app.idx.qrc.vo.QrCodeMemberInfoVO;

public interface QrCodeService {

	QrCodeMemberInfoVO sltMberSn(String encryptValue);

}