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
    <fmt:message bundle="${loc}" key="local.button.edit.profile" var="edit_profile"/>
    <fmt:message bundle="${loc}" key="local.text.enter_name" var="enter_name"/>
    <c:set var="userOnPage" value="${sessionScope.user}"/>
</head>
<body>
<div class="form-style-10">
    <h1>${edit_profile}<span>${edit_profile}</span></h1>
    <form action="Controller" method="post">
        <div class="section"><span>1</span>${name_email}</div>

        <div class="inner-wrap">
            <label>${user_name}:<br/> <input type="text" placeholder="Enter your name" name="name"> </label> <br/>
            <label>${email}:<br/> <input type="email" placeholder="Enter your email" name="email"
                                         value="${userOnPage.email}" readonly > </label> <br/>
        </div>
        <div class="section"><span>2</span> ${password}</div>
        <div class="inner-wrap">
            <label>${password}:<br/> <input type="password" placeholder="Enter your password" name="pass"> </label><br/>
            <label>${password}:<br/> <input type="password" placeholder="Confirm your password" name="pass_new">
            </label><br/>
        </div>
        <input type="hidden" name="command" value="profile_edit"><br/>
        <input type="submit" value=${submit}>
    </form>
</div>
</body>
</html>
