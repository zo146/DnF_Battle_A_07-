package com.game.model;

public abstract class 캐릭터 {
    // CD 명세: 모두 private(-)
    private String 캐릭터명;
    private int 레벨;
    private int HP;
    private int 공격력;
    private 인벤토리 캐릭터인벤토리;

    // [실행을 위한 살] 생성자
    public 캐릭터(String 캐릭터명, int 레벨, int HP, int 공격력) {
        this.캐릭터명 = 캐릭터명;
        this.레벨 = 레벨;
        this.HP = HP;
        this.공격력 = 공격력;
        // 요구사항 명세: 캐릭터 생성 시 빈 인벤토리 자동 생성 (Composition)
        this.캐릭터인벤토리 = new 인벤토리(); 
    }

    // CD 명세: +스킬발동() double
    public abstract double 스킬발동();

    // [실행을 위한 살] 하위 클래스 연산 및 UI 출력을 위한 Getter
    public String get캐릭터명() { return this.캐릭터명; }
    public int get레벨() { return this.레벨; }
    public int getHP() { return this.HP; }
    public int get공격력() { return this.공격력; }
    public 인벤토리 get캐릭터인벤토리() { return this.캐릭터인벤토리; }
}