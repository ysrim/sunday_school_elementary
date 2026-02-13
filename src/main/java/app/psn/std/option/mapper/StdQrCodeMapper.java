package app.psn.std.option.mapper;

import app.psn.std.option.vo.StdQrCodeMemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdQrCodeMapper {

	/**
	 * QR코드 사용자 정보
	 */
	StdQrCodeMemberInfoVO sltMberSn(String mberSn);

}