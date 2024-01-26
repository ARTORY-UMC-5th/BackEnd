package com.example.demo.global.error.exception;

import com.example.demo.global.error.ErrorCode;

public class StoryException extends BusinessException{

    public StoryException(ErrorCode errorcode){
        super(errorcode);
    }

}