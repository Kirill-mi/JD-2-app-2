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
    <fmt:message bundle="${loc}" key="local.button.read_news" var="read_news_button"/>
    <fmt:message bundle="${loc}" key="local.button.update_news" var="update_news_button"/>
    <fmt:message bundle="${loc}" key="local.button.delete_news" var="delete_news_button"/>
    <fmt:message bundle="${loc}" key="local.button.comment_news" var="comment_news_button"/>
</head>
<body>
<div class="form-style-10">
    <h1>${news}<span>${news_text}</span></h1>
    <div class="section"> ${sessionScope.newsAttribute.title}</div>
    <div class="section">   ${sessionScope.newsAttribute.author} ${sessionScope.newsAttribute.date}</div>
    ${sessionScope.newsAttribute.text}
    <div class="button-section">
        <c:set var="userOnPage" value="${sessionScope.user}"/>
        <c:if test="${userOnPage.role eq 'user'}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="go_to_update_news"><br/>
                <input type="submit" value=${update_news_button}>
            </form>
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="delete_news"><br/>
                <input type="submit" onclick="return confirm('Are you sure?')"
                       value=${delete_news_button}>
            </form>
        </c:if>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_news"><br/>
            <input type="submit" value=${comment_news_button}>
        </form>
    </div>
</body>
</html>