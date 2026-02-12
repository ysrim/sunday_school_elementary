package app.psn.std.atnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.atnd.vo.StdAtndVO;

@Mapper
public interface StdAtndMapper {

	/**
	 * 당월 주일 출석 목록
	 */
	List<StdAtndVO> sltAtndList(Integer mberSn); // 출석 목록

	/**
	 * 연속출석일 수
	 */
	Integer sltCtnuAtndCnt(Integer mberSn);

}