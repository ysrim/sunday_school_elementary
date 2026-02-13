package app.psn.std.home.service.impl;

import app.psn.com.service.CacheService;
import app.psn.std.home.mapper.StdHomeMapper;
import app.psn.std.home.service.StdHomeService;
import app.psn.std.home.vo.StdHomeGildInfoVO;
import app.psn.std.home.vo.StdHomeGildVO;
import app.psn.std.qest.service.StdQestService;
import app.psn.std.qest.vo.StdQestPendingVO;
import com.base.enumm.std.CacheKeys;
import com.base.utl.CommonUtil;
import com.base.utl.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("stdHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdHomeServiceImpl implements StdHomeService {

	private final StdHomeMapper stdHomeMapper;

	private final StdQestService stdQestService;

	private final CacheService cacheService;

	@Override
	public List<StdHomeGildVO> sltGildMberList() {

		List<StdHomeGildVO> list = stdHomeMapper.sltGildMberList(SessionUtil.getStdMberInfo().guildSn());

		if (list != null)
			list.parallelStream().forEach(vo -> vo.setAccess(cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())));

		return list != null ? list : Collections.emptyList();

	}

	@Override
	public List<StdHomeGildVO> sltGildMberAccessList() {

		List<StdHomeGildVO> list = stdHomeMapper.sltGildMberList(SessionUtil.getStdMberInfo().guildSn());

		return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream() //
				.filter(vo -> cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())) // 길드원이 온라인만 리스트업
				.collect(Collectors.toList());

	}

	@Override
	public StdHomeGildInfoVO sltGildInfo() {

		return stdHomeMapper.sltGildInfo(SessionUtil.getStdMberInfo().guildSn());

	}

	@Override
	public boolean wordsAmenDo() {

		StdQestPendingVO stdQestPendingVO = CommonUtil.setQuestPendingVO(4);
		if (stdQestService.qestCompleteChk(stdQestPendingVO)) {
			return false;
		} else {
			stdQestService.qestDo(stdQestPendingVO);
			return true;
		}

	}

}