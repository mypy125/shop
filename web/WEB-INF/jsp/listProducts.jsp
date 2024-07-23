<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h2>Product List</h2>
<a href="${pageContext.request.contextPath}/products/create">Create New Product</a>
<table border="1">
    <thead>
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Price</th>
        <th>Production Date</th>
        <th>Expiration Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:out value="${product.code}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
            <td><fmt:formatDate value="${product.productionDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${product.expirationDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
