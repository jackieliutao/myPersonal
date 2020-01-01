package com.dongpo.service.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.dongpo.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //登录成功的处理
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(new Result(true, "登陆成功"));
        writer.println(s);
    }
}
