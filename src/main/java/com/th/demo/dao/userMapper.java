package com.th.demo.dao;

import java.util.List;

import com.th.demo.model.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface userMapper {
	public void addUser(user user);
	public List<user> selUser();
	public int updateUser(user user);
	public user selUserById(String id);
}
