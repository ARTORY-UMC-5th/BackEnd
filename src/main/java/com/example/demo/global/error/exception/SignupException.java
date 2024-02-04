package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class SignupException extends BusinessException{
    public SignupException(ErrorCode errorcode){
        super(errorcode);
    }
}
