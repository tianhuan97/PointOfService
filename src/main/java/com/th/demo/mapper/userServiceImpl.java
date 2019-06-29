package com.th.demo.mapper;

import com.th.demo.dao.userMapper;
import com.th.demo.model.user;
import com.th.demo.service.userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class userServiceImpl implements userService {
    @Resource
    private userMapper userDao;

    @Override
    public void addUser(user user) {
        userDao.addUser(user);
    }

    @Override
    public List<user> selUser() {
        return userDao.selUser();
    }

    @Override
    public int updateUser(user user) {
        return userDao.updateUser(user);
    }


    @Override
    public user selUserById(String id) {
        return userDao.selUserById(id);
    }
}
