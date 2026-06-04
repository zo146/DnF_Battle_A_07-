package com.game.model;

import java.util.ArrayList;

public class 길드 {
    // CD 명세: 모두 private(-)
    private String 길드명;
    private int 최대인원 = 5;
    private ArrayList<캐릭터> 캐릭터리스트 = new ArrayList<>();

    // [실행을 위한 살] 생성자
    public 길드(String 길드명) {
        this.길드명 = 길드명;
    }

    // CD 명세: +캐릭터가입() boolean
    // [실행을 위한 살] SD 명세의 파라미터 적용
    public boolean 캐릭터가입(캐릭터 가입캐릭터) {
        // SD 명세: 길드 정원 < 5
        if (this.캐릭터리스트.size() < 최대인원) {
            this.캐릭터리스트.add(가입캐릭터);
            return true;
        } else {
            return false;
        }
    }

    // [실행을 위한 살] UI 출력을 위한 Getter
    public String get길드명() { return 길드명; }
    public ArrayList<캐릭터> get캐릭터리스트() { return 캐릭터리스트; }
}