package com.ssh.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

//	private HibernateTemplate hibernateTemplate;
//
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
	
@SuppressWarnings("unchecked")
	//	登陆的功能
	@Override
	public User loginUser(User user) {
		//调用方法得到hibernatetemplate对象
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		List<User> use_list = (List<User>) hibernateTemplate.find("from User where USERNAME=? and PASSWORD=?", user.getUsername(),user.getPassword());
		if((use_list.size()!=0) && use_list != null) {
			User u = use_list.get(0);
				return u;
		}else {
			return null;
		}
	}

@Override
public List<User> findAll() {
	List<User> users = (List<User>) this.getHibernateTemplate().find("from User");
	return users;
}
}
