<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Stock</title>
</head>
<body>
<h2>Update Stock</h2>
<form action="${pageContext.request.contextPath}/stock/${stock.id}" method="post">
    <input type="hidden" name="_method" value="put" />

    <label for="code">Stock Code:</label>
    <input type="text" id="code" name="code" value="${stock.code}" required /><br/><br/>

    <label for="name">Stock Name:</label>
    <input type="text" id="name" name="name" value="${stock.name}" required /><br/><br/>

    <button type="submit">Update Stock</button>
</form>
</body>
</html>
