<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<h2>Update Product</h2>
<form action="${pageContext.request.contextPath}/products/${product.id}" method="post">
    <input type="hidden" name="_method" value="put" />

    <label for="code">Product Code:</label>
    <input type="text" id="code" name="code" value="${product.code}" required /><br/><br/>

    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" value="${product.name}" required /><br/><br/>

    <label for="price">Price:</label>
    <input type="number" step="0.01" id="price" name="price" value="${product.price}" required /><br/><br/>

    <label for="productionDate">Production Date:</label>
    <input type="date" id="productionDate" name="productionDate" value="${product.productionDate}" required /><br/><br/>

    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" value="${product.expirationDate}" /><br/><br/>

    <button type="submit">Update Product</button>
</form>
</body>
</html>
