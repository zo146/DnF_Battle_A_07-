package com.game.model;

import java.util.ArrayList;

public class 인벤토리 {
    // CD 명세: -int 최대용량, -ArrayList 아이템리스트
    private int 최대용량 = 10;
    private ArrayList<아이템> 아이템리스트 = new ArrayList<>();

    // CD 명세: +아이템추가() boolean 
    // [실행을 위한 살] SD 명세의 파라미터 적용
    public boolean 아이템추가(String 아이템명, String 타입, int 가치) {
        // SD 명세: 인벤토리 용량 < 10
        if (this.아이템리스트.size() < 최대용량) {
            아이템 newItem = new 아이템(아이템명, 타입, 가치);
            this.아이템리스트.add(newItem);
            return true;
        } else {
            return false;
        }
    }

    // [실행을 위한 살] UI 출력을 위한 Getter
    public ArrayList<아이템> get아이템리스트() { return this.아이템리스트; }
}