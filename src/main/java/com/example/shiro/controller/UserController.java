package com.example.shiro.controller;

import com.example.shiro.rpc.ErrorCodeEnum;
import com.example.shiro.rpc.Response;
import com.example.shiro.rpc.Results;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Map> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
            Map resMap = new HashMap();
            resMap.put("token", String.valueOf(subject.getSession().getId()));
            return Results.newSuccessResponse(resMap);
        } catch (UnknownAccountException uae) {
            token.clear();
            return Results.newFailedResponse(ErrorCodeEnum.FAIL,"未知账户");
        } catch (IncorrectCredentialsException ice) {
            token.clear();
            return Results.newFailedResponse(ErrorCodeEnum.FAIL,"密码不正确");
        } catch (LockedAccountException lae) {
            token.clear();
            return Results.newFailedResponse(ErrorCodeEnum.FAIL,"账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            token.clear();
            return Results.newFailedResponse(ErrorCodeEnum.FAIL,"用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            token.clear();
            return Results.newFailedResponse(ErrorCodeEnum.FAIL,"用户名或密码不正确！");
        }
        /*
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
        */
    }

}
