package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.po.User;

public interface UserDAO {
	public List<User> getAllUsers(@Param("skip") int skip,@Param("size") int size,@Param("search_name") String name,@Param("search_account") String search_account);
	public int getCount(@Param("search_name") String search_name,@Param("search_account") String search_account);
	public int addUser(User user);
	public int updateUser(User user);
	public int deleteUserById(int id);
}
