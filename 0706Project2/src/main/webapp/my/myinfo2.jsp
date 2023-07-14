<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="text-center"> <!-- 사이즈 보기 -->
		<h1>내정보 수정</h1>
		</div>
		<table class="table""> <!-- 사이즈 보기 -->
			<tr>
			<th>아이디</th>
			<td><textarea style="height: 30px">${vo.id }</textarea></td>
		</tr>	
		<tr>
			<th>이름</th>
			<td><textarea style="height: 30px">${vo.name }</textarea></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><textarea style="height: 30px">${vo.nickname }</textarea></td>
		</tr>	
		<tr>
			<th>생년월일</th>
			<td><textarea style="height: 30px">${vo.birthday }</textarea></td>
		</tr>		
		<tr>
			<th>이메일</th>
			<td><textarea style="height: 30px">${vo.email }</textarea></td>
		</tr>	
		<tr>
			<th>성별</th>
			<td><textarea style="height: 30px">${vo.sex }</textarea></td>
		</tr>	
		<tr>
			<th>우편번호</th>
			<td><textarea style="height: 30px">${vo.post }</textarea></td>
		</tr>	
		<tr>
			<th>주소</th>
			<td><textarea style="height: 30px;width: 4	00px">${vo.addr1 }</textarea></td>
		</tr>	
		</table>
		<div class="text-right">
		<a href="#" class="btn btn-sm btn-danger">저장</a>
		<a href="mypage.do?mode=1" class="btn btn-sm btn-danger">취소</a>
	</div>
</body>
</html>