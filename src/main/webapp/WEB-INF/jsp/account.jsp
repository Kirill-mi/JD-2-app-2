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
    <fmt:message bundle="${loc}" key="local.account" var="account"/>
    <fmt:message bundle="${loc}" key="local.text.account" var="account_text"/>
    <fmt:message bundle="${loc}" key="local.button.account" var="account_button"/>
    <fmt:message bundle="${loc}" key="local.button.edit.profile" var="profile_edit_button"/>
    <fmt:message bundle="${loc}" key="local.button.edit.news" var="news_edit_button"/>
    <fmt:message bundle="${loc}" key="local.account.name" var="account_name"/>
    <fmt:message bundle="${loc}" key="local.account.email" var="account_email"/>
    <fmt:message bundle="${loc}" key="local.account.role" var="account_role"/>
</head>
<body>
<div class="form-style-10">
    <h1>${account}<span>${account_text}</span>
        <span> <c:set var="userd" value="${sessionScope.user}"/>
        <c:out value="${account_name}"/>
        <c:out value="${userd.name}"/><br/>
        <c:out value="${account_email}"/>
        <c:out value="${userd.email}"/><br/>
        <c:out value="${account_role}"/>
        <c:out value="${userd.role}"/><br/>
      </span>
    </h1>
    <div class="button-section">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_edit_profile"><br/>
            <input type="submit" value=${profile_edit_button}>
        </form>
        <c:if test="${userd.role eq 'user'}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="news_edit"><br/>
                <input type="submit" value=${news_edit_button}>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>