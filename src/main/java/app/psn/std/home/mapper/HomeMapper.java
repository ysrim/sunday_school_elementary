package app.psn.std.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.home.vo.HomeGuildInfoVO;
import app.psn.std.home.vo.HomeGuildListVO;

@Mapper
public interface HomeMapper {

    /**
     * 길드원 목록
     */
    List<HomeGuildListVO> sltGuildMberList(Integer guildSn);

    /**
     * 길드 정보
     */
    HomeGuildInfoVO sltGuildInfo(Integer guildSn);

}