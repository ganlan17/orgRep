package com.etc.dao;

import com.etc.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from Users where uname=#{uname} and password=#{password}")
    public User loginQuery(@Param("uname") String uname,@Param("password") String password);
    @Select("select * from Users order by uid desc")
    public List<User> find();
    @Select("select* from users where uid=#{uid}")
    public User get(Integer uid);
    @Insert("insert into Users values(default,#{uname},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "uid")
    public void add(User u);
    @Update("update Users set uname=#{uname},password=#{password} where uid=#{uid}")
    public void mod(User u);
    @Delete("delete from Users where uid=#{uid}")
    public void del(Integer uid);
}
