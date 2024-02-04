package com.example.demo.domain.comment.repository;

import com.example.demo.domain.comment.dto.SubCommentResponseDto;
import com.example.demo.domain.comment.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    @Query("select sc.id, sc.commentContext, m.memberId, m.nickname " +
            "from SubComment sc " +
            "left join Member m on sc.member.memberId = m.memberId " +
            "where sc.comment.id = :commentId and sc.isDeleted = false " +
            "order by sc.creatTime")
    List<Object[]> findByCommentId(Long commentId);
}
