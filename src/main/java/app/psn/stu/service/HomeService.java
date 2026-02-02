package app.psn.stu.service;

import java.util.List;

import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

public interface HomeService {

	List<HomeGuildListVO> sltGuildMberList(Integer guildSn); // 길드원 목록

	List<HomeGuildListVO> sltGuildMberAccessList(Integer guildSn); // 길드원 목록

	HomeGuildInfoVO sltGuildInfo(Integer guildSn); // 길드정보

}