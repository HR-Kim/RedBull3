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
          // ���� ��ū �� ���
          //alert(naver_id_login.oauthParams.access_token);
          
          // ���̹� ����� ������ ��ȸ
          naver_id_login.get_naver_userprofile("naverSignInCallback()");
          
          // ���̹� ����� ������ ��ȸ ���� ������ ������ ó���� callback function
          function naverSignInCallback() {
	          var id = naver_id_login.getProfileData('email');
	          var name = naver_id_login.getProfileData('name');
            
    		$.ajax({
            	type:"POST",
                url : "http://localhost:8080/redbull/user/check_id.do", // ���̹� �����ʿ��� ������ �̸����� DB�� �ִ��� Ȯ�� 
                dataType:"html",// JSON
	            data:{
	            	"rid": id
	            },
	            success: function(data){ //����� ���������� �̷�� ������ ���� �Լ�
	            	
	            	console.log(data); 
	            
	            	var jData = JSON.parse(data); // String �����͸� json���� �Ľ�
	            	
                     if(jData.msgId == "10"){ //DB�� ���̵� ���� ��� => ȸ������
                    	
                    	 alert("ȸ��������...");
                    
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
                                 alert("ȸ������ �Ϸ�. ���̹��������� �α����� �����մϴ�.");
                                 window.close();
                                 //top.opener.location="http://localhost:8080/redbull/login/login.jsp";
                              }
                          })
                      }
                      if(jData.msgId == "30"){ //DB�� ���̵� ������ ��� => �α���
                    	  
                    	  //alert("�α��� ��...");
                          //window.close();
                          
	              			//ajax-1
	              			$.ajax({
	              				type : "POST",
	              				url : "http://localhost:8080/redbull/user/get_selectOne.do", // ���̹� �����ʿ��� ������ �̸��Ϸ� �ܰ���ȸ(��й�ȣ �����;� ��)
	              				dataType : "html",
	              				data : {
	              					"rid": id
	              				},
	              				success : function(selData) { 
	              					
	              					var SelJData = JSON.parse(selData); // String �����͸� json���� �Ľ�
	              					
	              					alert("���̹� " + SelJData.rid + "�� �α��� �Ϸ�");
	              					
	              					if(null != SelJData) { // �����Ͱ� ������(�ܰ���ȸ �ؿ�)
	              					
	        	              			//ajax-2
	        	              			$.ajax({
	        	              				type : "POST",
	        	              				url : "http://localhost:8080/redbull/login/do_login.do", // �α���
	        	              				dataType : "html",
	        	              				data : {
	        	              					"lang": 'ko',
	        	              					"rid": id,
	        	              					"passwd": SelJData.passwd
	        	              				},
	        	              				success : function(selData) { 
	        	              					
	        	              					window.close();
	        	              					top.opener.location="http://localhost:8080/redbull/main/main.do"; // ���� ȭ������ �̵�

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