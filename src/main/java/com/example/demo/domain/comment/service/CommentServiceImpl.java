package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.CommentRequestDto;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService{


    @Transactional
    public void saveComment(CommentRequestDto commentRequestDto, MemberInfoDto memberInfoDto) {



    }

    @Transactional
    public void deleteComment(CommentRequestDto commentRequestDto, MemberInfoDto memberInfoDto) {

    }
}
