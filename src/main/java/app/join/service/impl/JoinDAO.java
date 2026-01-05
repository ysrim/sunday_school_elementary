package app.join.service.impl;

import org.springframework.stereotype.Repository;

import com.base.cmm.dao.SqlAbstractDAO;

import net.api.enotary.vo.RestApiLogVO;

@Repository("joinDAO")
public class JoinDAO extends SqlAbstractDAO {

	public int idDupleChk(String memberId) {
		return selectOne("JoinDAO.idDupleChk", memberId);
	}

}