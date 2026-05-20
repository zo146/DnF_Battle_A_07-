package com.game.logic;

import com.game.model.캐릭터;
import com.game.model.마법사;
import com.game.model.전사;

public class 전투 {
    private 플레이어 playerMngr = new 플레이어();

    // 캐릭터 생성 로직
    public 캐릭터 캐릭터생성(String 플레이어id, String 캐릭터명, String 직업, int 레벨) {
        if (!playerMngr.플레이어체크(플레이어id)) {
            return null; 
        }
        
        if ("전사".equals(직업)) {
            return new 전사(캐릭터명, 레벨);
        } else if ("마법사".equals(직업)) {
            return new 마법사(캐릭터명, 레벨);
        }
        return null;
    }

    // 몬스터 공격 로직 (등급 뒤에 연산된 데미지 숫자를 붙여서 반환)
    public String 몬스터공격(String 플레이어id, 캐릭터 내캐릭터) {
        if (!playerMngr.플레이어체크(플레이어id)) {
            return "공격 실패";
        }
        
        // 직업별 스킬 발동 및 데미지 산출
        double 데미지 = 내캐릭터.스킬발동();
        String 등급 = "";
        
        // 데미지 등급 판정
        if (데미지 >= 200) {
            등급 = "S급 공격";
        } else if (데미지 >= 100) {
            등급 = "A급 공격";
        } else {
            등급 = "B급 공격";
        }
        
        // 결과 예시: "S급 공격 [데미지: 300.0]"
        return 등급 + " [데미지: " + 데미지 + "]";
    }
}