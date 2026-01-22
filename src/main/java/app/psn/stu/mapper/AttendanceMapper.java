package app.psn.stu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import app.psn.stu.vo.AttendanceVO;
import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

@Mapper
public interface AttendanceMapper {

	List<AttendanceVO> sltAttendanceList(int mberSn); // 출석 목록

	int insAttendanceDo(int mberSn); //출석하기

}