package app.idx.lgn.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;

@Mapper
public interface LoginMapper {

	public SessionVO sltMber(LoginVO loginVO);

}