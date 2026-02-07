package app.psn.tch.attend.mapper;

import app.psn.tch.attend.vo.StdAttendanceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TchAttendanceMapper {

	/**
	 * 당월 주일 출석 목록
	 */
	List<StdAttendanceVO> sltAttendanceList(Integer mberSn); // 출석 목록

	/**
	 * 연속출석일 수
	 */
	Integer sltAttendanceContinueCount(Integer mberSn);

}