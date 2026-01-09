<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@page isErrorPage="true" %>
<%
	response.setStatus(200);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>error page</title>
	<meta charset="UTF-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
</head>
<body class="admin">
<div class="container">
	<div class="error-wrap">
		<h2 class="tit-error">
			404<strong>원하시는 페이지를 찾을 수가 없습니다.</strong>
		</h2>
		<p class="txt-error">
			error: ${exception}<br/>홈페이지 관리자에게 문의 하시기 바랍니다.
		</p>
		<div class="util-error">
			<a href="#" class="btn-type02">이전페이지로 이동</a>
		</div>
	</div>
</div>
</body>
</html>