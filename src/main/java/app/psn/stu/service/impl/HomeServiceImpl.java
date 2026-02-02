package app.psn.stu.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.CacheKeys;

import app.psn.com.service.CacheService;
import app.psn.stu.mapper.HomeMapper;
import app.psn.stu.service.HomeService;
import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("homeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {

	private final HomeMapper homeMapper;

	private final CacheService cacheService;

	@Override
	public List<HomeGuildListVO> sltGuildMberList(int guildSn) {
		List<HomeGuildListVO> list = homeMapper.sltGuildMberList(guildSn);
		if (list != null) {
			list.parallelStream().forEach(vo -> {
				vo.setAccess(cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId()));
			});
		}
		return list != null ? list : Collections.emptyList();
	}

	@Override
	public List<HomeGuildListVO> sltGuildMberAccessList(int guildSn) {
		List<HomeGuildListVO> list = homeMapper.sltGuildMberList(guildSn);
		return Optional.ofNullable(list)
			.orElseGet(Collections::emptyList)
			.stream()
			.filter(vo -> cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())) // 길드원이 온라인만 리스트업
			.collect(Collectors.toList());
	}

	@Override
	public HomeGuildInfoVO sltGuildInfo(int guildSn) {
		return homeMapper.sltGuildInfo(guildSn);
	}

}