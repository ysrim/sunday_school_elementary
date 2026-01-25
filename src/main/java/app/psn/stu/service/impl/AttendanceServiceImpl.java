package app.psn.stu.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.CacheKeys;

import app.psn.com.service.CacheService;
import app.psn.stu.mapper.AttendanceMapper;
import app.psn.stu.mapper.HomeMapper;
import app.psn.stu.service.AttendanceService;
import app.psn.stu.service.HomeService;
import app.psn.stu.vo.AttendanceVO;
import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("attendanceService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper attendanceMapper;

	@Override
	public Map<String, Object> sltAttendanceList(int mberSn) {
		List<AttendanceVO> attendanceList = attendanceMapper.sltAttendanceList(mberSn);
		String attendanceToday = attendanceList.stream()
			.filter(vo -> "today".equals(vo.getToday()))
			.map(AttendanceVO::getAttendanceToday)
			.findFirst()
			.orElse("N"); // 오늘 날짜 데이터가 없을 경우 기본값
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("attendanceList", attendanceList);
		resultMap.put("attendanceToday", attendanceToday);
		return resultMap;
	}

	@Override
	public boolean attendanceDo(int mberSn) {
		int cnt = attendanceMapper.insAttendanceDo(mberSn);
		log.info("insert count: {}", cnt);
		if (cnt < 1) {
			// 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("오류가 발생했습니다.");
		}
		return true;
	}
}