<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Stock</title>
</head>
<body>
<h2>Create New Stock</h2>
<form action="${pageContext.request.contextPath}/stock" method="post">
    <label for="code">Stock Code:</label>
    <input type="text" id="code" name="code" required /><br/><br/>

    <label for="name">Stock Name:</label>
    <input type="text" id="name" name="name" required /><br/><br/>

    <button type="submit">Create Stock</button>
</form>
</body>
</html>
