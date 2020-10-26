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
	// alert("<%=request.getContextPath()%>");
	// alert("${pageContext.request.contextPath}");두개가 같은 값이 나온다.
	 location.href="${pageContext.request.contextPath}/selectAll"
	</script>
</body>
</html>