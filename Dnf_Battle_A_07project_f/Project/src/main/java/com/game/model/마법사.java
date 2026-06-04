package com.game.model;

public class 마법사 extends 캐릭터 {
    // 요구사항 명세: HP(레벨x60), 공격력(레벨x25)
    public 마법사(String 캐릭터명, int 레벨) {
        super(캐릭터명, 레벨, 레벨 * 60, 레벨 * 25);
    }

    // CD 명세: +스킬발동_파이어볼() double
    public double 스킬발동_파이어볼() {
        return get공격력() * 2.0;
    }

    @Override
    public double 스킬발동() {
        return 스킬발동_파이어볼();
    }
}