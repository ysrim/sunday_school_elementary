package app.psn.tch.home.service.impl;

import app.psn.tch.home.mapper.TchHomeMapper;
import app.psn.tch.home.service.TchHomeService;
import app.psn.tch.home.vo.TchGildPostVO;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("tchHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchHomeServiceImpl implements TchHomeService {

	private final TchHomeMapper tchHomeMapper;

	@Override
	public Map<String, String> dashboard() {

		return tchHomeMapper.dashboard(SessionUtil.getTchMberInfo().guildSn());

	}

	@Override
	public String gildMsg() {

		return tchHomeMapper.gildMsg(SessionUtil.getTchMberInfo().guildSn());
	}

	@Override
	public void regGildMsg(String slogan) {

		tchHomeMapper.regGildMsg(SessionUtil.getTchMberInfo().guildSn(), slogan);

	}

	@Override
	public List<TchGildPostVO> getTchGildPost() {

		return tchHomeMapper.getTchGildPost(SessionUtil.getTchMberInfo().guildSn(), SessionUtil.getTchMberInfo().mberSn());

	}

	@Override
	public void delGildPost(Integer postSn) {

		tchHomeMapper.delGildPost(SessionUtil.getTchMberInfo().guildSn(), postSn);

	}

	@Override
	public void regGildPost(String content) {

		tchHomeMapper.regGildPost(SessionUtil.getTchMberInfo().guildSn(), SessionUtil.getTchMberInfo().mberSn(), content);

	}

}