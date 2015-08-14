package com.dao;

import com.entity.Admin;
import com.entity.User;

public interface ValidationDaoI {

	
	public User validateUser(String name,String pass);
	public Admin validateAdmin(String name,String pass);
	public void addAdmin(Admin admin);
}
