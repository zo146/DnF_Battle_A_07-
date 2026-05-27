<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.game.logic.전투" %>
<%@ page import="com.game.model.캐릭터" %>
<%@ page import="com.game.model.아이템" %>
<%@ page import="java.util.ArrayList" %>
<%
    request.setCharacterEncoding("UTF-8");
    // 세션 영역에 저장된 전투 컨텍스트 객체 인양
    전투 전투객체 = (전투) session.getAttribute("battleContext");
    캐릭터 내캐릭터 = (전투객체 != null) ? 전투객체.get내캐릭터() : null;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>던전앤파이터 시스템 v2.0</title>
    <style>
        body { font-family: 'Malgun Gothic', sans-serif; margin: 30px; background-color: #fafafa; color: #333; }
        h2 { color: #4A148C; border-bottom: 3px solid #7B1FA2; padding-bottom: 10px; }
        h3 { margin-top: 0; color: #6A1B9A; border-bottom: 1px solid #E0E0E0; padding-bottom: 8px; }
        .menu-container { margin-bottom: 40px; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .menu-container a { display: inline-block; margin-right: 15px; padding: 12px 20px; background-color: #7B1FA2; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; transition: background 0.2s; }
        .menu-container a:hover { background-color: #9C27B0; }
        .dashboard { display: flex; gap: 25px; flex-wrap: wrap; }
        .card { background: white; border: 1px solid #E0E0E0; padding: 20px; border-radius: 8px; width: 320px; box-shadow: 0 2px 4px rgba(0,0,0,0.05); }
        .empty-text { color: #9e9e9e; font-style: italic; }
        ul { padding-left: 20px; margin: 10px 0; }
        li { margin-bottom: 5px; }
    </style>
</head>
<body>

    <h2>던전앤파이터 시스템 v2.0 메인 메뉴</h2>
    
    <div class="menu-container">
        <a href="Create_Character_UI.jsp">캐릭터 생성</a>
        <a href="Attack_Monster_UI.jsp">몬스터 공격</a>
        <a href="Add_Item_UI.jsp">아이템 획득</a>
        <a href="Join_Guild_UI.jsp">길드 가입</a>
    </div>

    <h2>실시간 시스템 객체 관리 현황 (대시보드)</h2>
    <div class="dashboard">
        
        <div class="card">
            <h3>캐릭터 정보</h3>
            <% if (내캐릭터 != null) { %>
                <p><strong>캐릭터명:</strong> <%= 내캐릭터.get캐릭터명() %></p>
                <p><strong>레벨:</strong> <%= 내캐릭터.get레벨() %> LV</p>
                <p><strong>HP:</strong> <%= 내캐릭터.getHP() %></p>
                <p><strong>공격력:</strong> <%= 내캐릭터.get공격력() %></p>
                <p><strong>클래스 타입:</strong> <%= (내캐릭터 instanceof com.game.model.전사) ? "전사 (Warrior)" : "마법사 (Mage)" %></p>
            <% } else { %>
                <p class="empty-text">생성된 캐릭터 객체가 존재하지 않습니다. 캐릭터 생성을 먼저 진행해 주세요.</p>
            <% } %>
        </div>

        <div class="card">
            <h3>인벤토리 현황 (Composition)</h3>
            <% if (내캐릭터 != null && 내캐릭터.get캐릭터인벤토리() != null) { 
                ArrayList<아이템> 아이템리스트 = 내캐릭터.get캐릭터인벤토리().get아이템리스트();
            %>
                <p><strong>현재 용량:</strong> <%= 아이템리스트.size() %> / 10 칸</p>
                <p><strong>보유 아이템 명단:</strong></p>
                <% if (아이템리스트.isEmpty()) { %>
                    <p class="empty-text">인벤토리가 비어 있습니다.</p>
                <% } else { %>
                    <ul>
                        <% for (아이템 단일아이템 : 아이템리스트) { %>
                            <li>
                                <strong><%= 단일아이템.get아이템명() %></strong> 
                                (<%= 단일아이템.get타입() %>) 
                                <br><small>가치: <%= 단일아이템.get가치() %> / 등급: <%= 단일아이템.get등급() %></small>
                            </li>
                        <% } %>
                    </ul>
                <% } %>
            <% } else { %>
                <p class="empty-text">활성화된 인벤토리가 없습니다.</p>
            <% } %>
        </div>

        <div class="card">
            <h3>소속 길드 현황 (Aggregation)</h3>
            <% if (전투객체 != null && 전투객체.get기본길드() != null) { 
                com.game.model.길드 명문길드 = 전투객체.get기본길드();
                ArrayList<캐릭터> 길드원들 = 명문길드.get캐릭터리스트();
                boolean 가입여부 = 길드원들.contains(내캐릭터);
            %>
                <p><strong>길드명:</strong> <%= 명문길드.get길드명() %></p>
                <p><strong>길드 정원:</strong> <%= 길드원들.size() %> / 5 명</p>
                <p><strong>본인 가입 상태:</strong> <%= 가입여부 ? "<span style='color:#2E7D32; font-weight:bold;'>가입 완료</span>" : "<span style='color:#C62828;'>미가입</span>" %></p>
                <p><strong>길드원 명단:</strong></p>
                <% if (길드원들.isEmpty()) { %>
                    <p class="empty-text">현재 길드에 가입된 캐릭터가 없습니다.</p>
                <% } else { %>
                    <ul>
                        <% for (캐릭터 원 : 길드원들) { %>
                            <li><%= 원.get캐릭터명() %> (LV.<%= 원.get레벨() %>)</li>
                        <% } %>
                    </ul>
                <% } %>
            <% } else { %>
                <p class="empty-text">초기화된 길드 시스템이 없습니다.</p>
            <% } %>
        </div>
        
    </div>

</body>
</html>