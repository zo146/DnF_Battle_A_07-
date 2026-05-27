package com.game.model;
import java.util.ArrayList;

public class 인벤토리 {
    private int 최대용량 = 10;
    private ArrayList<아이템> 아이템리스트;

    public 인벤토리() {
        this.아이템리스트 = new ArrayList<>();
    }

    // SD 명세: 아이템추가() boolean 반환
    public boolean 아이템추가(String 아이템명, String 타입, int 가치) {
        if (this.아이템리스트.size() < 최대용량) {
            // Composition: 인벤토리가 아이템의 생성을 직접 책임짐
            아이템 newItem = new 아이템(아이템명, 타입, 가치);
            this.아이템리스트.add(newItem);
            return true;
        }
        return false;
    }

    public ArrayList<아이템> get아이템리스트() {
        return 아이템리스트;
    }
}