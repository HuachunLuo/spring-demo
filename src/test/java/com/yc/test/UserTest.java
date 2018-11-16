package com.yc.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.dao.UserDAO;
import com.yc.po.User;

import junit.framework.Assert;

public class UserTest {

	@Test
	public void insertTest() {
		SqlSession session = MybatisUtil.getSession();
		try {

			User user = new User();
			user.setName("罗华春");
			user.setAddress("宜山路580号四楼一座");
			user.setAccount("luohuachun");
			user.setEmail("abc@111.com");
			user.setPhone("13569000000");

			UserDAO dao = session.getMapper(UserDAO.class);
			Assert.assertEquals(1, dao.addUser(user));
		} finally {
			session.close();
		}
	}

	@Test
	public void getCount(){
		SqlSession session = MybatisUtil.getSession();
		try {
			UserDAO dao = session.getMapper(UserDAO.class);
			Assert.assertEquals(1, dao.getCount("%%", "%%"));
		} finally {
			session.close();
		}
	}

	@Test
	public void DeleteUserById() {
		SqlSession session = MybatisUtil.getSession();
		try {
			UserDAO dao = session.getMapper(UserDAO.class);
			Assert.assertEquals(1, dao.deleteUserById(1));
		} finally {
			session.close();
		}
	}

	@Test
	public void UpdateUser() {
		SqlSession session = MybatisUtil.getSession();
		try {
			UserDAO dao = session.getMapper(UserDAO.class);
			User user = dao.getAllUsers(0, 10, "%%", "%%").get(0);
			user.setName("罗华春aaa");
			Assert.assertEquals(1, dao.updateUser(user));
		} finally {
			session.close();
		}
	}

	@Test
	public void getUser() {
		SqlSession session = MybatisUtil.getSession();
		try {
			UserDAO dao = session.getMapper(UserDAO.class);
			List<User> users = dao.getAllUsers(0, 10, "%%", "%%");
			for (User o : users) {
				System.out.println(o.getAccount() + " " + o.getAddress() + " " + o.getEmail() + " " + o.getId() + " "
						+ o.getName() + " " + o.getPhone());
			}
		} finally {
			session.close();
		}
	}
}
