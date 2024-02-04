package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class ExhibitionException extends BusinessException {

    public ExhibitionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
