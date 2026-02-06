package app.psn.tch.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;

@Mapper
public interface TchLoginMapper {

	/**
	 * 학생 회원 사용자 정보 요청
	 */
	public TchSessionVO sltMber(TchLoginVO loginVO);

}