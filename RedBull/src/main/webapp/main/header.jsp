<%@page import="kr.co.redbull.user.service.UserService"%>
<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
    
<html lang="ko">
  <head>
  	<!-- meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon -->
	<link rel="shortcut icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon">
	<link rel="icon" href="${context}/resources/img/favicon3.ico" type="image/x-icon">

    <title>집순이 쇼핑몰</title>
    
      <!-- Bootstrap CSS -->
<%-- 	  <link rel="stylesheet" href="${context}/resources/css/bootstrap.css" /> --%>
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
    <div class="top_menu">
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
                  
                  <li class="nav-item">
                    <a class="nav-link" href="${context}/login/login.jsp">임시 로그인</a>
                  </li>
                  
                  <li class="nav-item">
                    <a class="nav-link" href="#" id="userUpdateBtn">임시 회원수정</a>
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
                    <a href="${context}/mypage/get_retrieve.do" class="icons"> <!-- 마이페이지/로그인(인터셉터) -->
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
    
		function logout() {
			
			if(confirm("로그아웃 하시겠습니까?")) {
				
        		location.href="${context}/login/do_logout.do";
			}
		}
		
		// 초기화
		$("#logoutBtn").on("click", function() {
	
			//alert("logoutBtn");
			logout();
			location.href="${context}/main/main.do"; // 메인 화면으로 이동

		});
		
		//-----------------------------------------------------------------
		
		// 회원정보수정 버튼 클릭: 회원 단건조회
		$("#userUpdateBtn").on("click", function() {

			var rid = '<%=session.getAttribute("rid")%>'
			alert(rid);
			
 			//ajax
			$.ajax({
				type : "GET",
				url : "${context}/user/get_selectOne.do",
				dataType : "html",
				data : {
					"rid" : rid
				},
				success : function(data) {
					
					//var jData = JSON.parse(data); // String 데이터를 json으로 파싱
					
					if(null != data) { // 데이터가 있으먼

						location.href="${context}/user/get_updateForm.do"; 
	
					}
					else {
						
						location.href="${context}/main/main.do";
					}
				},
				complete : function(data) { 

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  
			
/* 			user.action = "${context}/user/get_selectOneUpdate.do";
			
			user.submit(); */
			
/* 			user.submit();
			
			location.href="${context}/user/get_selectOne.do"; 
			
			
 			$.ajax({
	            type:"POST",
	            url:"${context}/user/get_selectOne.do",
	            dataType:"html",// JSON
	            data:{
	            	"rid": user.rid
	            },
	            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	             	console.log(data); // 선택한 데이터 전체 출력
	             	
	            	var parseData = $.parseJSON(data);
	            	
	            	console.log(parseData.rid); // 선택한 데이터 아이디만 출력
	            	
	            	$("#rid").val(parseData.rid); // 선택한 데이터의 아이디값이 입력 폼에 들어옴
	            	$("#passwd").val(parseData.passwd);
	            	$("#uname").val(parseData.uname);
	            	$("#birth").val(parseData.birth);
	            	$("#phone").val(parseData.phone);
	            	$("#postnum").val(parseData.postnum);
	            	$("#address").val(parseData.address);
	            	$("#detadd").val(parseData.detadd);
	            	$("#lvl").val(parseData.lvl);
	            	$("#upoint").val(parseData.upoint);
	            	
	            	$("#rid").prop("disabled", true);
	            		
	            },
	            
	            	complete: function(data){//무조건 수행
	
	             
	            },
	            	error: function(xhr,status,error){
	             
	            }
            
        	});  */

		});
		
  </script>
  
</body>
</html>