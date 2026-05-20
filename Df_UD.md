```mermaid
flowchart LR
&#x20;   Player --- UC1
    Player([플레이어])
    UC1(캐릭터 생성)
    UC2(몬스터 공격)
    UC3(플레이어 체크)

    Player --- UC1
    Player --- UC2
    UC1 -. "<< include >>" .-> UC3
    UC2 -. "<< include >>" .-> UC3