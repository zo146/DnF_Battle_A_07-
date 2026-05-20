```mermaid
sequenceDiagram
    actor Player as 플레이어
    participant UI as Attack_Monster_UI
    participant Battle as 전투
    participant PlayerMngr as 플레이어
    participant Warrior as 전사
    participant Mage as 마법사

    Player->>UI: 몬스터 공격 요청 (id)
    UI->>Battle: 몬스터공격(id)
    
    %% 플레이어 체크 (Include 관계)
    Battle->>PlayerMngr: 플레이어체크(id)
    
    alt id == "hero" (성공)
        PlayerMngr-->>Battle: true
        
        %% 직업별 스킬 발동 및 데미지 계산
        alt 현재 캐릭터 == 전사
            Battle->>Warrior: 스킬발동_검휘두르기()
            Warrior-->>Battle: 데미지 반환 (공격력 x 1.5)
        else 현재 캐릭터 == 마법사
            Battle->>Mage: 스킬발동_파이어볼()
            Mage-->>Battle: 데미지 반환 (공격력 x 2.0)
        end
        
        %% 데미지 등급 판정
        critical 데미지 등급 부여
            option 데미지 >= 200
                Battle->>Battle: 등급 = "S급 공격"
            option 데미지 >= 100
                Battle->>Battle: 등급 = "A급 공격"
            option 데미지 < 100
                Battle->>Battle: 등급 = "B급 공격"
        end
        
        Battle-->>UI: 공격 결과 반환 (데미지, 등급)
        UI-->>Player: "검 휘두르기! / 파이어볼!" 및 등급 화면 표시
        
    else id != "hero" (실패)
        PlayerMngr-->>Battle: false
        Battle-->>UI: "공격 실패 (올바르지 않은 플레이어)"
        UI-->>Player: 에러 메시지 표시
    end