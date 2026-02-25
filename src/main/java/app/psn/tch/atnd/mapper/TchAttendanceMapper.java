package app.psn.tch.atnd.mapper;

import app.psn.tch.atnd.vo.AtndApprovalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TchAttendanceMapper {

	/**
	 * 출석 승인 요청 목록
	 */
	List<AtndApprovalVO> sltReqAtndList(@Param("guildSn") Integer guildSn, @Param("reqDate") String reqDate);

}