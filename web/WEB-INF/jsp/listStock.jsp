<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Stock List</title>
</head>
<body>
<h2>Stock List</h2>
<a href="${pageContext.request.contextPath}/stock/create">Create New Stock</a>
<table border="1">
    <thead>
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Products</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stocks}" var="stock">
        <tr>
            <td><c:out value="${stock.code}"/></td>
            <td><c:out value="${stock.name}"/></td>
            <td>
                <c:forEach items="${stock.products}" var="entry">
                    <c:out value="${entry.key.name}"/> - <c:out value="${entry.value}"/>;<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
