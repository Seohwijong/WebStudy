<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.sist.vo.*,java.io.*"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%
	// ok_jsp : 기능처리 (member_ok, update_ok ...)
	// 데이터베이스 처리 => list.jsp
	// 1. 한글 처리
	request.setCharacterEncoding("UTF-8");
	// 1-1 파일 업로드 클래스 생성
	String path="c:\\download";
	int size=1024*1024*100;
	String enctype="UTF-8";
	MultipartRequest mr=new MultipartRequest(request,path,size,enctype,
							new DefaultFileRenamePolicy());
	// 2. 요청 데이터 받기
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	// 3. VO에 묶는다
	DataBoardVO vo=new DataBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	String filename=mr.getOriginalFileName("upload");
	if(filename==null)
	{
		// 업로드가 안된 상태
		vo.setFilename("");
		vo.setFilesize(0);
	}
	else
	{
		// 업로드가 된 상태
		File file=new File(path+"\\"+filename);
		vo.setFilename(filename);
		vo.setFilesize((int)file.length());
	}
	// 4. DAO로 전송
	DataBoardDAO dao=DataBoardDAO.newInstance();
	dao.databoardInsert(vo);
	// 5. 화면이동 (list.jsp)
	response.sendRedirect("list.jsp");
	// 심청이
	// 자료실 제작중(VO에 값을 채워서 전송)
	// 등장 = 자동으로 VO에 값을 채워주는 방법이 존재  
	// #액션태그 <jsp setProperty>
	// 1234
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>