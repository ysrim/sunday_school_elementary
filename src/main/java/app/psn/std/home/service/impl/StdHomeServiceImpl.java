package app.psn.std.home.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.base.utl.SessionUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.std.CacheKeys;
import com.base.utl.StringUtil;

import app.psn.com.service.CacheService;
import app.psn.std.home.mapper.StdHomeMapper;
import app.psn.std.home.service.StdHomeService;
import app.psn.std.quest.service.StdQuestService;
import app.psn.std.home.vo.StdHomeGuildInfoVO;
import app.psn.std.home.vo.StdHomeGuildListVO;
import app.psn.std.quest.vo.StdQuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdHomeServiceImpl implements StdHomeService {

	private final StdHomeMapper stdHomeMapper;

	private final StdQuestService stdQuestService;

	private final CacheService cacheService;

	@Override
	public List<StdHomeGuildListVO> sltGuildMberList() {

		List<StdHomeGuildListVO> list = stdHomeMapper.sltGuildMberList(SessionUtil.getStdMberInfo().guildSn());

		if (list != null) {
			list.parallelStream().forEach(vo -> {
				vo.setAccess(cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId()));
			});
		}

		return list != null ? list : Collections.emptyList();

	}

	@Override
	public List<StdHomeGuildListVO> sltGuildMberAccessList() {

		List<StdHomeGuildListVO> list = stdHomeMapper.sltGuildMberList(SessionUtil.getStdMberInfo().guildSn());

		return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream() //
			.filter(vo -> cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())) // 길드원이 온라인만 리스트업
			.collect(Collectors.toList());

	}

	@Override
	public StdHomeGuildInfoVO sltGuildInfo() {

		return stdHomeMapper.sltGuildInfo(SessionUtil.getStdMberInfo().guildSn());

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