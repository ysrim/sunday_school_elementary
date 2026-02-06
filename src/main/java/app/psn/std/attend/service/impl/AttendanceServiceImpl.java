package app.psn.std.attend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.std.attend.mapper.AttendanceMapper;
import app.psn.std.attend.service.AttendanceService;
import app.psn.std.quest.service.QuestService;
import app.psn.std.attend.vo.AttendanceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("attendanceService")
@RequiredArgsConstructor
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper attendanceMapper;

	private final QuestService questService;

	@Override
	public List<AttendanceVO> sltAttendanceList() {

		return attendanceMapper.sltAttendanceList(SessionUtil.getMberInfo().mberSn());

	}

	@Override
	public Integer sltAttendanceContinueCount() {

		return attendanceMapper.sltAttendanceContinueCount(SessionUtil.getMberInfo().mberSn());

	}

	@Override
	public void attendanceDo() {

		questService.questDo(StringUtil.setQuestPendingVO(2));

	}

}