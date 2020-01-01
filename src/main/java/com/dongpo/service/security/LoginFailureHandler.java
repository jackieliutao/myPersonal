package com.dongpo.service.security;

import com.alibaba.druid.support.json.JSONUtils;
import com.dongpo.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        //response.setStatus(500);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(new Result(false, "登陆失败"));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }
}
