```mermaid
sequenceDiagram
    autonumber
    actor Player as 플레이어
    participant UI as Join_Guild_UI
    participant Battle as 전투
    participant PClass as 플레이어(Class)
    participant Guild as 길드
    participant Char as 캐릭터

    Player->>UI: 길드 가입 요청 (id, 길드명)
    UI->>Battle: 길드가입(id, 길드명)
    
    Battle->>PClass: 플레이어체크(id)
    PClass-->>Battle: boolean (true)
    
    alt 체크 성공
        Battle->>Guild: 캐릭터가입(캐릭터 객체)
        Note over Guild: 기존에 생성된 길드 객체 참조
        
        alt 길드 정원 < 5
            Guild->>Guild: 캐릭터리스트 추가
            Guild-->>Battle: true (가입 완료)
            Battle-->>UI: "길드 가입 성공"
        else 길드 정원 가득 참 (>= 5)
            Guild-->>Battle: false (정원 초과)
            Battle-->>UI: "길드 정원이 가득 찼습니다"
        end
    else 체크 실패
        Battle-->>UI: "플레이어 인증 실패"
    end