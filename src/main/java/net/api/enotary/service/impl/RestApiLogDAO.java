package net.api.enotary.service.impl;

import org.springframework.stereotype.Repository;

import com.base.cmm.dao.SqlAbstractDAO;

import net.api.enotary.vo.RestApiLogVO;

@Repository("restApiLogDAO")
public class RestApiLogDAO extends SqlAbstractDAO {

    public int insertRestAPiLog(RestApiLogVO vo) {
        return insert("RestApiLogDAO.insertRestAPiLog", vo);
    }

}