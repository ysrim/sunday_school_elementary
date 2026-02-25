package app.psn.mng.mber.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.mng.mber.vo.MngMberVO;

@Mapper
public interface MngMberMapper {

	/**
	 * 회원 목록
	 */
	List<MngMberVO> getMberList();

}