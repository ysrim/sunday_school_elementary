package app.psn.tch.gild.service.impl;

import app.psn.tch.gild.mapper.TchGildMapper;
import app.psn.tch.gild.service.TchGildService;
import app.psn.tch.gild.vo.TchGildMemberVO;

import com.base.utl.SessionUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("tchGildService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchGildServiceImpl implements TchGildService {

	private final TchGildMapper tchGildMapper;

	@Override
	public List<TchGildMemberVO> sltTchGuildMberList() {

		return tchGildMapper.sltTchGuildMberList(SessionUtil.getTchMberInfo().guildSn());

	}

}