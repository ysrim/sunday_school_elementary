package app.psn.std.attend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.std.attend.mapper.StdAttendanceMapper;
import app.psn.std.attend.service.StdAttendanceService;
import app.psn.std.quest.service.StdQuestService;
import app.psn.std.attend.vo.StdAttendanceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdAttendanceService")
@RequiredArgsConstructor
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class StdAttendanceServiceImpl implements StdAttendanceService {

	private final StdAttendanceMapper stdAttendanceMapper;

	private final StdQuestService stdQuestService;

	@Override
	public List<StdAttendanceVO> sltAttendanceList() {

		return stdAttendanceMapper.sltAttendanceList(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public Integer sltAttendanceContinueCount() {

		return stdAttendanceMapper.sltAttendanceContinueCount(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public void attendanceDo() {

		stdQuestService.questDo(StringUtil.setQuestPendingVO(2));

	}

}