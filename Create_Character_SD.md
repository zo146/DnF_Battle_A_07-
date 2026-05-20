```mermaid
sequenceDiagram
    actor Player as 플레이어
    participant UI as Create_Character_UI
    participant Battle as 전투
    participant PlayerMngr as 플레이어
    participant Warrior as 전사
    participant Mage as 마법사

    Player->>UI: 캐릭터 생성 요청 (id, 이름, 직업, 레벨)
    UI->>Battle: 캐릭터생성(id, 이름, 직업, 레벨)
    
    %% 플레이어 체크 (Include 관계)
    Battle->>PlayerMngr: 플레이어체크(id)
    alt id == "hero" (성공)
        PlayerMngr-->>Battle: true
        
        %% 직업별 생성 분기
        alt 직업 == "전사"
            Battle->>Warrior: 캐릭터 객체 생성 (HP = 레벨x100, 공격력 = 레벨x15)
            Warrior-->>Battle: 생성 완료
        else 직업 == "마법사"
            Battle->>Mage: 캐릭터 객체 생성 (HP = 레벨x60, 공격력 = 레벨x25)
            Mage-->>Battle: 생성 완료
        end
        
        Battle-->>UI: "캐릭터 생성 성공"
        UI-->>Player: 생성 성공 메시지 표시
        
    else id != "hero" (실패)
        PlayerMngr-->>Battle: false
        Battle-->>UI: "캐릭터 생성 실패 (올바르지 않은 플레이어)"
        UI-->>Player: 에러 메시지 표시
    end