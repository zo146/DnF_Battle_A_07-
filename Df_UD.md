```mermaid

flowchart LR

&#x20;   Player(\[플레이어])

&#x20;   UC1(캐릭터 생성)

&#x20;   UC2(몬스터 공격)

&#x20;   UC3(플레이어 체크)



&#x20;   Player --- UC1

&#x20;   Player --- UC2

&#x20;   UC1 -. "<< include >>" .-> UC3

&#x20;   UC2 -. "<< include >>" .-> UC3

