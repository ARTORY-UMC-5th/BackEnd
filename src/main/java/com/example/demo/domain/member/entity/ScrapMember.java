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
// 누가 누구를 스크랩했는지 여부를 알기 위해선 해당 데이터가 있는지만 확인하면 됨

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrapMember_id")
    private Long id;

    // 유저 즐겨찾기 여부를 나타내는 필드 (is_favorite) 삭제

    // 누가 (스크랩 한 사람)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    // 누구를 (스크랩 당한 사람)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember;
}