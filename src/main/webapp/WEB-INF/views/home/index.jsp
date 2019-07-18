<%--
  Created by IntelliJ IDEA.
  User: hezepeng
  Date: 2019-07-18
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="item" items="${userList}">
    ${item.username}
    <fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate>
</c:forEach>
</body>
</html>
