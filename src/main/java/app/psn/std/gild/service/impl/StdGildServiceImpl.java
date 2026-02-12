package app.psn.std.gild.service.impl;

import app.psn.std.gild.mapper.StdGildMapper;
import app.psn.std.gild.service.StdGildService;
import app.psn.std.gild.vo.StdGildPostVO;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("stdGildService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdGildServiceImpl implements StdGildService {

	private final StdGildMapper stdGildMapper;

	@Override
	public List<StdGildPostVO> getTchGildPost() {

		return stdGildMapper.getTchGildPost(SessionUtil.getStdMberInfo().guildSn(), SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public void delGildPost(Integer postSn) {

		stdGildMapper.delGildPost(postSn, SessionUtil.getStdMberInfo().mberSn());

	}

	@Override
	public void regGildPost(String content) {

		stdGildMapper.regGildPost(SessionUtil.getStdMberInfo().guildSn(), SessionUtil.getStdMberInfo().mberSn(), content);

	}

}