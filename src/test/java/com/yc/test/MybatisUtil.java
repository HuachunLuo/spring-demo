package com.yc.test;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class MybatisUtil {
	public static SqlSessionFactory factory=null;
	public static SqlSessionFactory getfactory(){
		if (factory==null){
			InputStream config = MybatisUtil.class.getClassLoader().getResourceAsStream("mybatisconfig.xml");
			factory = new SqlSessionFactoryBuilder().build(config);
		}
		return factory;
	}
	
	public static SqlSession getSession(boolean AutoCommit){
		return getfactory().openSession(AutoCommit);
	}
	
	public static SqlSession getSession(){
		return getSession(true);
	}
}
