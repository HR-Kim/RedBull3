<%@ page language="java" contentType="text/html; charset=EUC-KR"   pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 
<head>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
 
<body>
    <script type="text/javascript">
          var naver_id_login = new naver_id_login("{TyqyGR4or2iouEKEGY6D}", "http://localhost:8080/redbull/login/callback.jsp");
          // 접근 토큰 값 출력
          //alert(naver_id_login.oauthParams.access_token);
          
          // 네이버 사용자 프로필 조회
          naver_id_login.get_naver_userprofile("naverSignInCallback()");
          
          // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
          function naverSignInCallback() {
	          var id = naver_id_login.getProfileData('email');
	          var name = naver_id_login.getProfileData('name');
            
    		$.ajax({
            	type:"POST",
                url : "http://localhost:8080/redbull/user/check_id.do", // 네이버 프로필에서 가져온 이메일이 DB에 있는지 확인 
                dataType:"html",// JSON
	            data:{
	            	"rid": id
	            },
	            success: function(data){ //통신이 성공적으로 이루어 졌을때 받을 함수
	            	
	            	console.log(data); 
	            
	            	var jData = JSON.parse(data); // String 데이터를 json으로 파싱
	            	
                     if(jData.msgId == "10"){ //DB에 아이디가 없을 경우 => 회원가입
                    	
                    	 alert("회원가입중...");
                    
                          $.ajax({
                        	  type : "POST",
                              url : "http://localhost:8080/redbull/user/do_save.do",
                              dataType : "html",
	              			  data : {
		          	            	"rid": id,
		        	            	"passwd": "0000",
		        	            	"uname": name,
		        	            	"birth": "0000-00-00",
		        	            	"phone": "000-0000-0000",
		        	            	"postnum": 12345,
		        	            	"address": "0000"
	              			  },
                              success : function(JSONData){
                                 alert("회원가입 완료. 네이버계정으로 로그인이 가능합니다.");
                                 window.close();
                                 //top.opener.location="http://localhost:8080/redbull/login/login.jsp";
                              }
                          })
                      }
                      if(jData.msgId == "30"){ //DB에 아이디가 존재할 경우 => 로그인
                    	  
                    	  //alert("로그인 중...");
                          //window.close();
                          
	              			//ajax-1
	              			$.ajax({
	              				type : "POST",
	              				url : "http://localhost:8080/redbull/user/get_selectOne.do", // 네이버 프로필에서 가져온 이메일로 단건조회(비밀번호 가져와야 함)
	              				dataType : "html",
	              				data : {
	              					"rid": id
	              				},
	              				success : function(selData) { 
	              					
	              					var SelJData = JSON.parse(selData); // String 데이터를 json으로 파싱
	              					
	              					alert("네이버 " + SelJData.rid + "로 로그인 완료");
	              					
	              					if(null != SelJData) { // 데이터가 있으면(단건조회 해옴)
	              					
	        	              			//ajax-2
	        	              			$.ajax({
	        	              				type : "POST",
	        	              				url : "http://localhost:8080/redbull/login/do_login.do", // 로그인
	        	              				dataType : "html",
	        	              				data : {
	        	              					"lang": 'ko',
	        	              					"rid": id,
	        	              					"passwd": SelJData.passwd
	        	              				},
	        	              				success : function(selData) { 
	        	              					
	        	              					window.close();
	        	              					top.opener.location="http://localhost:8080/redbull/main/main.do"; // 메인 화면으로 이동

	        	              				},
	        	              				complete : function(selData) { 
	        	
	        	              				},
	        	              				error : function(xhr, status, error) {
	        	              					alert("error:" + error);
	        	              				}
	        	              			});
	        	              			//--ajax-2            				
	              					
	              					}
	              				},
	              				complete : function(selData) { 
	
	              				},
	              				error : function(xhr, status, error) {
	              					alert("error:" + error);
	              				}
	              			});
	              			//--ajax-1 
                          
                      }
                  }
            })
            
          }
    </script>
    
    <form name="defaultForm">
    </form>
    
</body>
</html>