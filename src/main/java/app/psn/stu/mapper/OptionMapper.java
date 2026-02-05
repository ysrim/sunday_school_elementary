package app.psn.stu.mapper;

import app.psn.stu.vo.PointHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionMapper {

    void pwChg(@Param("mberSn") Integer mberSn, @Param("newPw") String newPw);

    // 지금은 where 조건에 point적립내역만 있지만 추후에는 사용내역 추가해서 조건 걸어야.
    List<PointHistoryVO> sltPointHistory(@Param("mberSn") Integer mberSn);

}