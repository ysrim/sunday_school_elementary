package app.psn.stu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;

import app.psn.stu.mapper.AttendanceMapper;
import app.psn.stu.service.AttendanceService;
import app.psn.stu.service.QuestService;
import app.psn.stu.vo.AttendanceVO;
import app.psn.stu.vo.QuestPendingVO;
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
		return attendanceMapper.sltAttendanceList(SessionUtil.getMberInfo().getMberSn());
	}

	@Override
	public boolean attendanceDo() {
		QuestPendingVO vo = new QuestPendingVO();
		vo.setMberSn(SessionUtil.getMberInfo().getMberSn());
		vo.setQuestSn(2);
		return questService.questDo(vo);
	}

}