package com.game.model;

public class 전사 extends 캐릭터 {
    // 요구사항 명세: HP(레벨x100), 공격력(레벨x15)
    public 전사(String 캐릭터명, int 레벨) {
        super(캐릭터명, 레벨, 레벨 * 100, 레벨 * 15);
    }

    // CD 명세: +스킬발동_검휘두르기() double
    public double 스킬발동_검휘두르기() {
        return get공격력() * 1.5;
    }

    @Override
    public double 스킬발동() {
        return 스킬발동_검휘두르기();
    }
}