package com.gusi.demo.idao;

import java.util.List;
import java.util.Map;

import com.gusi.demo.pojo.User;

public interface IUser {
	public int count(User user);

	public User queryUserById(int id);

	public List<User> queryListUser(Map<String, Object> parameter);

	public List<User> queryListUserByPage(Map<String, Object> parameter);
}
