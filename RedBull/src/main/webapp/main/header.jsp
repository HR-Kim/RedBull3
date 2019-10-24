<%@page import="kr.co.redbull.user.service.UserService"%>
<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
    
<html lang="ko">
  <head>
  	<!-- meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon -->
	<link rel="shortcut icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon">
	<%-- <link rel="icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon"> --%>

    <title>집순이 쇼핑몰</title>
    
      <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="${context}/resources/css/bootstrap.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/linericon/style.css" />
	  <link rel="stylesheet" href="${context}/resources/css/font-awesome.min.css" />
	  <link rel="stylesheet" href="${context}/resources/css/themify-icons.css" />
	  <link rel="stylesheet" href="${context}/resources/css/flaticon.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/owl-carousel/owl.carousel.min.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/lightbox/simpleLightbox.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/nice-select/css/nice-select.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/animate-css/animate.css" />
	  <link rel="stylesheet" href="${context}/resources/vendors/jquery-ui/jquery-ui.css" />
	  <!-- main css -->
	  <link rel="stylesheet" href="${context}/resources/css/style.css" />
	  <link rel="stylesheet" href="${context}/resources/css/responsive.css" />
	  
    <!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
    
  </head>
  
<body> 

  <!--================Header Menu Area =================-->
  <header class="header_area">
    <div class="main_menu">
    
	  	<div class="top_menu" style="background: #ffffff;">
	      <div class="container">
	        <div class="row">
	          <div class="col-lg-7">
	          </div>
	          <div class="col-lg-5">
	            <div class="float-right">
	              <ul class="right_side">
					<c:choose>
	                  	<c:when test="${(user != null) && (user.lvl == 'MANAGER') }"> <!-- 세션 값이 있고, 관리자일 경우 -->
			                <li>
			                  <a href="${context}/product/do_product_mng.do">
			                                   상품 등록
			                  </a>
			                </li>
			                <li class="nav-item">
		                    	<a>${user.uname}님 환영합니다.</a>
		                  	</li>
	                	</c:when>
	                	<c:when test="${(user != null) && (user.lvl != 'MANAGER') }"> <!-- 세션 값이 있고, 일반 사용자일 경우 -->
			                <li class="nav-item">
		                    	${user.uname}님 환영합니다.
		                  	</li>
	                	</c:when>
	                </c:choose>
	              </ul>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	    
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
                      aria-expanded="false"><spring:message code="message.header.store" />
                    </a> <!-- 다국어: 스토어 -->
                    <ul class="dropdown-menu">
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_retrieve.do"><spring:message code="message.header.store_category" /></a>
                      </li> <!-- 다국어: 카테고리 -->
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_newList.do"><spring:message code="message.header.store_new" /></a>
                      </li> <!-- 다국어: 신상 -->
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_rankList.do"><spring:message code="message.header.store_ranking" /></a>
                      </li> <!-- 다국어: 랭킹 -->
                      <li class="nav-item">
                        <a class="nav-link" href="${context}/product/get_hotSaleList.do"><spring:message code="message.header.store_sale" /></a>
                      </li> <!-- 다국어: 핫세일 -->
                    </ul>
                  </li>
                
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/board/get_retrieve.do?searchDiv=20"><spring:message code="message.header.qna" /></a>
                  </li> <!-- 다국어: 질문과답변 -->
                  
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/board/get_retrieve.do?searchDiv=10"><spring:message code="message.header.notice" /></a>
                  </li> <!-- 다국어: 공지사항 -->
                                    
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/user/get_updateForm.do" >임시 회원수정</a>
                  </li>
                  
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/mypage/mypage.jsp" >임시 MP</a>
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
                    <a href="${context}/cart/get_retrieve.do" class="icons">
                      <i class="ti-shopping-cart"></i> <!-- 장바구니/로그인(인터셉터) -->
                    </a>
                  </li>
                  
                  <c:choose>
                  	<c:when test="${user != null }"> <!-- 세션 값이 있을 때만 보여줌 -->
	                  <li class="nav-item">
	                    <a href="#" class="icons" id="logoutBtn">
	                      <i class="ti-shift-right"></i> <!-- 로그아웃 -->
	                    </a>
	                  </li>
                  	</c:when>
                  </c:choose>

                  <li class="nav-item">
                    <a href="${context}/fakemypage/get_fakemypage.do" class="icons"> <!-- 마이페이지/로그인(인터셉터) -->
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
  
  <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  
  <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
  <script src="${context}/resources/js/bootstrap.min.js"></script>   
  
  <!-- Optional JavaScript -->
  <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="${context}/resources/js/jquery-3.2.1.min.js"></script>
  <script src="${context}/resources/js/popper.js"></script>
  <script src="${context}/resources/js/bootstrap.min.js"></script>
  <script src="${context}/resources/js/stellar.js"></script>
  <script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
  <script src="${context}/resources/vendors/nice-select/js/jquery.nice-select.min.js"></script>
  <script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
  <script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
  <script src="${context}/resources/vendors/owl-carousel/owl.carousel.min.js"></script>
  <script src="${context}/resources/js/jquery.ajaxchimp.min.js"></script>
  <script src="${context}/resources/vendors/counter-up/jquery.waypoints.min.js"></script>
  <script src="${context}/resources/vendors/counter-up/jquery.counterup.js"></script>
  <script src="${context}/resources/js/mail-script.js"></script>
  <script src="${context}/resources/js/theme.js"></script>
  
  <script type="text/javascript">
    
		// 로그아웃
		$("#logoutBtn").on("click", function() {
	
			//alert("logoutBtn");
			if(confirm("로그아웃 하시겠습니까?")) {
				
        		location.href="${context}/login/do_logout.do";
        		alert("로그아웃되었습니다.")
        		location.href="${context}/main/main.do"; // 메인 화면으로 이동
        		
			}
			else {
				
				// do nothing
			}
	
		});
		
  </script>
  
</body>
</html>