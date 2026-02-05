package app.psn.stu.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.stu.vo.PointHistoryVO;

@Mapper
public interface OptionMapper {

	void pwChg(@Param("mberSn") Integer mberSn, @Param("newPw") String newPw);

	// 지금은 where 조건에 point적립내역만 있지만 추후에는 사용내역 추가해서 조건 걸어야.
	List<PointHistoryVO> sltPointHistory(@Param("mberSn") Integer mberSn);

	HashMap<String, Integer> sltWeekStatics(@Param("mberSn") Integer mberSn);

}