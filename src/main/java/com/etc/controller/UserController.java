package com.etc.controller;

import com.etc.entity.User;
import com.etc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/userlogin")
    public ModelAndView userlogin(String uname, String password, HttpServletResponse response, HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userquery"));
        if(uname == null && password == null){//检证
            mv.setViewName("login");
            return mv;
        }
        User u = userService.loginquery(uname,password);
        if(u != null){//登陆成功
            session.setAttribute("user",u);
        }else{
            mv.setViewName("login");
            mv.addObject("error","用户名密码错误！");
        }
        return mv;
    }

    @RequestMapping("/userquery")
    public ModelAndView query(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView("userquery");
        mv.addObject("p",userService.findPage(pageNum,pageSize));
        return mv;
    }

    @RequestMapping("/userget/{uid}")
    public ModelAndView get(@PathVariable Integer uid){
        ModelAndView mv = new ModelAndView("userget");
        mv.addObject("u",userService.get(uid));
        return mv;
    }

    @RequestMapping("/userdel/{uid}")
    public ModelAndView del(@PathVariable Integer uid){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userquery"));
        userService.del(uid);
        mv.addObject("msg","删除成功!");
        return mv;
    }

    @RequestMapping("/usertoadd")
    public ModelAndView toadd(){
        ModelAndView mv = new ModelAndView("useradd");
        return mv;
    }

    @RequestMapping("/useradd")
    public ModelAndView add(User u){
        userService.add(u);
        ModelAndView mv = new ModelAndView(new RedirectView("userget/"+u.getUid()));
        mv.addObject("msg","新加成功!");
        return mv;
    }

    @RequestMapping("/usermod")
    public ModelAndView mod(User u){
        userService.mod(u);
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userget/"+u.getUid()));
        mv.addObject("msg","修改成功!");
        return mv;
    }

}
