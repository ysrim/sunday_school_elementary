package app.psn.std.opts.service;

import app.psn.std.opts.vo.StdQrCodeMemberInfoVO;

public interface StdQrCodeService {

	/**
	 * QR코드 사용자 정보
	 */
	StdQrCodeMemberInfoVO sltMberSn(String encryptValue);

}