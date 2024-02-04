package com.example.demo.domain.member.entity;


import com.example.demo.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScrapMember extends BaseEntity {
//저장스토리 이름을 어차피 책갈피(=스크랩)이니까 스크랩 스토리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrapMember_id")
    private Long id;

    // 유저 즐겨찾기 여부를 나타내는 필드 추가
    @Column(name = "isScrapped")
    private Boolean isScrapped;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember;

}