package com.game.logic;

import com.game.model.*;

public class 전투 {
    // CD 명세: 전투 ..> 플레이어, 캐릭터 (references)
    private 플레이어 playerMngr = new 플레이어();
    private 캐릭터 내캐릭터;
    
    // SD 명세: 기존에 생성된 길드 객체 참조
    private 길드 기본길드 = new 길드("던파초보길드");

    // CD 명세: +캐릭터생성(...) String
    public String 캐릭터생성(String 플레이어id, String 캐릭터명, String 직업, int 레벨) {
        // SD 명세: 플레이어체크(id)
        if (playerMngr.플레이어체크(플레이어id)) {
            // SD 명세: 직업 분기
            if ("전사".equals(직업)) {
                this.내캐릭터 = new 전사(캐릭터명, 레벨);
            } else if ("마법사".equals(직업)) {
                this.내캐릭터 = new 마법사(캐릭터명, 레벨);
            }
            return "캐릭터 생성 성공";
        } else {
            return "캐릭터 생성 실패 (올바르지 않은 플레이어)";
        }
    }

    // CD 명세: +몬스터공격(String 플레이어id) String
    public String 몬스터공격(String 플레이어id) {
        if (playerMngr.플레이어체크(플레이어id)) {
            if (this.내캐릭터 == null) return "공격 실패 (캐릭터 없음)";

            double 데미지 = 0.0;
            
            // SD 명세: 현재 캐릭터 직업 확인 후 고유 스킬 발동
            if (this.내캐릭터 instanceof 전사) {
                전사 내전사 = (전사) this.내캐릭터;
                데미지 = 내전사.스킬발동_검휘두르기();
            } else if (this.내캐릭터 instanceof 마법사) {
                마법사 내마법사 = (마법사) this.내캐릭터;
                데미지 = 내마법사.스킬발동_파이어볼();
            }

            String 등급 = "";
            // SD 명세: critical 데미지 등급 부여
            if (데미지 >= 200) {
                등급 = "S급 공격";
            } else if (데미지 >= 100) {
                등급 = "A급 공격";
            } else {
                등급 = "B급 공격";
            }

            return "데미지: " + 데미지 + ", 등급: " + 등급;
        } else {
            return "공격 실패 (올바르지 않은 플레이어)";
        }
    }

    // CD 명세: +아이템획득(...) String
    public String 아이템획득(String 플레이어id, String 아이템명, String 아이템타입, int 아이템가치) {
        if (playerMngr.플레이어체크(플레이어id)) {
            if (this.내캐릭터 == null) return "아이템 획득 실패 (캐릭터 없음)";

            // SD 명세: 캐릭터인벤토리 참조
            인벤토리 inv = this.내캐릭터.get캐릭터인벤토리();
            
            // SD 명세: Char->>Inv: 아이템추가(...)
            boolean 추가성공 = inv.아이템추가(아이템명, 아이템타입, 아이템가치);

            if (추가성공) {
                return "아이템 획득 성공";
            } else {
                return "인벤토리가 가득 찼습니다";
            }
        } else {
            return "플레이어 인증 실패";
        }
    }

    // CD 명세: +길드가입(String 플레이어id, String 길드명) String
    public String 길드가입(String 플레이어id, String 길드명) {
        if (playerMngr.플레이어체크(플레이어id)) {
            if (this.내캐릭터 == null) return "길드 가입 실패 (캐릭터 없음)";

            // SD 명세: 기존에 생성된 길드 객체 참조 후 가입 진행
            boolean 가입성공 = this.기본길드.캐릭터가입(this.내캐릭터);

            if (가입성공) {
                return "길드 가입 성공";
            } else {
                return "길드 정원이 가득 찼습니다";
            }
        } else {
            return "플레이어 인증 실패";
        }
    }

    // [실행을 위한 살] UI 출력을 위한 Getter
    public 캐릭터 get내캐릭터() { return this.내캐릭터; }
    public 길드 get기본길드() { return this.기본길드; }
}