package app.psn.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.stu.vo.AttendanceVO;

@Mapper
public interface AttendanceMapper {

	List<AttendanceVO> sltAttendanceList(Integer mberSn); // 출석 목록

}