package com.gusi.demo.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gusi.demo.idao.IUser;
import com.gusi.demo.pojo.User;
import com.gusi.demo.utils.MybatisUtils;
import com.gusi.demo.utils.PageInfo;

public class UserDao {
	MybatisUtils mybatisUtils = new MybatisUtils();
	SqlSession sqlSession = null;

	// 通过手动写分页统计
	public List<User> queryListUser(User queryUser, PageInfo pageInfo) {
		List<User> userList = null;
		try {
			sqlSession = mybatisUtils.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		IUser iUser = sqlSession.getMapper(IUser.class);

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("user", queryUser);
		int count = iUser.count(queryUser);
		pageInfo.setTotalReacordNumber(count);
		parameter.put("pageInfo", pageInfo);

		userList = iUser.queryListUser(parameter);
		return userList;
	}

	// 通过拦截器查询分页
	public List<User> queryListUserByPage(User queryUser, PageInfo pageInfo) {
		List<User> userList = null;
		try {
			sqlSession = mybatisUtils.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		IUser iUser = sqlSession.getMapper(IUser.class);

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("user", queryUser);
		// int count = iUser.count(queryUser);
		// pageInfo.setTotalReacordNumber(count);
		parameter.put("pageInfo", pageInfo);

		userList = iUser.queryListUserByPage(parameter);
		return userList;
	}
}
