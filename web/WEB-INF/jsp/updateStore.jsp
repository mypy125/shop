<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Store</title>
</head>
<body>
<h2>Update Store</h2>
<form action="${pageContext.request.contextPath}/stores/${store.id}" method="post">
    <input type="hidden" name="_method" value="put" />

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${store.password}" required /><br/><br/>

    <label for="name">Store Name:</label>
    <input type="text" id="name" name="name" value="${store.name}" required /><br/><br/>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${store.address}" required /><br/><br/>

    <button type="submit">Update Store</button>
</form>
</body>
</html>
