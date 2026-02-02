package app.psn.stu.service;

import java.util.List;

import app.psn.stu.vo.AttendanceVO;

public interface AttendanceService {

	List<AttendanceVO> sltAttendanceList(); // 출석리스트

	boolean attendanceDo(); // 출석하기

}