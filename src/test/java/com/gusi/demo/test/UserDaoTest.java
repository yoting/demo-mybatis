package com.gusi.demo.test;

import java.util.List;

import org.junit.Test;

import com.gusi.demo.dao.UserDao;
import com.gusi.demo.pojo.User;
import com.gusi.demo.utils.PageInfo;

public class UserDaoTest {
	@Test
	public void testQueryListUserByPage() {
		UserDao dao = new UserDao();
		User queryUser = new User();
		queryUser.setUsername("a");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageSize(2);
		pageInfo.setPageNo(1);
		List<User> userList = dao.queryListUser(queryUser, pageInfo);
		System.out.println(pageInfo);
		for (User user : userList) {
			System.out.println(user);
		}

		pageInfo.setPageNo(2);
		userList = dao.queryListUserByPage(queryUser, pageInfo);
		System.out.println(pageInfo);
		for (User user : userList) {
			System.out.println(user);
		}
	}
}
