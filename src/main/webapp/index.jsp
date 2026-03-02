<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="is.production" var="isProd"/>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>봉동중앙교회 초등부 RPG</title>
</head>
<c:choose>
	<c:when test="${isProd eq 'true'}">
		<script>
			location.href = 'https://www.bjcc-ss.kr/std/idx/intro.pg';
		</script>
	</c:when>
	<c:otherwise>
		<script>
			location.href = '/std/idx/intro.pg';
		</script>
	</c:otherwise>
</c:choose>
<body>
<!-- do something -->
</body>
</html>