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
    <fmt:message bundle="${loc}" key="local.button.read" var="read_news"/>
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
        <c:set var="number" scope="request" value="0"/>
        <tr>
            <th>News title</th>
            <th>News brief</th>
            <th>News date</th>
            <th>News author</th>
        </tr>
        <c:forEach var="news" items="${sessionScope.newsArray}">
            <tr>
                <td>${news.title}</td>
                <td>${news.brief}</td>
                <td>${news.date}</td>
                <td>${news.author}</td>
                <td>
                <td><a href="${pageContext.request.contextPath}
        Controller?command=read_news&number=${number}">${read_news}</a></td>
                <c:set var="number" scope="request" value="${number+1}"/>
            </tr>
        </c:forEach>
    </table>

    <table>
        <tr>
            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="${pageContext.request.contextPath}
                        Controller?command=go_to_news&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <c:if test="${requestScope.currentPage != 1}">
        <td><a href="${pageContext.request.contextPath}
        Controller?command=go_to_news&page=${requestScope.currentPage - 1}">Previous</a></td>
    </c:if>

    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
        <td><a href="${pageContext.request.contextPath}
        Controller?command=go_to_news&page=${requestScope.currentPage + 1}">Next</a></td>
    </c:if>
</div>
</body>
</html>