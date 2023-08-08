<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <nav class="navbar navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="../assets/imgs/logo.svg" alt="">
            </a>
            <div class="socials">
                <a href="javascript:void(0)"><i class="ti-facebook"></i></a>
                <a href="javascript:void(0)"><i class="ti-twitter"></i></a>
                <a href="javascript:void(0)"><i class="ti-pinterest-alt"></i></a>
                <a href="javascript:void(0)"><i class="ti-instagram"></i></a>
                <a href="javascript:void(0)"><i class="ti-youtube"></i></a>
            </div>
        </div>
    </nav> 
    <nav class="navbar custom-navbar navbar-expand-md navbar-light bg-primary sticky-top">
        <div class="container">
            <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">                     
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="no-sidebar.html">레시피</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="single-post.html">맛집</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="single-post.html">상품</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="single-post.html">공유주방</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mypage.do">마이페이지</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="adminpage.do">관리자페이지</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            더보기
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">자유게시판</a>
                            <a class="dropdown-item" href="#">묻고 답하기</a>
                            <!-- <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a> -->
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
<!--  -->                 
                    <form class="d-flex tm-search-form">
            			<input class="form-control tm-search-input" type="search" placeholder="Search" aria-label="Search" style="width:70%;">
            			<button class="btn btn-outline-success tm-search-btn" type="submit"style="width:30%">검색
                		<i class="fas fa-search"></i>
           				</button>
        			</form>
                </ul>
                <div class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="components.html" class="btn btn-dark mt-1 btn-sm">로그인</a><a href="#" class="btn btn-danger mt-1 btn-sm">회원가입</a>
                    </li>
                </div>
                <div class="navbar-nav ml-auto">
                    <li class="nav-item">
                        
                    </li>
                </div>
            </div>
        </div>
    </nav>
</body>
</html>