package com.etc;

import com.etc.entity.User;
import com.etc.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Resource
    UserService userService;
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void findUserTest(){
        List<User> list = userService.find();
        for(User u:list){
            logger.info(u.getUid()+":uname:" + u.getUname() + ",password:" + u.getPassword());
        }
    }
}
