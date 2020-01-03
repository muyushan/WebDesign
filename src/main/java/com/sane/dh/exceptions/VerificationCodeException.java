package com.sane.dh.exceptions;

import javax.naming.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException() {
        super("验证码验证失败，请刷新验证码重新填写");
    }
}
