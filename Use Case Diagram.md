```mermaid
graph LR
    %% Actor (플레이어)
    Player([플레이어])

    %% Use Cases (유스케이스 정의)
    CreateChar[캐릭터 생성]
    AttackMonster[몬스터 공격]
    GainItem[아이템 획득]
    JoinGuild[길드가입]
    CheckPlayer[플레이어 체크]

    %% Actor to Use Case Relations (액터 연결)
    Player --- CreateChar
    Player --- AttackMonster
    Player --- GainItem
    Player --- JoinGuild

    %% Include Relations (포함 관계)
    CreateChar -. "<< include >>" .-> CheckPlayer
    AttackMonster -. "<< include >>" .-> CheckPlayer
    GainItem -. "<< include >>" .-> CheckPlayer
    JoinGuild -. "<< include >>" .-> CheckPlayer

    %% 스타일링 (원형 느낌을 주기 위한 설정)
    style Player fill:#E6E6FA,stroke:#9370DB,stroke-width:2px
    style CreateChar fill:#F0F0FF,stroke:#9370DB,stroke-width:1px
    style AttackMonster fill:#F0F0FF,stroke:#9370DB,stroke-width:1px
    style GainItem fill:#F0F0FF,stroke:#9370DB,stroke-width:1px
    style JoinGuild fill:#F0F0FF,stroke:#9370DB,stroke-width:1px
    style CheckPlayer fill:#F0F0FF,stroke:#9370DB,stroke-width:1px