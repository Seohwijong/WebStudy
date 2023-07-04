<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%--
		JSP
		---
		1) 지시자 : page / include / taglib
						 -----------------
				   page 지시자 : JSP파일에 대한 정보
				   1. contentType : 브라우저에 실행하는 타입
				   					-------------------
				   					html 	=> text/html
				   					xml		=> text/xml
				   					json 	=> text/plain
				   					---------------------
				   					Ajax / Vue / React
				   					--------------------- RestFul
				   2. import : 여러번 사용이 가능 , 라이브러리 읽기
				   3. errorpage : 에러시에 에러 출력 화면으로 이동
				   4. buffer : html 태그를 저장하는 공간
				   			   소스미리보기
				   			   => 사용자마다 한개만 생성 (브라우저가 연결해서 읽어가는 위치)
		2) 스크립트 : 자바 / HTML을 분리해서 소스코딩
		   ------ 자바언언 코딩 위치 (벗어나면 일반 텍스트로 인식)
		   <%! %> : 선언문 (멤버변수 , 메소드선언)
		   			=> 클래스 제작시 클래스 블록 => 사용빈도가 없다
		   			=> 자바 클래스를 만들어서 메소드를 호출
		   <% %> : 일반 메소드 영역 => 지역변수 , 제어문 , 메소드 호출 ...
		   		   _jspService()
		   		   {
		   		   	--------------
		   		   		JSP에 첨부하는 소스
		   		   	--------------
		   		   
		   		   }
		   <%= %> : 화면 출력 (변수 , 문자열 ...) => out.println(<%= %>)
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   out : JSPWriter (출력 버퍼 관리)
		   		 출력 (메모리)
		   		  println() <%= %>
		   		  
		   		 메모리 확인
		   session
		   application
		   pageContext
		   ------------------------- 필수
		   config : web.xml처리 (환경 설정)
		   exception : try ~ catch
		   page : this
 --%>
 <%-- out 출력 (복잡한 HTML구조) print --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->

<!-- </body> -->
<!-- </html> -->
<% 
	EmpDAO dao=new EmpDAO();
	List<EmpVO> list=dao.empListData();
	out.print("<!DOCTYPE html>");
	out.print("<html>");
	out.print("<head>");
	out.print("<meta charset=\"UTF-8\">");
	out.print("<title>Insert title here</title>");
	out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	out.print("<style>");
	out.print(".container{margin-top:50px}");
	out.print(".row{margin:0px auto;width:800px}");
	out.print("h1{text-align:center}");
	out.print("</style>");
	out.print("</head>");
	out.print("<body>");
	out.print("<div class=container>");
	out.print("<h1>사원 목록</h1>");
	out.print("<div class=row>");
	out.print("<table class=\"table table-striped\">");
	out.print("<tr class=danger>");
	out.print("<th class=text-center>사번</th>");
	out.print("<th class=text-center>이름</th>");
	out.print("<th class=text-center>직위</th>");
	out.print("<th class=text-center>입사일</th>");
	out.print("<th class=text-center>급여</th>");
	out.print("<th class=text-center>성과급</th>");
	out.print("<th class=text-center>부서명</th>");
	out.print("<th class=text-center>근무지</th>");
	out.print("<th class=text-center>호봉</th>");
	out.print("</tr>");
	for(EmpVO vo:list)
	{
		out.print("<tr>");
		out.print("<td class=text-center>"+vo.getEmpno()+"</td>");
		out.print("<td class=text-center><a href=\"MainServlet?mode=2&empno="+vo.getEmpno()+"\">"+vo.getEname()+"</a></td>");
		out.print("<td class=text-center>"+vo.getJob()+"</td>");
		out.print("<td class=text-center>"+vo.getDbday()+"</td>");
		out.print("<td class=text-center>"+vo.getDbsal()+"</td>");
		out.print("<td class=text-center>"+vo.getComm()+"</td>");
		out.print("<td class=text-center>"+vo.getDvo().getDname()+"</td>");
		out.print("<td class=text-center>"+vo.getDvo().getLoc()+"</td>");
		out.print("<td class=text-center>"+vo.getSvo().getGrade()+"</td>");
		out.print("</tr>");
	}
	out.print("</table>");
	out.print("</div>");
	out.print("</div>");
	out.print("</body>");
	out.print("</html>");
%>