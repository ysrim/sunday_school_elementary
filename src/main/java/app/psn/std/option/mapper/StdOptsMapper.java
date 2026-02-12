package app.psn.std.option.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.std.option.vo.StdRewardHistoryVO;

@Mapper
public interface StdOptsMapper {

    /**
     * 패스워드 변경
     */
    void pwChg(@Param("mberSn") Integer mberSn, @Param("newPw") String newPw);

    /**
     * 리워드 적립 내역
     */
    List<StdRewardHistoryVO> sltRewardHistory(@Param("mberSn") Integer mberSn);

    /**
     * 리워드 주간 적립 내역
     */
    HashMap<String, Integer> sltWeekStatics(@Param("mberSn") Integer mberSn);

}