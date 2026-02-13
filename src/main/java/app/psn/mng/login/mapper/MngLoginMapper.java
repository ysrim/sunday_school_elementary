package app.psn.mng.login.mapper;

import app.psn.mng.login.vo.MngLoginVO;
import app.psn.mng.login.vo.MngSessionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MngLoginMapper {

	/**
	 * 관리자 회원 사용자 정보 요청
	 */
	MngSessionVO sltMber(MngLoginVO loginVO);

}