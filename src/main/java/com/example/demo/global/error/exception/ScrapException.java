package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class ScrapException extends BusinessException{

    public ScrapException(ErrorCode errorCode) {
        super(errorCode);
    }
}
