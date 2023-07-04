<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>내장 객체</h1>
	<table class="table">
		<tr>
			<td colspan=2>
			내장객체 : 미리 객체를 생성해서 필요시마다 사용이 가능하게 만들어 준다 <br>
			         => 모든 서버에서 request, response, session ... 지원 <br>
			         => Nodels , JSP , PHP , ASP , DJango
			</td>
		</tr>
		<tr>
		<td colspan=2>
			<pre>public void _jspService(<mark></mark>)
			
			
			</pre>
		</td>
		</tr>
		<tr>
			<td colspan=2>
			<ul>
				<li>JSP파일에서 입출력 관련 객체</li>
				<li>JSP파일에서 외부환경 관련 객체</li>
				<li>JSP파일에서 서블릿 관련 객체</li>
				<li>JSP파일에서 예외관련 객체</li>
			</ul>
			</td>
		</tr>
		<tr>
			<td width=25%>request</td>
			<td width=75%>사용자와 관련(요청정보 , 브라우저 정보 , 추가정보)</td>
		</tr>
		<tr>
			<td width=25%>response</td>
			<td width=75%>서버에서 응답정보 , 화면이동 정보 , 헤더정보</td>
		</tr>
		<tr>
			<td width=25%>out</td>
			<td width=75%>출력 버퍼와 관련 , 화면출력 정보</td>
			<br><sub>출력버퍼 : 실행된 HTML을 저장하는 공간(브라우저에서 읽어가는 공간)</sub>
		</tr>
		<tr>
			<td width=25%>application</td>
			<td width=75%>서버정보 , 자원정보 , 로그정보</td>
		</tr>
		<tr>
			<td width=25%>session</td>
			<td width=75%>서버에 사용자의 일부 정보를 저장 : 전역변수처럼 사용</td>
		</tr>
		<tr>
			<td width=25%>pageContext</td>
			<td width=75%></td>
		</tr>
		<tr>
			<td width=25%></td>
			<td width=75%></td>
		</tr>
		<tr>
			<td width=25%></td>
			<td width=75%></td>
		</tr>
	</table>
</body>
</html>