package app.psn.mng.mber.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.mng.mber.vo.MngMberVO;

@Mapper
public interface MngMberMapper {

	/**
	 * 회원 목록
	 */
	List<MngMberVO> getMberList();

	/**
	 * 패스워드 초기화
	 */
	void resetPasswordAx(@Param("mberSn") Integer mberSn, @Param("pwd") String pwd);

}