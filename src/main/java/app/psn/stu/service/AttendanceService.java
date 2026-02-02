package app.psn.stu.service;

import java.util.List;
import java.util.Map;

import app.idx.reg.vo.JoinMemberVO;
import app.psn.stu.vo.AttendanceVO;
import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

public interface AttendanceService {

	List<AttendanceVO> sltAttendanceList(); // 출석리스트

	boolean attendanceDo(); // 출석하기

}