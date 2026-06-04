<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.game.logic.전투" %>
<%@ page import="com.game.model.아이템" %>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
    전투 전투객체 = (전투) session.getAttribute("battleContext");
%>
<!DOCTYPE html>
<html>
<head><title>아이템 획득</title></head>
<body>
<%
    if (action == null) {
%>
        <h2>아이템 획득 (Add_Item_UI)</h2>
        <form method="post" action="Add_Item_UI.jsp">
            <input type="hidden" name="action" value="add">
            플레이어 ID: <input type="text" name="플레이어id" required><br><br>
            아이템명: <input type="text" name="아이템명" required><br><br>
            아이템 타입: 
            <select name="아이템타입">
                <option value="무기">무기</option>
                <option value="방어구">방어구</option>
                <option value="물약">물약</option>
            </select><br><br>
            아이템 가치: <input type="number" name="아이템가치" required><br><br>
            <input type="submit" value="아이템 획득!">
        </form>
        <br>
        <a href="index.jsp">[메인탭으로 돌아가기]</a>
<%
    } else if ("add".equals(action)) {
        if (전투객체 == null) {
            out.println("<h3>오류: 캐릭터를 먼저 생성해주세요.</h3>");
        } else {
            String 플레이어id = request.getParameter("플레이어id");
            String 아이템명 = request.getParameter("아이템명");
            String 아이템타입 = request.getParameter("아이템타입");
            int 아이템가치 = Integer.parseInt(request.getParameter("아이템가치"));

            String 결과메시지 = 전투객체.아이템획득(플레이어id, 아이템명, 아이템타입, 아이템가치);
%>
            <h3><%= 결과메시지 %></h3>
            <p>현재 인벤토리 아이템 개수: <%= 전투객체.get내캐릭터().get캐릭터인벤토리().get아이템리스트().size() %> / 10</p>
<%
        }
%>
        <br>
        <a href="Add_Item_UI.jsp">다시 획득하기</a> | 
        <a href="index.jsp"><strong>[메인탭으로 돌아가기]</strong></a>
<%
    }
%>
</body>
</html>