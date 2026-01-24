package app.idx.qrc.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.idx.qrc.vo.QrCodeMemberInfoVO;

@Mapper
public interface QrCodeMapper {

	QrCodeMemberInfoVO sltMberSn(String mberSn);

}