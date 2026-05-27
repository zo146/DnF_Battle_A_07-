package com.game.logic;

import com.game.model.캐릭터;
import com.game.model.마법사;
import com.game.model.전사;
import com.game.model.길드; // Phase 2: 길드 클래스 import 추가

public class 전투 {
    private 플레이어 playerMngr = new 플레이어();
    
    // 클래스 다이어그램의 [전투 ..> 캐릭터 : references] 관계를 반영하는 속성
    private 캐릭터 내캐릭터; 

    // Phase 2: SD에 명시된 "기존에 생성된 길드 객체 참조" 반영
    private 길드 기본길드 = new 길드("던파초보길드");

    // --- (Phase 1 기존 유지) CD 명세: 반환 타입 String 완벽 일치 ---
    public String 캐릭터생성(String 플레이어id, String 캐릭터명, String 직업, int 레벨) {
        // 시퀀스 다이어그램(SD)의 플레이어체크 및 분기 흐름 반영
        if (!playerMngr.플레이어체크(플레이어id)) {
            return "캐릭터 생성 실패 (올바르지 않은 플레이어)";
        }
        
        if ("전사".equals(직업)) {
            this.내캐릭터 = new 전사(캐릭터명, 레벨);
            return "캐릭터 생성 성공";
        } else if ("마법사".equals(직업)) {
            this.내캐릭터 = new 마법사(캐릭터명, 레벨);
            return "캐릭터 생성 성공";
        }
        return "캐릭터 생성 실패";
    }
    // -------------------------------------------------------------

    // --- (Phase 1 기존 유지) CD 명세: 매개변수는 플레이어id만 존재, 반환 타입 String 완벽 일치 ---
    public String 몬스터공격(String 플레이어id) {
        if (!playerMngr.플레이어체크(플레이어id)) {
            return "공격 실패";
        }
        
        if (this.내캐릭터 == null) {
            return "공격 실패 (생성된 캐릭터가 없습니다.)";
        }
        
        // --- 시퀀스 다이어그램(SD)의 'alt 현재 캐릭터 == 전사/마법사' 분기 완벽 반영 ---
        double 데미지 = 0.0;
        
        if (this.내캐릭터 instanceof 전사) {
            // 현재 캐릭터가 전사일 경우, 전사로 형변환 후 고유 스킬 호출
            전사 내전사 = (전사) this.내캐릭터;
            데미지 = 내전사.스킬발동_검휘두르기();
            
        } else if (this.내캐릭터 instanceof 마법사) {
            // 현재 캐릭터가 마법사일 경우, 마법사로 형변환 후 고유 스킬 호출
            마법사 내마법사 = (마법사) this.내캐릭터;
            데미지 = 내마법사.스킬발동_파이어볼();
        }
        // -------------------------------------------------------------
        
        String 등급 = "";
        
        // 시퀀스 다이어그램(SD)의 critical 데미지 등급 부여 반영
        if (데미지 >= 200) {
            등급 = "S급 공격";
        } else if (데미지 >= 100) {
            등급 = "A급 공격";
        } else {
            등급 = "B급 공격";
        }
        
        return 등급 + " [데미지: " + 데미지 + "]";
    }
    // -------------------------------------------------------------

    // --- Phase 2: GetItem_SD 완벽 반영 (아이템 획득) ---
    public String 아이템획득(String 플레이어id, String 아이템명, String 아이템타입, int 아이템가치) {
        if (!playerMngr.플레이어체크(플레이어id)) {
            return "플레이어 인증 실패";
        }
        
        if (this.내캐릭터 == null) {
            return "캐릭터가 존재하지 않습니다.";
        }
        
        // 캐릭터 내부의 인벤토리를 참조하여 아이템 추가 (Composition)
        boolean 추가결과 = this.내캐릭터.get캐릭터인벤토리().아이템추가(아이템명, 아이템타입, 아이템가치);
        
        if (추가결과) {
            return "아이템 획득 성공";
        } else {
            return "인벤토리가 가득 찼습니다";
        }
    }
    // -------------------------------------------------------------

    // --- Phase 2: JoinGuild_SD 완벽 반영 (길드 가입) ---
    public String 길드가입(String 플레이어id, String 길드명) {
        // SD: 플레이어체크
        if (!playerMngr.플레이어체크(플레이어id)) {
            return "플레이어 인증 실패";
        }
        
        if (this.내캐릭터 == null) {
            return "캐릭터가 존재하지 않습니다.";
        }
        
        // SD: 길드 정원 확인 및 캐릭터리스트 추가 (Aggregation)
        boolean 가입결과 = this.기본길드.캐릭터가입(this.내캐릭터);
        
        if (가입결과) {
            return "길드 가입 성공";
        } else {
            return "길드 정원이 가득 찼습니다";
        }
    }
    // -------------------------------------------------------------

    // UI(JSP) 화면에서 캐릭터 정보를 출력하기 위해 필요한 내부 Getter
    public 캐릭터 get내캐릭터() {
        return this.내캐릭터;
    }

    // Phase 2: JSP 화면에서 길드 정보를 확인하기 위한 Getter 추가
    public 길드 get기본길드() {
        return this.기본길드;
    }
}