package com.yc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.dao.UserDAO;
import com.yc.po.User;
import com.yc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;

	@Override
	public List<User> getAllUser(int pageNo, int size, String search_name, String search_account) {
		// TODO Auto-generated method stub
		return userDao.getAllUsers((pageNo - 1) * size, size, "%" + search_name + "%", "%" + search_account + "%");
	}

	@Override
	public int getCount(String search_name, String search_account) {
		return userDao.getCount("%" + search_name + "%", "%" + search_account + "%");
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

}
