package com.gusi.demo.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

	public SqlSession getSqlSession() throws IOException {
		// 1通过配置文件获取数据库连接信息
		InputStream inputStream = Resources.getResourceAsStream("Configuration.xml");
		// 2通过配置文件得到sqlSessionFactory对象
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
		// 3通过sessionFactory得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

}
