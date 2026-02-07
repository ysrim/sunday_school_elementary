package app.psn.tch.home.service.impl;

import app.psn.com.service.CacheService;
import app.psn.std.quest.service.StdQuestService;
import app.psn.std.quest.vo.StdQuestPendingVO;
import app.psn.tch.home.mapper.TchHomeMapper;
import app.psn.tch.home.service.TchHomeService;
import app.psn.tch.home.vo.StdHomeGuildInfoVO;
import app.psn.tch.home.vo.StdHomeGuildListVO;
import com.base.enumm.std.CacheKeys;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("tchHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchHomeServiceImpl implements TchHomeService {

	private final TchHomeMapper tchHomeMapper;

	private final StdQuestService stdQuestService;

	private final CacheService cacheService;

	@Override
	public List<StdHomeGuildListVO> sltGuildMberList() {

		List<StdHomeGuildListVO> list = tchHomeMapper.sltGuildMberList(SessionUtil.getStdMberInfo().guildSn());

		if (list != null)
			list.parallelStream().forEach(vo -> vo.setAccess(cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())));

		return list != null ? list : Collections.emptyList();

	}

	@Override
	public List<StdHomeGuildListVO> sltGuildMberAccessList() {

		List<StdHomeGuildListVO> list = tchHomeMapper.sltGuildMberList(SessionUtil.getStdMberInfo().guildSn());

		return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream() //
			.filter(vo -> cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())) // 길드원이 온라인만 리스트업
			.collect(Collectors.toList());

	}

	@Override
	public StdHomeGuildInfoVO sltGuildInfo() {

		return tchHomeMapper.sltGuildInfo(SessionUtil.getStdMberInfo().guildSn());

	}

	@Override
	public boolean wordsAmenDo() {

		StdQuestPendingVO stdQuestPendingVO = StringUtil.setQuestPendingVO(4);
		if (stdQuestService.questCompleteChk(stdQuestPendingVO)) {
			return false;
		} else {
			stdQuestService.questDo(stdQuestPendingVO);
			return true;
		}

	}

}