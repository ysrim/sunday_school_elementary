package app.login.service.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.SqlAbstractDAO;

import app.login.vo.LoginVO;
import app.login.vo.SessionVO;

@Repository("loginDAO")
public class LoginDAO extends SqlAbstractDAO {

	public SessionVO sltMber(LoginVO loginVO) {
		return selectOne("LoginDAO.sltMber", loginVO);
	}

}