package app.psn.std.opts.mapper;

import app.psn.std.opts.vo.StdQrCodeMemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdQrCodeMapper {

	/**
	 * QR코드 사용자 정보
	 */
	StdQrCodeMemberInfoVO sltMberSn(String mberSn);

}