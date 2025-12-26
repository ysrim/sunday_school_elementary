package app.login.service.impl;

import org.springframework.stereotype.Repository;

import com.base.cmm.dao.SqlAbstractDAO;

import net.api.enotary.vo.RestApiLogVO;

@Repository("loginDAO")
public class LoginDAO extends SqlAbstractDAO {

	public int insertRestAPiLog(RestApiLogVO vo) {
		// return insert("RestApiLogDAO.insertRestAPiLog", vo);
		return 0;
	}

}