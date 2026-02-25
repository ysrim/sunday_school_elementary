package app.psn.tch.atnd.service.impl;

import app.psn.tch.atnd.mapper.TchAttendanceMapper;
import app.psn.tch.atnd.service.TchAttendanceService;
import app.psn.tch.atnd.vo.AtndApprovalVO;
import app.psn.tch.qest.service.TchQestService;
import app.psn.tch.qest.vo.ReqQestProcVO;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("tchAttendanceService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchAttendanceServiceImpl implements TchAttendanceService {

	private final TchAttendanceMapper tchAttendanceMapper;

	private final TchQestService tchQestService;

	@Override
	public List<AtndApprovalVO> sltReqAtndList(String reqDate) {

		return tchAttendanceMapper.sltReqAtndList(SessionUtil.getTchMberInfo().guildSn(), reqDate);

	}

	@Override
	public void atndChk(ReqQestProcVO reqQestProcVO) {

		tchQestService.questProc(reqQestProcVO);

	}

}