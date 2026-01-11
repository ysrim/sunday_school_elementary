package app.idx.lgn.service.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.SqlAbstractDAO;

import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;

@Repository("loginDAO")
public class LoginDAO extends SqlAbstractDAO {

	public SessionVO sltMber(LoginVO loginVO) {
		return selectOne("LoginDAO.sltMber", loginVO);
	}

}