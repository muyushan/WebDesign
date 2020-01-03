package com.sane.dh.filters;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.sane.dh.exceptions.VerificationCodeException;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(StringUtils.isEquals(request.getRequestURI(),request.getContextPath()+"/doLogin")&&StringUtils.isEquals(HttpMethod.POST.toString(),request.getMethod())){
            try {
                verificationCode(request);
               filterChain.doFilter(request,response);
            } catch (VerificationCodeException e) {
                response.getOutputStream().print("the verificationCode is wrong!");
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }

    private void  verificationCode(HttpServletRequest request) throws VerificationCodeException {
        String requestCaptcha=request.getParameter("captcha");
        String sessionCaptcha=request.getSession().getAttribute("captchaText").toString();
        if(StringUtils.isNotEmpty(sessionCaptcha)){
            request.getSession().removeAttribute("captchaText");
        }
        if(StringUtils.isEmpty(requestCaptcha)||StringUtils.isEmpty(sessionCaptcha)||!requestCaptcha.toLowerCase().equals(sessionCaptcha)){
            throw  new VerificationCodeException();
        }
    }
}
