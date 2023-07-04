<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	request / response / session => 웹 개발의 핵심
	-------------------  ------- 프로그램이 실행하는 중
	session은 서버에 저장 : 사용자의 정보를 지속적으로 관리
	-------- 1) 장바구니 , 결재 , 예약 , 추천 ...
			 2) session에 저장이 되면 모든 JSP에서 사용이 가능 (전역변수)
	클래스명 => HttpSession
			  클라이언트마다 1개 생성 => id가 부여 (구분자)
			  						-- sessionId => 채팅 , 상담 ...
	주요메소드
			String getId() : 세션마다 저장 구분자
			setMaxinactiveInterval() => 저장 기간을 설정
			 => 기본 default => 1800 (초단위 : 30분)
			 => 경매
			isNew() : ID가 할당이 된것인지 여부 확인
			 => 장바구니
			invalidate() : session에 저장된 모든 내용을 지운다
						   로그아웃
			setAttribute() : session에 정보 저장
			getAttribute() : 저장된 데이터 읽기
			removeAttribute() : 저장된 데이터 일부를 지울 때 사용
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>HttpSession(Session)</h1>
 <table class="table">
  <tr>
    <th width=20% class="text-center">클래스명</th>
    <td width=80%>HttpSession(session):177page</td>
  </tr>
  <tr>
    <th width=20% class="text-center">주요기능</th>
    <td width=80%>
    	<ul>
    		<li>사용자 요청 정보</li>
    		<li>브라우저 정보 / 서버 정보</li>
    		<li>추가 정보</li>
    	</ul>
    </td>
  </tr>
  <tr>
    <th width=20% class="text-center">주요메소드</th>
    <td width=80%>
		<ul>
			<li>
				사용자 요청 정보
				<ul>
					<li>***String getParameter(String key):단일값을 받을 경우에 사용</li>
					<li>String[] getParameterValues(String key):다중값을 받을 때 사용</li>
					<li>***void setCharacterEncoding():디코딩(인코딩으로 변경)</li>
				</ul>
			</li>
			<li>
				브라우저 정보/서버 정보
				<ul>
					<li>***String getRemoteAddr():사용자의 IP를 얻어온다</li>
					<li>String getServerName():서버명</li>
					<li>***String getRequestURL():URL주소 읽기</li>
					<li>***String getRequestURI():사용자가 요청한 파일</li>
					<li>***String getContextPath():사용자 요청 폴더의 루프</li>
				</ul>
			</li>
			<li>
				추가 정보
				<ul>
					<li>***void setAttribute():데이터를 request에 추가해서 전송</li>
					<li>***Object getAttribute():추가된 데이터 읽기</li>
				</ul>
			</li>
		</ul>
	</td>
  </tr>
 </table>
</body>
</html>