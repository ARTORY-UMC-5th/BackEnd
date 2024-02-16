package com.example.demo.api.login.validator;


import com.example.demo.domain.member.constant.MemberType;
import com.example.demo.global.error.ErrorCode;
import com.example.demo.global.error.exception.AuthenticationException;
import com.example.demo.global.error.exception.BusinessException;
import com.example.demo.global.jwt.constant.GrantType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OauthValidator {


    public void validateMemberType(String memberType){
        if(!MemberType.isMemberType(memberType)){
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
        }
    }

}
