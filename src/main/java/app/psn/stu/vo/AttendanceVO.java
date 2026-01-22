package app.psn.stu.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AttendanceVO {

	private String date; // 일자 (ex. YYYY-MM-DD)

	private int week; // 주차 (ex. 1주차)

	private String attendance; // 출석여부

	private String today; // 접속한날짜가 투표가능한 날짜인지 판단

	private String attendanceToday; // 접속한날짜에 대해 출석여부 체크

}