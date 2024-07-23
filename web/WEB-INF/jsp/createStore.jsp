<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Store</title>
</head>
<body>
<h2>Create New Store</h2>
<form action="${pageContext.request.contextPath}/stores" method="post">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required /><br/><br/>

    <label for="name">Store Name:</label>
    <input type="text" id="name" name="name" required /><br/><br/>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required /><br/><br/>

    <button type="submit">Create Store</button>
</form>
</body>
</html>
