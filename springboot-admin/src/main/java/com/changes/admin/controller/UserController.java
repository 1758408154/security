package com.changes.admin.controller;


import com.changes.common.model.CommonResult;
import com.changes.common.model.ResultCode;
import com.changes.common.service.UserService;
import com.changes.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuJunJie
 * @since 2019/11/21 15:28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("login")
    public CommonResult login(String username, String password){
        String token = null;
        UserDetails userDetails = userService.loadUserByUsername(username);


        if(userDetails!=null){
            String pass = userDetails.getPassword();
          //  System.out.println(passwordEncoder.matches("admin",pass));

            if(!passwordEncoder.matches(password,pass)){
                return CommonResult.failed(ResultCode.PASSWORD);
            }
            //生成token
            token = jwtTokenUtil.generateToken(userDetails);
            //设置全局用户信息
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return CommonResult.success(token);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @GetMapping("getCurrent")
    public String getCurrent(){

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
