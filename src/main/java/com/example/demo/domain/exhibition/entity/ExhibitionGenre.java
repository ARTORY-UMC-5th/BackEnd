package com.example.demo.domain.exhibition.entity;


import com.example.demo.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class ExhibitionGenre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitionGenre_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;



    @Builder.Default
    private int media = 0;
    @Builder.Default
    private int craft = 0;
    @Builder.Default
    private int design = 0;
    @Builder.Default
    private int picture = 0;
    @Builder.Default
    private int specialExhibition = 0;
    @Builder.Default
    private int sculpture = 0;
    @Builder.Default
    private int planExhibition = 0;
    @Builder.Default
    private int installationArt = 0;
    @Builder.Default
    private int painting = 0;
    @Builder.Default
    private int artistExhibition = 0;

    // 각 장르의 카운트를 증가시키는 메서드
    public void increaseMediaCount() {
        this.media++;
    }

    public void increaseCraftCount() {
        this.craft++;
    }

    public void increaseDesignCount() {
        this.design++;
    }

    public void increasePictureCount() {
        this.picture++;
    }

    public void increaseSculptureCount() {
        this.sculpture++;
    }

    public void increasePlanExhibitionCount() {
        this.planExhibition++;
    }

    public void increaseSpecialExhibitionCount() {
        this.specialExhibition++;
    }

    public void increaseInstallationArtCount() {
        this.installationArt++;
    }

    public void increasePaintingCount() {
        this.painting++;
    }

    public void increaseArtistExhibitionCount() {
        this.artistExhibition++;
    }


    // 각 장르의 카운트를 감소시키는 메서드
    public void decreaseMediaCount() {
        this.media--;
    }

    public void decreaseCraftCount() {
        this.craft--;
    }

    public void decreaseDesignCount() {
        this.design--;
    }

    public void decreasePictureCount() {
        this.picture--;
    }

    public void decreaseSculptureCount() {
        this.sculpture--;
    }

    public void decreasePlanExhibitionCount() {
        this.planExhibition--;
    }

    public void decreaseSpecialExhibitionCount() {
        this.specialExhibition--;
    }

    public void decreaseInstallationArtCount() {
        this.installationArt--;
    }

    public void decreasePaintingCount() {
        this.painting--;
    }

    public void decreaseArtistExhibitionCount() {
        this.artistExhibition--;
    }


}
