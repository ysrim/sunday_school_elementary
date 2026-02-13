package app.psn.tch.login.mapper;

import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TchLoginMapper {

	/**
	 * 학생 회원 사용자 정보 요청
	 */
	TchSessionVO sltMber(TchLoginVO loginVO);

}