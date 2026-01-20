package app.psn.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

@Mapper
public interface HomeMapper {

	List<HomeGuildListVO> sltGuildMberList(int guildSn); // 길드원 목록

	HomeGuildInfoVO sltGuildInfo(int guildSn); // 길드정보

}