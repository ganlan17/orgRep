package com.etc;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class SbmybatisdemoApplicationTests {

	@Resource
	UserDao userDao;
	Logger logger = LoggerFactory.getLogger(SbmybatisdemoApplicationTests.class);

	@Test
	public void findUserTest(){
		List<User> list = userDao.find();
		for(User u:list){
			logger.info(u.getUid()+":uname:" + u.getUname() + ",password:" + u.getPassword());
		}
	}

	@Test
	void contextLoads() {
	}

}
