package com.base.cmm.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;

import jakarta.annotation.Resource;

public abstract class SqlAbstractDAO extends EgovAbstractMapper {

	@Resource(name = "home.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}

}