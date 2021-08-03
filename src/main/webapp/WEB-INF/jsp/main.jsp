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
    <fmt:message bundle="${loc}" key="local.main_page" var="main_page"/>
    <fmt:message bundle="${loc}" key="local.main_page_text" var="main_page_text"/>
    <fmt:message bundle="${loc}" key="local.well.characteristics" var="well_characteristics"/>
    <fmt:message bundle="${loc}" key="local.system.characteristics" var="system_characteristics"/>
    <fmt:message bundle="${loc}" key="local.well.flow" var="well_flow"/>
    <fmt:message bundle="${loc}" key="local.well.static_level" var="static_level"/>
    <fmt:message bundle="${loc}" key="local.well.dynamic_level" var="dynamic_level"/>
    <fmt:message bundle="${loc}" key="local.well.specific_flow" var="specific_flow"/>
    <fmt:message bundle="${loc}" key="local.well.pressure" var="pressure"/>
    <fmt:message bundle="${loc}" key="local.pump.pressure" var="pump_pressure"/>
</head>
<div class="form-style-10">
    <h1>${main_page}<span>${main_page_text}</span></h1>
    <form action="Controller" method="post">
        <div class="section"><span>1</span>${well_characteristics}</div>
        <div class="inner-wrap">
            <label>${well_flow}<br/>
                <input type="text" name="wellFlowRate" pattern="^[0-9]*[.,]?[0-9]+$"
                       title="This field should only contain numbers"></label><br/>
            <label>${static_level}<br/>
                <input type="text" name="staticLevel" pattern="^[0-9]*[.,]?[0-9]+$"
                       title="This field should only contain numbers"></label><br/>
            <label>${dynamic_level}<br/>
                <input type="text" name="dynamicLevel" pattern="^[0-9]*[.,]?[0-9]+$"
                       title="This field should only contain numbers"></label><br/>
            <label>${specific_flow}<br/>
                <input type="text" name="specificFlowRate"
                       pattern="^[0-9]*[.,]?[0-9]+$" title="This field should only contain numbers"></label><br/>
            <label>${pressure}<br/>
                <input type="text" name="requiredPressure" pattern="^[0-9]*[.,]?[0-9]+$"
                       title="This field should only contain numbers"></label><br/>
        </div>
        <div class="section"><span>2</span>${system_characteristics}</div>
        <div class="inner-wrap">
            <label>${pump_pressure}<br/>
                <input type="text" name="pumpPerformance" pattern="^[0-9]*[.,]?[0-9]+$"
                       title="This field should only contain numbers"></label><br/>
            <input type="hidden" name="command" value="VALIDATION_USER"><br/>
            <input type="submit" value=${submit}>
        </div>
    </form>
</div>
</body>
</html>