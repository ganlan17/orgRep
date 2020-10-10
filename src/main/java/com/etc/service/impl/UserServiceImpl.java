package com.etc.service.impl;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import com.etc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User loginquery(String uname, String password) {
        return userDao.loginQuery(uname,password);
    }

    @Override
    public List<User> find() {
        return userDao.find();
    }

    @Override
    public User get(Integer uid) {
        return userDao.get(uid);
    }

    @Override
    public void add(User u) {
        userDao.add(u);
    }

    @Override
    public void mod(User u) {
        userDao.mod(u);
    }

    @Override
    public void del(Integer uid) {
        userDao.del(uid);
    }

    @Override
    public PageInfo<User> findPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//1,通过PageHelper方法startPage()设置当前页及每页显示的记录条数
        List<User> list = userDao.find();//2,查询出页面要显示的集合的数据
        PageInfo<User> pageInfo = new PageInfo<>(list);//3,把查询出的页面要显示的集合数据传入到PageInfo的构造方法中
        return pageInfo;
    }
}
