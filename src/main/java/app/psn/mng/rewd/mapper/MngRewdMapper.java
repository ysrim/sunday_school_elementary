package app.psn.mng.rewd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.mng.rewd.vo.MngStdVO;

@Mapper
public interface MngRewdMapper {

	/**
	 * 학생
	 */
	List<MngStdVO> getStdList();

}