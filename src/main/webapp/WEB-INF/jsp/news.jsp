<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <title>Pump Project</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="by.kirill.pumpproject.controller.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.news" var="news"/>
    <fmt:message bundle="${loc}" key="local.text.news" var="news_text"/>
    <fmt:message bundle="${loc}" key="local.button.account" var="account_button"/>
</head>
<body>
<div class="form-style-10">
    <h1>${news}<span>${news_text}</span></h1>
    <div class="button-section">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="user_s_account"><br/>
            <input type="submit" value=${account_button}>
        </form>
    </div>
    <table>
        <tr>
            <th>News title</th>
            <th>News brief</th>
            <th>News date</th>
        </tr>
        <c:forEach var="news" items="${requestScope.newsArray}">
            <tr>
                <td>${news.title}</td>
                <td>${news.brief}</td>
                <td>${news.date}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>