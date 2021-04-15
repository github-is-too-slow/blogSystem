package com.billion.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.billion.common.annotation.LogAnnotation;
import com.billion.common.result.Result;
import com.billion.common.result.ResultCode;
import com.billion.entity.User;
import com.billion.service.UserService;
import com.billion.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Billion
 * @create 2021/04/04 17:21
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/checkUsername")
    @LogAnnotation(module = "用户模块", operation = "用户名检查")
    public Result checkUsername(@RequestParam String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User dbuser = userService.getOne(wrapper);
        if(null == dbuser){//用户名合法
            return Result.success();
        }
        return Result.error(ResultCode.ERROR);
    }

    @PostMapping("/login")
    @LogAnnotation(module = "用户模块", operation = "用户登录")
    public Result login(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());
        User dbuser = userService.getOne(wrapper);
        if(null == dbuser) {
            return Result.error(ResultCode.ERROR);
        }
        Result result = Result.success();
        result.simple().put("user", dbuser);
        return result;
    }

    @PostMapping("/save")
    @LogAnnotation(module = "用户模块", operation = "用户注册")
    public Result saveUser(HttpServletRequest request,
                           User user, @RequestPart MultipartFile realAdavar){
        String originalFilename = realAdavar.getOriginalFilename();
        String saveFilename = new Date().getTime() + originalFilename;
        //上传相对目录为static/api/image/adavar/（参考目录：classpath）
        File uploadDest = PathUtils.getUploadDest("static/api/image/adavar/", saveFilename);
        try {
            realAdavar.transferTo(uploadDest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成文件的网络URL:通过Nginx服务器映射到后端路径
        String url = PathUtils.getUploadFileUrl(request.getScheme(), "localhost",
                "/api/image/adavar/", saveFilename);
        user.setAdavar(url);
        user.setCreateDate(new Date());
        boolean flag = userService.save(user);
        Result result = new Result();
        if(flag) {//注册成功，userId中已被重写入user
            result.setResultCode(ResultCode.SUCCESS);
            result.simple().put("user", user);
        }else {//注册失败
            result.setResultCode(ResultCode.ERROR);
        }
        return result;
    }

}
