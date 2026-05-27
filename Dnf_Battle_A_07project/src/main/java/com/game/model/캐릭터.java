package com.game.model;

public abstract class 캐릭터 {
    protected String 캐릭터명;
    protected int 레벨;
    protected int HP;
    protected int 공격력;
    protected 인벤토리 캐릭터인벤토리; // Phase 2: 인벤토리 복합객체 추가

    public String get캐릭터명() { return 캐릭터명; }
    public int get레벨() { return 레벨; }
    public int getHP() { return HP; }
    public int get공격력() { return 공격력; }
    public 인벤토리 get캐릭터인벤토리() { return 캐릭터인벤토리; }

    public abstract double 스킬발동();
    public abstract String 스킬명가져오기();
}