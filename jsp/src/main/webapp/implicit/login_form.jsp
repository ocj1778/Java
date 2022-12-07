<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="login_action.jsp" method="post" name="loginForm">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="button" id="loginBtn">로그인</button></td>
		</tr>
	</table>
	</form>
	<p id="message" style="color: red;"></p>
	
	<script type="text/javascript">
	loginForm.id.focus();
	
	document.getElementById("loginBtn").onclick=function() {
		if(loginForm.id.value=="") {
			document.getElementById("message").innerHTML="아이디를 입력해 주세요.";
			loginForm.id.focus();
			return;
		}
		
		if(loginForm.passwd.value=="") {
			document.getElementById("message").innerHTML="비밀번호를 입력해 주세요.";
			loginForm.passwd.focus();
			return;
		}
		
		loginForm.submit();
	}
	</script>
</body>
</html>








