<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Store List</title>
</head>
<body>
<h2>Store List</h2>
<a href="${pageContext.request.contextPath}/stores/create">Create New Store</a>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Products</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stores}" var="store">
        <tr>
            <td><c:out value="${store.name}"/></td>
            <td><c:out value="${store.address}"/></td>
            <td>
                <c:forEach items="${store.products}" var="entry">
                    <c:out value="${entry.key.name}"/> - <c:out value="${entry.value}"/>;<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
