package app.psn.stu.vo;

public record AttendanceVO( // 출석
							String date,       // 일자 (ex. YYYY-MM-DD)
							Integer week,      // 주차 (ex. 1주차)
							String status   // 출석상태('PENDING','APPROVED','REJECTED')
) {
	// 필요한 경우 컴팩트 생성자를 통해 유효성 검증 로직을 넣을 수 있습니다.
}