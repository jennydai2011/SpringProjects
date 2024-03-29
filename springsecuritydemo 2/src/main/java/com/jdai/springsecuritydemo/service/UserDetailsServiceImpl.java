package com.jdai.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder pw;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("        ---执行了自定义登录------------------");


        //1.查询数据库判断用户是否存在，如果不存在就会抛出UsernameNotFoundexception
        if(!"admin".equals(username)){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        //2.把从数据库查询返回的密码(注册时已经加密过）进行解析，或者直接把秘密法放入构造方法
        String password = pw.encode("123");
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_abc"));
    }
}
