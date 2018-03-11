<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>LogIn</title>
</head>
<body>
	<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
		<input id="username" type="text" name="username" placeholder="Email" required> 
		<input id="password" type="password" name="password" placeholder="Password" required>
		<button type="submit">log in</button>
	</form>
	<a href="<c:url value="/"/>">home</a><br/>
</body>
</html>