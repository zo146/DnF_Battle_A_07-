package com.game.logic;

public class 플레이어 {
    // 다이어그램의 플레이어체크 메서드
    public boolean 플레이어체크(String 플레이어id) {
        // 플레이어 ID가 "hero"일 때만 true 반환
        return "hero".equals(플레이어id);
    }
}