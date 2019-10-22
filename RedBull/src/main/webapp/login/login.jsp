<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />    
     
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    
    <!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/animate/animate.css">
	<!--===============================================================================================-->	
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/select2/select2.min.css">
	<!--===============================================================================================-->	
		<link rel="stylesheet" type="text/css" href="${context}/resources/vendors/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${context}/resources/css/util.css">
		<link rel="stylesheet" type="text/css" href="${context}/resources/css/main.css">
	<!--===============================================================================================-->
    
    <title>로그인</title>

    <!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

 
  </head>

<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">

				<form class="login100-form validate-form" name="loginForm" id="loginForm" action="${context}/login/do_login.do" method="post">
<!-- 					<span class="login100-form-title p-b-33">
						집순이 쇼핑몰
					</span> -->
					<a class="login100-form-title p-b-33" href="${context}/main/main.do">
		            	<img src="${context}/resources/img/logo3.png" style="max-width: 100%; height: auto;" />
		            </a>
					
					<div class="login100-form-title p-b-33">
						<select name="lang" id="lang" class="wrap-input100 validate-input" style="width:100px;font-size:16px;float:right">
							<option value="ko">한국어</option>
							<option value="en">영어</option>
						</select>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Valid id is required: ex@abc.xyz">
						<input class="input100" type="text" name="rid" id="rid" placeholder=이메일주소>
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="passwd" id="passwd" placeholder=비밀번호>
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
				</form>
				
					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" id="signIn">
							로그인
						</button>
					</div>
					
					<!-- 네이버 로그인 창으로 이동 -->
					<div class="container-login100-form-btn m-t-20" id="naver_id_login" style="text-align:center"><a href="${url}">
					<img width="50" src="${context}/resources/img/naver.png"/></a></div>
					<br>

					<div class="text-center p-t-45 p-b-4">
						<a href="${context}/user/find.jsp" class="txt2 hov1">
							비밀번호 찾기
						</a>
					</div>

					<div class="text-center">
						<a href="${context}/user/join.jsp" class="txt2 hov1">
							회원가입
						</a>
					</div>
				
			</div>
		</div>
	</div>

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <!-- jQuery validate -->
	<script src="${context}/resources/js/jquery.validate.js"></script>
    
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>   
    
    <script type="text/javascript">
        
        function do_login() {
        
        	var frm = document.loginForm;
        	
        	// validation
        	frm.submit();
        	
        }
        
        $("#signIn").on("click", function(){
        	
        	//alert("signIn");
        	
			// validation
			if($("#loginForm").valid() == false) return; // validation이 false이면 수행 안 함
			
			//ajax
			$.ajax({
				type : "POST",
				url : "${context}/login/do_login.do",
				dataType : "html",
				data : {
					"lang" : $("#lang").val(),
					"rid" : $("#rid").val(),
					"passwd" : $("#passwd").val()
				},
				success : function(data) {
					
					var jData = JSON.parse(data); // String 데이터를 json으로 파싱
					
					if(null != jData) { // 데이터가 있으면
						
						if (jData.msgId == "30" && null != jData.msgMsg) { // 로그인이 성공하고 msgMsg의 값이 있으면(등업을 했으면)

							alert(jData.msgMsg); // 등업 메시지를 출력하고
							location.href="${context}/main/main.do"; // 메인 화면으로 이동
						}
						else if (jData.msgId == "30" && null == jData.msgMsg) { // 로그인이 성공하고 msgMsg의 값이 없으면(등업을 안 함)
							
							location.href="${context}/main/main.do"; // 메인 화면으로 이동				
						}
						else if (jData.msgId == "10") { // 아이디 체크 실패
							
							$("#rid").focus();
							alert(jData.msgMsg);		
						}
						else if (jData.msgId == "20") { // 비밀번호 체크 실패
							
							$("#passwd").focus();
							alert(jData.msgMsg);
						}	
					
					}
				},
				complete : function(data) { 

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  
			
        	//do_login();
        	
        });
        
		// form validate
		$("#loginForm").validate({
			rules: {
				rid: {
					required: true,
					minlength: 2,
					maxlength: 320
				},
				passwd: {
					required: true,
					minlength: 2,
					maxlength: 20
				}
			},
			messages: {
				rid: {
					required: "id를 입력하시오.",
					minlength: $.validator.format("{0}자 이상 입력하시오"),
					maxlength: $.validator.format("{0}자 내로 입력하시오")
				},
				passwd: {
					required: "비밀번호를 입력하시오.",
					minlength: $.validator.format("{0}자 이상 입력하시오"),
					maxlength: $.validator.format("{0}자 내로 입력하시오")
				}
			},
			errorPlacement : function(error, element) {
			     //do nothing
			},
		    invalidHandler : function(form, validator) {
		    	
			     var errors = validator.numberOfInvalids();
			     
				     if (errors) {
				    	 
					      alert(validator.errorList[0].message);
					      validator.errorList[0].element.focus();
			     	 }
			}

		});
        
    	$(document).ready(function(){
    		//alert("ready");	
    	});
    
    
    </script>

</body>
</html>