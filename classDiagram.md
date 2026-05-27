```mermaid
classDiagram
    class Add_Item_UI {
        <<boundary>>
    }
    class Join_Guild_UI {
        <<boundary>>
    }

    class 플레이어 {
        +플레이어체크(String 플레이어id) boolean
    }

    class 캐릭터 {
        <<abstract>>
        -String 캐릭터명
        -int 레벨
        -int HP
        -int 공격력
        -인벤토리 캐릭터인벤토리
        +스킬발동() double
    }

    class 전사 {
        +스킬발동_검휘두르기() double
    }

    class 마법사 {
        +스킬발동_파이어볼() double
    }

    class 인벤토리 {
        -int 최대용량
        -ArrayList 아이템리스트
        +아이템추가() boolean
    }

    class 아이템 {
        -String 아이템명
        -String 타입
        -int 가치
        -String 등급
    }

    class 길드 {
        -String 길드명
        -int 최대인원
        -ArrayList 캐릭터리스트
        +캐릭터가입() boolean
    }

    class 전투 {
        +캐릭터생성(String 플레이어id, String 캐릭터명, String 직업, int 레벨) String
        +몬스터공격(String 플레이어id) String
        +아이템획득(String 플레이어id, String 아이템명, String 아이템타입, int 아이템가치) String
        +길드가입(String 플레이어id, String 길드명) String
    }

    전사 --|> 캐릭터 : extends
    마법사 --|> 캐릭터 : extends

    캐릭터 *--> 인벤토리
    인벤토리 *--> 아이템
    길드 o--> 캐릭터

    Add_Item_UI ..> 전투 : uses
    Join_Guild_UI ..> 전투 : uses
    전투 ..> 플레이어 : references
    전투 ..> 캐릭터 : references
    전투 ..> 길드 : references