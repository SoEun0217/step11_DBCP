<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert("${requestScope.msg}");//request.setAttribute("msg","오류발생했다.");
		history.back();
	</script>
</body>
</html>