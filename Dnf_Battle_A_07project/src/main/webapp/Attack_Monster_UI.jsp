<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.game.logic.전투" %>
<%@ page import="com.game.model.캐릭터" %>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>몬스터 공격</title>
</head>
<body>
<%
    if (action == null) {
%>
        <h2>몬스터 공격 (Attack_Monster_UI)</h2>
        <form method="post" action="Attack_Monster_UI.jsp">
            <input type="hidden" name="action" value="attack">
            플레이어 ID 확인: <input type="text" name="플레이어id" required><br><br>
            <input type="submit" value="공격하기!">
        </form>
<%
    } else if ("attack".equals(action)) {
        String 플레이어id = request.getParameter("플레이어id");
        
        캐릭터 내캐릭터 = (캐릭터) session.getAttribute("myCharacter");
        
        if (내캐릭터 == null) {
            out.println("<h3>공격 실패 (캐릭터가 없습니다. 먼저 생성해주세요.)</h3>");
        } else {
            전투 전투객체 = new 전투();
            String 결과등급 = 전투객체.몬스터공격(플레이어id, 내캐릭터);
            
            if ("공격 실패".equals(결과등급)) {
%>
                <h3>공격 실패 (올바르지 않은 플레이어)</h3>
<%
            } else {
%>
                <h3>공격 결과: <%= 결과등급 %></h3>
                <p style="color:red; font-weight:bold;"><%= 내캐릭터.스킬명가져오기() %></p>
                <p>(<%= 내캐릭터.get캐릭터명() %>의 공격!)</p>
<%
            }
        }
%>
        <br>
        <a href="Attack_Monster_UI.jsp">다시 공격하기</a> | 
        <a href="Create_Character_UI.jsp">캐릭터 생성으로 돌아가기</a>
<%
    }
%>
</body>
</html>