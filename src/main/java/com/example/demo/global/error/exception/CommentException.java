package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class CommentException extends BusinessException {

    public CommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
