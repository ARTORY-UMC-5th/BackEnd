package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorcode){
        super(errorcode);
    }

}
