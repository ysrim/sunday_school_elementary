package app.psn.stu.service;

import java.util.List;
import java.util.Map;

import app.idx.reg.vo.JoinMemberVO;
import app.psn.stu.vo.AttendanceVO;
import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

public interface AttendanceService {

	Map<String, Object> sltAttendanceList(int mberSn); // 출석리스트

	boolean attendanceDo(int mberSn); // 출석하기

}