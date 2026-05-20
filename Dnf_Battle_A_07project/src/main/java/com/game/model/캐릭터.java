package com.game.model;

public abstract class 캐릭터 {
    protected String 캐릭터명;
    protected int 레벨;
    protected int HP;
    protected int 공격력;

    // JSP에서 값을 읽어오기 위한 공통 Getter 메서드
    public String get캐릭터명() { return 캐릭터명; }
    public int get레벨() { return 레벨; }
    public int getHP() { return HP; }
    public int get공격력() { return 공격력; }

    // 하위 직업 클래스에서 구현할 추상 메서드
    public abstract double 스킬발동();
    public abstract String 스킬명가져오기();
}