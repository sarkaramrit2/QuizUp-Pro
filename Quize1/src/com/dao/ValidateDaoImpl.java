package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.entity.Admin;
import com.entity.User;

public class ValidateDaoImpl implements ValidationDaoI{

	
	private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate1) {
		this.hibernateTemplate = hibernateTemplate1;
	}


	@Override
	public User validateUser(String name, String pass) {
		
		String hql="from User where username=? and password=?";
		Object[] params={name,pass};
		List<User> lst=hibernateTemplate.find(hql,params);
		if(lst.size()!=0)
			return lst.get(0);
		return null;
	}


	@Override
	public Admin validateAdmin(String name, String pass) {
		
		String hql="from Admin where password=?";
		Object[] params={pass};
		List<Admin> lst=hibernateTemplate.find(hql,params);
		if(lst.size()!=0)
			return lst.get(0);
		return null;
	}


	@Override
	public void addAdmin(Admin admin) {
		
		hibernateTemplate.save(admin);
		System.out.println("Done");
	}
	
	
}
