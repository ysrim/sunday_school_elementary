package app.psn.std.option.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.option.vo.QrCodeMemberInfoVO;

@Mapper
public interface QrCodeMapper {

    /**
     * QR코드 사용자 정보
     */
    QrCodeMemberInfoVO sltMberSn(String mberSn);

}