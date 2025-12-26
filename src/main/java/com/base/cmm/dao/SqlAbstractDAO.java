package com.base.cmm.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;

public abstract class SqlAbstractDAO extends EgovAbstractMapper {

	@Resource(name = "home.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}

}