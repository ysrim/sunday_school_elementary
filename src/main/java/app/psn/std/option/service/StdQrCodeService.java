package app.psn.std.option.service;

import app.psn.std.option.vo.StdQrCodeMemberInfoVO;

public interface StdQrCodeService {

	/**
	 * QR코드 사용자 정보
	 */
	StdQrCodeMemberInfoVO sltMberSn(String encryptValue);

}