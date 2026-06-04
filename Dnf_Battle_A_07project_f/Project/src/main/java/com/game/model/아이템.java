package com.game.model;

public class 아이템 {
    // CD 명세: 모두 private(-)
    private String 아이템명;
    private String 타입;
    private int 가치;
    private String 등급;

    // [실행을 위한 살] SD 생성자 및 등급 부여 Note 명세
    public 아이템(String 아이템명, String 타입, int 가치) {
        this.아이템명 = 아이템명;
        this.타입 = 타입;
        this.가치 = 가치;
        
        if (가치 >= 1000) {
            this.등급 = "Legendary";
        } else if (가치 >= 500) {
            this.등급 = "Rare";
        } else {
            this.등급 = "Common";
        }
    }

    // [실행을 위한 살] UI 출력을 위한 Getter
    public String get아이템명() { return 아이템명; }
    public String get타입() { return 타입; }
    public int get가치() { return 가치; }
    public String get등급() { return 등급; }
}