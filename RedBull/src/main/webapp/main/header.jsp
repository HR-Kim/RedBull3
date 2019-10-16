<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
	
	//User sessionUser = (User)session.getAttribute("user"); 

%>     
<html lang="ko">
  <head>
  	<!-- meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
	.carousel-control.left, .carousel-control.right {
	  left: 0;
	  z-index: 1;
	}
	</style>
    <!-- favicon -->
	<link rel="shortcut icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon">
	<link rel="icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon">

    <title>집순이 쇼핑몰</title>
    
      <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="${context}/resources/css/bootstrap.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/linericon/style.css" />
	  <link rel="stylesheet" href="${context}/resources/css/font-awesome.min.css" />
	  <link rel="stylesheet" href="${context}/resources/css/themify-icons.css" />
	  <link rel="stylesheet" href="${context}/resources/css/flaticon.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/lightbox/simpleLightbox.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/nice-select/css/nice-select.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/animate-css/animate.css" />
	  <!-- main css -->
	  <link rel="stylesheet" href="${context}/resources/css/style.css" />
	  <link rel="stylesheet" href="${context}/resources/css/responsive.css" />
  </head>
  
<body>

  <!--================Header Menu Area =================-->
  <header class="header_area">
    <div class="top_menu">
      <div class="container">
        <div class="row">
          <div class="col-lg-7">
          </div>
          <div class="col-lg-5">
            <div class="float-right">
              <ul class="right_side">
<%--                 <li>
                  <a href="${context}/user/do_update.do">
                  	회원정보 수정
                    <c:out value="${sessionUser.uname }" />
                  </a>
                </li> --%>
                <li>
                  <a href="${context}/product/do_product_mng.do">
                                   상품 등록
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="main_menu">
      <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light w-100">
          <!-- Brand and toggle get grouped for better mobile display -->
          <a class="navbar-brand logo_h" href="${context}/main/main.do">
            <img src="${context}/resources/img/logo3.png" style="max-width: 100%; height: auto;" />
          </a>
          
<!--           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> -->
          <span></span>
          
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse offset w-100" id="navbarSupportedContent">
            <div class="row w-100 mr-0">
              <div class="col-lg-7 pr-0">
                <ul class="nav navbar-nav center_nav">
                          
                  <li class="nav-item active submenu dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                      aria-expanded="false">스토어
                    </a>
                    <ul class="dropdown-menu">
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_retrieve.do">카테고리</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_retrieve.do">신상</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_retrieve.do">랭킹</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_retrieve.do">핫세일</a>
                      </li>
                    </ul>
                  </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/board/get_retrieve.do?searchDiv=20">질문과답변</a>
                  </li>
                  
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/board/get_retrieve.do?searchDiv=10">공지사항</a>
                  </li>
                  
                </ul>
              </div>

              <div class="col-lg-5 pr-0">
                <ul class="nav navbar-nav navbar-right right_nav pull-right">
                  <li class="nav-item">
                    <a href="#" class="icons">
                      <i class="ti-search" aria-hidden="true"></i>
                    </a>
                  </li>

                  <li class="nav-item">
                    <a href="#" class="icons">
                      <i class="ti-shopping-cart"></i>
                    </a>
                  </li>

                  <li class="nav-item">
                    <a href="${context}/login/login.jsp" class="icons">
                      <i class="ti-user" aria-hidden="true"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
  </header>
  <!--================Header Menu Area =================-->
  
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
  <script src="${context}/resources/js/jquery-1.12.4.js"></script>
  <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
  <script src="${context}/resources/js/popper.js"></script>
  <script src="${context}/resources/js/bootstrap.min.js"></script>
  <script src="${context}/resources/js/stellar.js"></script>
  <script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
  <script src="${context}/resources/vendors/nice-select/js/jquery.nice-select.min.js"></script>
  <script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
  <script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
  <script src="${context}/resources/js/jquery.ajaxchimp.min.js"></script>
  <script src="${context}/resources/vendors/counter-up/jquery.waypoints.min.js"></script>
  <script src="${context}/resources/vendors/counter-up/jquery.counterup.js"></script>
  <script src="${context}/resources/js/mail-script.js"></script>
  <script src="${context}/resources/js/theme.js"></script>
</body>
</html>