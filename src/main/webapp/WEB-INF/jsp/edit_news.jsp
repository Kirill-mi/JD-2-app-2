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
    <fmt:message bundle="${loc}" key="local.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.button.edit.news" var="edit_news"/>
    <fmt:message bundle="${loc}" key="local.text.new.news" var="new_news"/>
    <fmt:message bundle="${loc}" key="local.text.edit.news" var="edit_news"/>
    <fmt:message bundle="${loc}" key="local.news.text" var="news_text"/>
    <fmt:message bundle="${loc}" key="local.news.title" var="news_title"/>
    <c:set var="userd" value="${sessionScope.user}"/>
</head>
<body>
<div class="form-style-10">
   <h1>${edit_news}<span>${edit_news}</span></h1>
    <div class="section"><span>1</span>${new_news}</div>
    <form action="Controller" method="post" >
        <div class="inner-wrap">
            <label>${news_title}:<br/>
                <textarea name="title" maxlength="200" cols="50" rows="3" required></textarea></label>
            <label>${news_text}:<br/>
                <textarea name="text" maxlength="2000"  cols="50" rows="7" required></textarea><br/>
                <input type="hidden" name="command" value="create_news"><br/>
                <input type="submit" value=${submit}></label>
        </div>
    </form>
    <div class="section"><span>2</span>${edit_news}</div>
    <form action="Controller" method="post">
        <div class="inner-wrap">
            <label>${news_title}:<br/>
                <textarea name="title" maxlength="200" cols="50" rows="3" required></textarea></label>
                <input type="submit" value=${submit}>
        </div>
    </form>
</div>
</body>
</html>
