<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.game.logic.전투" %>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    전투 전투객체 = (전투) session.getAttribute("battleContext");
%>
<!DOCTYPE html>
<html>
<head><title>길드 가입</title></head>
<body>
<%
    if (action == null) {
%>
        <h2>길드 가입 (Join_Guild_UI)</h2>
        <form method="post" action="Join_Guild_UI.jsp">
            <input type="hidden" name="action" value="join">
            플레이어 ID: <input type="text" name="플레이어id" required><br><br>
            가입할 길드명: <input type="text" name="길드명" required><br><br>
            <input type="submit" value="길드 가입!">
        </form>
        <br>
        <a href="index.jsp">[메인탭으로 돌아가기]</a>
<%
    } else if ("join".equals(action)) {
        if (전투객체 == null) {
            out.println("<h3>오류: 캐릭터를 먼저 생성해주세요.</h3>");
        } else {
            String 플레이어id = request.getParameter("플레이어id");
            String 길드명 = request.getParameter("길드명");

            String 결과메시지 = 전투객체.길드가입(플레이어id, 길드명);
%>
            <h3><%= 결과메시지 %></h3>
            <p>현재 길드 인원: <%= 전투객체.get기본길드().get캐릭터리스트().size() %> / 5</p>
<%
        }
%>
        <br>
        <a href="Join_Guild_UI.jsp">다시 가입시도</a> | 
        <a href="index.jsp"><strong>[메인탭으로 돌아가기]</strong></a>
<%
    }
%>
</body>
</html>