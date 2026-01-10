package com.base.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import jakarta.annotation.Resource;

public abstract class SqlAbstractDAO extends DataMapper {

	@Resource(name = "home.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}

}