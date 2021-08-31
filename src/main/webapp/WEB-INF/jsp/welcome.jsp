<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <title>Pump Project</title>

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="by.kirill.pumpproject.controller.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.text.welcome" var="text_welcome"/>
    <fmt:message bundle="${loc}" key="local.welcome" var="welcome"/>
    <fmt:message bundle="${loc}" key="local.button.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.button.authorization" var="authorization"/>
</head>
<body>
<div class="form-style-10">
    <h1>${welcome}<span>${text_welcome}</span></h1>
    <div class="button-section">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_registration">
            <input type="submit" value="${registration}">
        </form>
        <br/>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_authorization">
            <input type="submit" value="${authorization}">
        </form>
    </div>
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
</body>
</html>
