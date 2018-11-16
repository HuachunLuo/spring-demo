package com.yc.service;

import java.util.List;

import com.yc.po.User;

public interface UserService {
	public List<User> getAllUser(int pageNo,int size,String search_name,String search_account);
	public int getCount(String search_name,String search_account);
	public int addUser(User user);
	public int deleteUserById(int id);
	public int updateUser(User user);
	
}
