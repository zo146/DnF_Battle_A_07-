package com.game.logic;

public class 플레이어 {
    // CD 명세: +플레이어체크(String 플레이어id) boolean
    public boolean 플레이어체크(String 플레이어id) {
        // SD 명세: id == "hero" 일 때 성공(true)
        return "hero".equals(플레이어id);
    }
}