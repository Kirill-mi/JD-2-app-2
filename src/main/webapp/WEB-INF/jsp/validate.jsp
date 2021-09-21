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
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.username" var="user_name"/>
    <fmt:message bundle="${loc}" key="local.name_email" var="name_email"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.button.submit" var="submit"/>
    <fmt:message bundle="${loc}" key="local.text.correct_password" var="correct_password"/>
    <fmt:message bundle="${loc}" key="local.text.correct_name" var="correct_name"/>
    <fmt:message bundle="${loc}" key="local.text.sign_in" var="sign_in"/>
    <fmt:message bundle="${loc}" key="local.text.reg_info" var="reg_info"/>
    <fmt:message bundle="${loc}" key="local.text.create_account_text" var="sign_in_text"/>
</head>
<body>
<div class="form-style-10">
    <h1>${sign_in}<span>${sign_in_text}</span></h1>
    <div class="section"><span>1</span> ${reg_info}</div>
    <div class="privacy-policy">
        <c:out value="${sessionScope.registration_status}"/>
        <c:remove var="registration_status" scope="session"/>
        <c:out value="${sessionScope.message}"/>
    </div>
    <div class="inner-wrap">
        <form action="Controller" method="post">
            <label>${email}:<br/>
                <input type="text" placeholder="Enter your email" name="email"></label><br/>
            <label>${password}:<br/>
                <input type="password" placeholder="Enter your password" name="pass"></label> <br/>
            <input type="hidden" name="command" value="validation"><br/>
            <input type="submit" value=${submit}>
        </form>
        <br/> <br/> <br/> <br/>
        <div class="">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="local"/>
                <input type="hidden" name="location" value="ru"/>
                <input type="submit" value="${ru_button}"/><br/>
            </form>
            <br/>
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="local"/>
                <input type="hidden" name="location" value="en"/>
                <input type="submit" value="${en_button}"/><br/>
            </form>
        </div>
    </div>
</div>
</body>
</html>