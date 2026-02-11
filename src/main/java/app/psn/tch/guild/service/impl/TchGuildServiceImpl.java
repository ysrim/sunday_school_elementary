package app.psn.tch.guild.service.impl;

import app.psn.tch.guild.mapper.TchGuildMapper;
import app.psn.tch.guild.service.TchGuildService;
import app.psn.tch.guild.vo.TchGuildMemberVO;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("tchGuildService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchGuildServiceImpl implements TchGuildService {

	private final TchGuildMapper tchGuildMapper;

	@Override
	public List<TchGuildMemberVO> sltTchGuildMberList() {

		return tchGuildMapper.sltTchGuildMberList(SessionUtil.getTchMberInfo().guildSn());

	}

}