<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<h2>Create New Product</h2>
<form action="${pageContext.request.contextPath}/products" method="post">
    <label for="code">Product Code:</label>
    <input type="text" id="code" name="code" required /><br/><br/>

    <label for="name">Product Name:</label>
    <input type="text" id="name" name="name" required /><br/><br/>

    <label for="price">Price:</label>
    <input type="number" step="0.01" id="price" name="price" required /><br/><br/>

    <label for="productionDate">Production Date:</label>
    <input type="date" id="productionDate" name="productionDate" required /><br/><br/>

    <label for="expirationDate">Expiration Date:</label>
    <input type="date" id="expirationDate" name="expirationDate" /><br/><br/>

    <button type="submit">Create Product</button>
</form>
</body>
</html>
