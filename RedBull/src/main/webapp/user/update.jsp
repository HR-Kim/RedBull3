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
	
	<title>회원정보 수정</title>
	
	<!-- Font Icon -->
    <link rel="stylesheet" href="${context}/resources/fonts/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="${context}/resources/vendors/nouislider/nouislider.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="${context}/resources/css/joinstyle.css">

</head>

<body>

    <div class="main">

        <div class="container">
            <div class="signup-content">
            
                <div class="signup-img">
                    <div class="signup-img-content">
                        <h2>Register now </h2>
                        <p>while seats are available !</p>
                    </div>
                </div>
                
                <div class="signup-form">
                
                    <form method="GET" class="register-form" id="update-form" name="update-form">
                        <div class="form-row">
                        
                            <div class="form-group">
                                <div class="form-input">
                                    <label for="rid" class="required">아이디</label>
                                    <input type="text" name="rid" id="rid" placeholder="이메일주소 " maxlength="320" value="${user.rid}" disabled="disabled"/>
                                </div>
                                <div class="form-input">
                                    <label for="passwd" class="required">비밀번호</label>
                                    <input type="password" name="passwd" id="passwd" placeholder="20자 이하 영문 대소문자/숫자" maxlength="20" value="${user.passwd}"/>
                                </div>
                                <div class="form-input">
                                    <label for="passwdchk" class="required">비밀번호 확인</label>
                                    <input type="password" name="passwdchk" id="passwdchk" placeholder="비밀번호 확인 " maxlength="20" value="${user.passwd}"/>
                                </div>
                                <div class="form-input">
                                    <label for="uname" class="required">이름</label>
                                    <input type="text" name="uname" id="uname" placeholder="이름 " maxlength="17" value="${user.uname}"/>
                                </div>
                                <div class="form-input">
                                    <label for="birth" class="required">생년월일</label>
                                    <input type="text" name="birth" id="birth" placeholder="YYYY-MM-DD" maxlength="10" value="${user.birth}"/>
                                </div>
                                
                            </div>
                            
                            <div class="form-group">
                                <div class="form-input">
                                    <label for="phone" class="required">휴대폰 번호</label>
                                    <input type="text" name="phone" id="phone" placeholder="010-1111-1111" maxlength="13" value="${user.phone}"/>
                                </div>
<!--                                 <div class="form-input">
                                    <label for="phone_number" class="required">우편 번호</label>
                                    <input type="text" name="postnum" id="postnum" placeholder="5자리 숫자"/>
                                </div>
                                <div class="form-input">
                                    <label for="phone_number" class="required">주소</label>
                                    <input type="text" name="address" id="address" placeholder="주소"/>
                                </div>
                                <div class="form-input">
                                    <label for="phone_number">상세 주소</label>
                                    <input type="text" name="detadd" id="detadd" placeholder="상세주소"/>
                                </div> -->
                                <div class="form-input">
                                    <label for="postnum" class="required" readonly="readonly" >우편번호</label>
                                    <input class="form-control" style="width: 50%; height: 50px; display: inline;" type="text" name="postnum" id="postnum" placeholder="우편번호" maxlength="5" value="${user.postnum}"/>
                                    <button type="button" 
                                    style="height:45px; width:130px; font-family: 'Poppins'; font-weight: bold; 
                                    padding: 5px; border: none; border-radius: 5px" 
                                    onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
                                </div>
                                <div class="form-input">
                                    <label for="address" class="required" readonly="readonly" >주소</label>
                                    <input type="text" name="address" id="address" placeholder="주소" value="${user.address}"/>
                                </div>
                                <div class="form-input">
                                    <label for="detadd" >상세 주소</label>
                                    <input type="text" name="detadd" id="detadd" placeholder="상세 주소" maxlength="100" value="${user.detadd}"/>
                                </div>
                                
                                <!-- hidden values -->
                                <div class="form-input">
                                    <input type="hidden" name="lvl" id="lvl" placeholder="레벨" value="${user.lvl}"/>
                                </div>
                                <div class="form-input">
                                    <input type="hidden" name="upoint" id="upoint" placeholder="포인트" value="${user.upoint}"/>
                                </div>
                                
                            </div>
                            
                        </div>


                    </form>
                    <div class="form-submit" style="margin: 10px 20px 30px 40px;">
                        <input type="submit" value="수정" class="submit" id="userUpdate" name="update" />
                        <input type="submit" value="초기화" class="submit" id="userReset" name="reset" />
                        <input type="submit" value="회원탈퇴" class="submit" id="userDelete" name="delete" />
                    </div>
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

    <!-- JS -->
    <script src="${context}/resources/vendors/jquery/jquery.min.js"></script>
    <script src="${context}/resources/vendors/nouislider/nouislider.min.js"></script>
    <script src="${context}/resources/vendors/wnumb/wNumb.js"></script>
    <script src="${context}/resources/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="${context}/resources/vendors/jquery-validation/dist/additional-methods.min.js"></script>
    <script src="${context}/resources/js/main.js"></script>
    
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script type="text/javascript">
    
    	// 우편번호 조회
	    function execPostCode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	               // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
	               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	               var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
	               var extraRoadAddr = ''; // 도로명 조합형 주소 변수
	
	               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                   extraRoadAddr += data.bname;
	               }
	               // 건물명이 있고, 공동주택일 경우 추가한다.
	               if(data.buildingName !== '' && data.apartment === 'Y'){
	                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	               }
	               // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	               if(extraRoadAddr !== ''){
	                   extraRoadAddr = ' (' + extraRoadAddr + ')';
	               }
	               // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
	               if(fullRoadAddr !== ''){
	                   fullRoadAddr += extraRoadAddr;
	               }
	
	               // 우편번호와 주소 정보를 해당 필드에 넣는다.
	               console.log(data.zonecode);
	               console.log(fullRoadAddr);
	               
	               
	               $("[name=postnum]").val(data.zonecode);
	               $("[name=address]").val(fullRoadAddr);
	               
	               /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
	               document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	               document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	           }
	        }).open();
	    }
    	
		// 수정
		$("#userUpdate").on("click", function() {
			
			alert("userUpdate");
			
			if(confirm("수정하시겠습니까?") == false) return;
			
	        $.ajax({
	            type:"GET",
	            url:"${context}/user/do_update.do",
	            dataType:"html",// JSON
	            data:{
	            	"rid": $("#rid").val(),
	            	"passwd": $("#passwd").val(),
	            	"uname": $("#uname").val(),
	            	"birth": $("#birth").val(),
	            	"phone": $("#phone").val(),
	            	"postnum": $("#postnum").val(),
	            	"address": $("#address").val(),
	            	"detadd": $("#detadd").val(),
	            	"lvl": $("#lvl").val(),
	            	"upoint": $("#upoint").val()
	            },
	            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	            	
	             	console.log(data); // {"msgId":"1","msgMsg":"1234님 수정성공"}
	             
	            	var parseData = $.parseJSON(data);
	            	//var parseData = JSON.parse(data);
	            	
	            	if(null != parseData && parseData.msgId == "1") { // 성공하면
	            		
	            		alert(parseData.msgMsg); // 메시지값 
	            		location.href="${context}/main/main.do";

	            	}
	            	else { // 실패하면
	            		
	            		alert(parseData.msgMsg); // 메시지값 
	            	}

	            },
	            complete: function(data){//무조건 수행

	             
	            },
	            error: function(xhr,status,error){
	             
	            	alert("error: " + error)
	            }
	        }); 
			
		});
		
		// 초기화
		$("#userReset").on("click", function() {
	
			//alert("doInit");
			
			// input 데이터 클리어
			$("#rid").val("");
			$("#passwd").val("");
			$("#uname").val("");
			$("#birth").val("");
			$("#phone").val("");
			$("#postnum").val("");
			$("#address").val("");
			$("#detadd").val("");
		
		});
		
		// 삭제
		$("#userDelete").on("click", function() {

			//alert("doDelete");
			
			// id 값을 콘솔에 출력해봄
			console.log("rid: " + $("#rid").val());
			
			// validation
			if(confirm("탈퇴하시겠습니까?") == false) return;
			
			//ajax
			$.ajax({
				type : "POST",
				url : "${context}/user/do_delete.do",
				dataType : "html",
				data : {
					"rid" : $("#rid").val()
				},
				success : function(data) {
					
					var jData = $.parseJSON(data); // String 데이터를 json으로 파싱
					
					if (null != jData && jData.msgId == "1") { // 메소드 수행이 성공하면
						alert(jData.msgMsg); // 성공 메시지 출력하고 
						// 목록으로 이동
						location.href="${context}/main/main.do"; // 목록조회 화면으로 이동
										
					} else { // 실패하면
						
						alert(jData.msgId + "|" + jData.msgMsg); // 실패메시지 출력하고
					}
				},
				complete : function(data) { 

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  

		});

    </script>
    
</body>
</html>