package com.th.demo.service;

import java.util.List;

import com.th.demo.model.user;

public interface userService {
	public void addUser(user user);
	public List<user> selUser();
	public int updateUser(user user);
	public user selUserById(String id);
}
