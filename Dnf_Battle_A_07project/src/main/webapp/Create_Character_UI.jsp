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
    <title>캐릭터 생성</title>
</head>
<body>
<%
    if (action == null) {
%>
        <h2>캐릭터 생성 (Create_Character_UI)</h2>
        <form method="post" action="Create_Character_UI.jsp">
            <input type="hidden" name="action" value="create">
            플레이어 ID: <input type="text" name="플레이어id" required placeholder="(힌트: hero)"><br><br>
            캐릭터명: <input type="text" name="캐릭터명" required><br><br>
            직업: 
            <select name="직업">
                <option value="전사">전사</option>
                <option value="마법사">마법사</option>
            </select><br><br>
            레벨: <input type="number" name="레벨" min="1" required><br><br>
            <input type="submit" value="생성하기">
        </form>
        <br>
        <a href="index.jsp">[메인탭으로 돌아가기]</a>
<%
    } else if ("create".equals(action)) {
        String 플레이어id = request.getParameter("플레이어id");
        String 캐릭터명 = request.getParameter("캐릭터명");
        String 직업 = request.getParameter("직업");
        int 레벨 = Integer.parseInt(request.getParameter("레벨"));

        전투 전투객체 = new 전투();
        String 결과메시지 = 전투객체.캐릭터생성(플레이어id, 캐릭터명, 직업, 레벨);

        if ("캐릭터 생성 성공".equals(결과메시지)) {
            session.setAttribute("battleContext", 전투객체);
            캐릭터 내캐릭터 = 전투객체.get내캐릭터();
%>
            <h3><%= 결과메시지 %></h3>
            <p>이름: <%= 내캐릭터.get캐릭터명() %></p>
            <p>직업: <%= 직업 %></p>
            <p>HP: <%= 내캐릭터.getHP() %></p>
            <p>공격력: <%= 내캐릭터.get공격력() %></p>
<%
        } else {
%>
            <h3><%= 결과메시지 %></h3>
<%
        }
%>
        <br>
        <a href="Create_Character_UI.jsp">다시 생성하기</a> | 
        <a href="Attack_Monster_UI.jsp">몬스터 공격하러 가기</a> | 
        <a href="index.jsp"><strong>[메인탭으로 돌아가기]</strong></a>
<%
    }
%>
</body>
</html>