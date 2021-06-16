<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>안녕</title>
    <meta charset="UTF-8">
</head>
<body>
<div>
    <form action="/result" method="post">
    <div>
        year: <select name="deal_yr">
        <c:forEach begin="2000" end="2020" var="year">
            <option value="${year}">${year}y</option>
        </c:forEach>
        </select>

        month: <select name="deal_mth">
        <c:forEach begin="1" end="12" var="month">
            <option value="${month}">${month}m</option>
        </c:forEach>
    </select>

        location : Daegu
        <select name="excd">
            <c:forEach items="${requestScope.locationList}" var="item">
                <option value="${item.excd}">${item.loc}</option>
            </c:forEach>
        </select>
        <input type="submit" value="search">
    </div>
    </form>
</div>
</body>
</html>