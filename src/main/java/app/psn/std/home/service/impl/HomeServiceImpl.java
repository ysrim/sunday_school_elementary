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
import app.psn.std.home.mapper.HomeMapper;
import app.psn.std.home.service.HomeService;
import app.psn.std.quest.service.QuestService;
import app.psn.std.home.vo.HomeGuildInfoVO;
import app.psn.std.home.vo.HomeGuildListVO;
import app.psn.std.quest.vo.QuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("homeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {

    private final HomeMapper homeMapper;

    private final QuestService questService;

    private final CacheService cacheService;

    @Override
    public List<HomeGuildListVO> sltGuildMberList() {

        List<HomeGuildListVO> list = homeMapper.sltGuildMberList(SessionUtil.getMberInfo().getGuildSn());

        if (list != null) {
            list.parallelStream().forEach(vo -> {
                vo.setAccess(cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId()));
            });
        }

        return list != null ? list : Collections.emptyList();

    }

    @Override
    public List<HomeGuildListVO> sltGuildMberAccessList() {

        List<HomeGuildListVO> list = homeMapper.sltGuildMberList(SessionUtil.getMberInfo().getGuildSn());

        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream() //
                .filter(vo -> cacheService.checkKeyExists(CacheKeys.OnlineMbers.name(), vo.getMberId())) // 길드원이 온라인만 리스트업
                .collect(Collectors.toList());

    }

    @Override
    public HomeGuildInfoVO sltGuildInfo() {
        return homeMapper.sltGuildInfo(SessionUtil.getMberInfo().getGuildSn());
    }

    @Override
    public boolean wordsAmenDo() {

        QuestPendingVO questPendingVO = StringUtil.setQuestPendingVO(4);
        if (questService.questCompleteChk(questPendingVO)) {
            return false;
        } else {
            questService.questDo(questPendingVO);
            return true;
        }

    }

}