```mermaid
classDiagram
    class Create_Character_UI {
        <<boundary>>
    }
    class Attack_Monster_UI {
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
        +스킬발동() double
    }

    class 전사 {
        +스킬발동_검휘두르기() double
    }

    class 마법사 {
        +스킬발동_파이어볼() double
    }

    class 전투 {
        +캐릭터생성(String 플레이어id, String 캐릭터명, String 직업, int 레벨) String
        +몬스터공격(String 플레이어id) String
    }

    전사 --|> 캐릭터 : extends
    마법사 --|> 캐릭터 : extends

    Create_Character_UI ..> 전투 : uses
    Attack_Monster_UI ..> 전투 : uses
    전투 ..> 플레이어 : references
    전투 ..> 캐릭터 : references