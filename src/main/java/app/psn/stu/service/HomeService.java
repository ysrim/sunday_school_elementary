package app.psn.stu.service;

import java.util.List;

import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

public interface HomeService {

    List<HomeGuildListVO> sltGuildMberList(); // 길드원 목록

    List<HomeGuildListVO> sltGuildMberAccessList(); // 길드원 목록

    HomeGuildInfoVO sltGuildInfo(); // 길드정보

    boolean wordsAmenDo();

}