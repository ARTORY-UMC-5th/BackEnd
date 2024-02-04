package com.example.demo.domain.comment.entity;

import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;


/**
 * Comment :
 * 1. 댓글을 삭제하면 아예 안보이게 할 것인지 or "삭제되었습니다" 와 같이 문구를 띄울 것인지
 * 2. 댓글을 삭제했을 시 해당 댓글의 대댓글도 삭제할 것인지
 *
 * SubComment :
 * 1. 대댓글을 삭제하면 아예 안보이게 할 것인지 or "삭제되었습니다" 와 같이 문구를 띄울 것인지

 *
 * => 일단 아예 안보이도록 하는 방향으로 개발 (추후 논의)
 */

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SubComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcomment_id")
    private Long id;

    @Column(length = 100000)
    private String commentContext;

    // 댓글 삭제 시 해당 댓글을 유지할 것인지
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
