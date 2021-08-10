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
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.username" var="user_name"/>
    <fmt:message bundle="${loc}" key="local.name_email" var="name_email"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.text.correct_password" var="correct_password"/>
    <fmt:message bundle="${loc}" key="local.text.correct_name" var="correct_name"/>
    <fmt:message bundle="${loc}" key="local.text.create_account" var="create_account"/>
    <fmt:message bundle="${loc}" key="local.text.create_account_text" var="create_account_text"/>
    <fmt:message bundle="${loc}" key="local.text.enter_name" var="enter_name"/>

</head>
<body>
<div class="form-style-10">
    <h1>${create_account}<span>${create_account_text}</span></h1>
    <div class="privacy-policy">
        <c:if test="${sessionScope.registration_status eq 'Enter correct name'}">
            <c:out value="${correct_name}"/>
        </c:if>
        <c:if test="${sessionScope.registration_status eq 'Enter correct password'}">
            <c:out value="${correct_password}"/>
        </c:if>
        <c:if test="${sessionScope.registration_status eq 'User exists'}">
            <c:out value="User exists"/>
        </c:if>
        <c:remove var="registration_status" scope="session"/>
    </div>
    <form action="Controller" method="post">
        <div class="section"><span>1</span>${name_email}</div>
        <div class="inner-wrap">
            <label>${user_name}:<br/> <input type="text" placeholder="Enter your name" name="name"> </label> <br/>
            <label>${email}:<br/> <input type="email" placeholder="Enter your email" name="email"> </label> <br/>
        </div>
        <div class="section"><span>2</span> ${password}</div>
        <div class="inner-wrap">
            <label>${password}:<br/> <input type="password" placeholder="Enter your password" name="pass"> </label><br/>
            <label>${password}:<br/> <input type="password" placeholder="Confirm your password" name="pass_new"> </label><br/>
        </div>
        <input type="hidden" name="command" value="registration_new_user"><br/>
        <input type="submit" value=${submit}>
    </form>
</div>
</body>
</html>
