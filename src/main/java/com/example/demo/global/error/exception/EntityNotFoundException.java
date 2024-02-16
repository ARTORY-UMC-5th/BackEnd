package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}
