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
        <br>
        <a href="index.jsp">[메인탭으로 돌아가기]</a>
<%
    } else if ("attack".equals(action)) {
        String 플레이어id = request.getParameter("플레이어id");
        전투 전투객체 = (전투) session.getAttribute("battleContext");
        
        if (전투객체 == null || 전투객체.get내캐릭터() == null) {
            out.println("<h3>공격 실패 (캐릭터가 없습니다. 먼저 생성해주세요.)</h3>");
        } else {
            String 결과등급 = 전투객체.몬스터공격(플레이어id);
            
            if (결과등급.startsWith("공격 실패")) {
%>
                <h3><%= 결과등급 %></h3>
<%
            } else {
                캐릭터 내캐릭터 = 전투객체.get내캐릭터();
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
        <a href="index.jsp"><strong>[메인탭으로 돌아가기]</strong></a>
<%
    }
%>
</body>
</html>