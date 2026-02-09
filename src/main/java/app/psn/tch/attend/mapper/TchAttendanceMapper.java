package app.psn.tch.attend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.tch.attend.vo.ReqAttendanceVO;

@Mapper
public interface TchAttendanceMapper {

	/**
	 * 출석 요청 목록
	 */
	List<ReqAttendanceVO> sltReqAtndList(@Param("guildSn") Integer guildSn, @Param("reqDate") String reqDate);

}