package com.example.demo.domain.member.constant;

public enum Genre {
    MEDIA,CRAFT,DESIGN,PICTURE,SPECIAL_EXHIBITION,SCULPTURE,PLAN_EXHIBITION,
    INSTALLATION_ART,PAINTING,ARTIST_EXHIBITION, NONE;


    //순서대로 미디어 / 공예 / 디자인 / 사진 / 특별전시 / 조각 / 기획전 / 설치미술 / 회화 / 작가전 ( + NONE 추가)

    public static Genre fromString(String str) {
        if (str != null) {
            switch (str.toUpperCase()) {
                case "MEDIA": return MEDIA;
                case "CRAFT": return CRAFT;
                case "DESIGN": return DESIGN;
                case "PICTURE": return PICTURE;
                case "SPECIAL_EXHIBITION": return SPECIAL_EXHIBITION;
                case "SCULPTURE": return SCULPTURE;
                case "PLAN_EXHIBITION": return PLAN_EXHIBITION;
                case "INSTALLATION_ART": return INSTALLATION_ART;
                case "PAINTING": return PAINTING;
                case "ARTIST_EXHIBITION": return ARTIST_EXHIBITION;
                default: return NONE;
            }
        }
        return NONE;
    }
}