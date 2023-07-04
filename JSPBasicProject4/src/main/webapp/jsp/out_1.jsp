<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	버퍼크기 : <%= out.getBuffersize() %><br>
	사용중인 버퍼 크기 : <%= out.getRemaining() %><br>
	현재 사용중인 버퍼 크기 : <%= out.getBufferSize()-out.getRemaining() %>
</body>
</html>