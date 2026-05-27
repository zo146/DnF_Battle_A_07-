```mermaid
sequenceDiagram
    autonumber
    actor Player as 플레이어
    participant UI as Add_Item_UI
    participant Battle as 전투
    participant PClass as 플레이어(Class)
    participant Char as 캐릭터
    participant Inv as 인벤토리
    participant Item as 아이템

    Player->>UI: 아이템 정보 입력 (id, 이름, 타입, 가치)
    UI->>Battle: 아이템획득(id, 이름, 타입, 가치)
    
    Battle->>PClass: 플레이어체크(id)
    PClass-->>Battle: boolean (true)
    
    alt 체크 성공
        Battle->>Char: 캐릭터인벤토리 참조
        Char->>Inv: 아이템추가(이름, 타입, 가치)
        
        alt 인벤토리 용량 < 10
            Inv->>Item: new 아이템(이름, 타입, 가치, 등급계산)
            Note over Item: 가치에 따라 등급 부여<br/>(Legendary/Rare/Common)
            Inv->>Inv: 아이템리스트 추가
            Inv-->>Char: true (추가 완료)
            Char-->>Battle: 성공 메시지 반환
            Battle-->>UI: "아이템 획득 성공"
        else 인벤토리 가득 참 (>= 10)
            Inv-->>Char: false
            Char-->>Battle: 용량 초과 에러
            Battle-->>UI: "인벤토리가 가득 찼습니다"
        end
    else 체크 실패 (id != "hero")
        Battle-->>UI: "플레이어 인증 실패"
    end