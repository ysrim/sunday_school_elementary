package app.psn.std.home.service;

import java.util.List;

import app.psn.std.home.vo.HomeGuildInfoVO;
import app.psn.std.home.vo.HomeGuildListVO;

public interface HomeService {

    /**
     * 길드원 목록
     */
    List<HomeGuildListVO> sltGuildMberList();

    /**
     * 현재 로그인한 길드원 목록
     */
    List<HomeGuildListVO> sltGuildMberAccessList();

    /**
     * 길드정보
     */
    HomeGuildInfoVO sltGuildInfo();

    /**
     * 오늘의 말씀 클릭
     */
    boolean wordsAmenDo();

}