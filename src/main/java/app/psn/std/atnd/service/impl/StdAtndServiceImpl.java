package app.psn.std.atnd.service.impl;

import app.psn.std.atnd.mapper.StdAtndMapper;
import app.psn.std.atnd.service.StdAtndService;
import app.psn.std.atnd.vo.StdAtndVO;
import app.psn.std.qest.service.StdQestService;
import com.base.utl.CommonUtil;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("stdAtndService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdAtndServiceImpl implements StdAtndService {

	private final StdAtndMapper stdAtndMapper;

	private final StdQestService stdQestService;

	@Override
	public List<StdAtndVO> sltAtndList() {

		return stdAtndMapper.sltAtndList(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public Integer sltCtnuAtndCnt() {

		return stdAtndMapper.sltCtnuAtndCnt(SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public void atndDo() {

		stdQestService.qestDo(CommonUtil.setQuestPendingVO(2));

	}

}