package com.game.model;
import java.util.ArrayList;

public class 길드 {
    private String 길드명;
    private int 최대인원 = 5;
    private ArrayList<캐릭터> 캐릭터리스트;

    public 길드(String 길드명) {
        this.길드명 = 길드명;
        this.캐릭터리스트 = new ArrayList<>();
    }

    // SD 명세: 캐릭터가입(캐릭터 객체)
    public boolean 캐릭터가입(캐릭터 가입캐릭터) {
        if (this.캐릭터리스트.size() < 최대인원) {
            this.캐릭터리스트.add(가입캐릭터);
            return true;
        }
        return false;
    }

    public String get길드명() { return 길드명; }
    public ArrayList<캐릭터> get캐릭터리스트() { return 캐릭터리스트; }
}