<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
<%
	User user = (User)session.getAttribute("user");
	out.println("user: " + user);
	
%>
<html lang="ko">
  <body>
    <!--================Header Menu Area =================-->
 	<jsp:include page="/main/header.jsp"></jsp:include>
    <!--================Header Menu Area =================-->

    <!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center">
        <div class="container">
          <div
            class="banner_content d-md-flex justify-content-between align-items-center">
            <div class="mb-3 mb-md-0">
              <h2>결제창</h2>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->

    <!--================Checkout Area =================-->
    
   <%--  <form name="updateForm" id="updateForm" class="user">
        <input type="hidden" name="work_div" id="work_div"/>
        	<!--  <form class="user"> -->
                    <div class="form-group">
                      <input type="text" value="${rid }" readonly="readonly" name="mod_id" id="mod_id" maxlength="20" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                      <input type="password" value="${uname }"  name="mod_passwd" id="mod_passwd" size="20" maxlength="20" class="form-control form-control-user" placeholder="비밀번호">
                    </div>
                     <div class="form-group">
                      <input type="password"  name="mod_repasswd" id="mod_repasswd" class="form-control form-control-user" placeholder="비밀번호 확인" >
                    </div>
                     <div class="form-group">
                      <input type="text" value="${uname }" name="mod_name" id="mod_name" size="30" maxlength="30" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="이름">
                    </div>
                    <div class="form-group">
                      <input type="email" value="${email }" readonly= "readonly" name="email"  id="email" size="100" maxlength="150" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                     	등록일 <input type="text" readonly= "readonly" value="${reg_dt }" name="reg_dt" id="reg_dt" size="10" maxlength="10" class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                     	 수정일<input type="text" readonly= "readonly" value="${mod_dt }" name="mod_dt" id="mod_dt" size="10" maxlength="10"  class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <hr>
                    <input type="submit" value="수정완료"  class="btn btn-primary btn-user btn-block" />
                    <input type="button" value="계정삭제" id="del_btn" class="btn btn-primary btn-user btn-block" />
       		  	</form> --%>
     <section class="checkout_area section_gap">
       <div class="container">
        <div class="billing_details">
          <div class="row">
          <form class="row contact_form" action="#" method="post" novalidate="novalidate">
            <div class="col-lg-8">
            <!-- 주문자 조회 --> 
              <h3>주문자</h3>
                <div class="col-md-12 form-group p_star">
             	    이름 <input  type="text" class="form-control" id="user_email" name="compemailany" readonly="readonly" value="${user.uname }" />
                </div>
                
                <div class="col-md-12 form-group p_star">
             	        이메일 <input  type="text" class="form-control" id="user_email" name="compemailany" readonly="readonly" value="${user.rid }"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  	 휴대전화<input  type="text" class="form-control" id="user_dphone"  name="dphone" readonly="readonly" value="${user.phone}"/>
                </div>
                </div>
                
               <!-- 배송지 입력 --> 
               <div class="col-lg-8">
               <h3>배송지</h3>
              
                <div class="col-md-12 form-group p_star">
                  <input type="text"  class="form-control" id="dName" name="dName" placeholder="이름"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="dPhone"  name="dPhone"  placeholder="전화번호"/>
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" name="dPostNum" id="dPostNum" name="compemailany" placeholder="우편번호"/><br/>
                  <button type="button" style="height:45px; width:130px; font-family: 'Poppins'; font-weight: bold; 
                   padding: 5px; border: none; border-radius: 5px" onclick="execPostCode();">
                   <i class="fa fa-search"></i> 우편번호 찾기</button>
                </div>
				
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control"  id="dAddr" name="dAddr" placeholder="주소"/>
                  <!-- <span  class="placeholder" data-placeholder="주소1" ></span> -->
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="dDetAddr"  name="dDetAddr" placeholder="상세주소" />
                 <!--  <span class="placeholder" data-placeholder="주소2" ></span> -->
                </div>

                <div class="col-md-12 form-group">
                  <textarea  class="form-control"  name="dMemo"  id="dMemo" rows="1" placeholder="배송메모" ></textarea>
                </div>
              </div>
                 <!-- 결제 수단 -->
	             <div class="col-lg-4">
	              <div class="order_box">
	                <h2>최종 결제</h2>
	                <ul class="list list_2">
	                  <li>
	                    	최종 결제 금액
	                      <span>1111 </span>
	                  </li>
	                </ul>
	                <div class="payment_item">
	                    <h2>결제 수단</h2>
	                </div>
	                <div class="payment_item active">
	                  <div class="radion_btn">
	                    <input type="radio" name="payMethod" id="payMethod" value="카드"/>
	                    <label for="payMethod">카드결제 </label>
	                    <div class="check"></div>
	                  </div>
	                   <img src="${context}/resources/img/product/single-product/card.jpg" alt="" /> 
	                </div>
	                <a class="main_btn" type="button" id="payment" >결제하기</a>
	                </div>
	              </div>
              </form>
          </div>
        </div>
      </div>
   <!--  </section>
    <!--================End Checkout Area =================-->
    <!-- 우편번호 API -->
       <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
       
    <!-- 결제시스템(아임포트) API -->
       <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	   <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
	   
    <script type="text/javascript">
    
    $("#payment").on("click",function(){
		if(confirm("결제 하시겠습니까?") == false) return;
    	
    	$.ajax({
            type:"POST",
            url:"${context}/pay/do_save.do",
            dataType:"html",
            data:{
            //"amount":$("#amount").val(),
            "dName":$("#dName").val(),
            "dPhone":$("#dPhone").val(),
            "dPostNum":$("#dPostNum").val(),
            "dAddr":$("#dAddr").val(),
            "dDetAddr":$("#dDetAddr").val(),
            "dMemo":$("#dMemo").val(),
            "payMethod":$("#payMethod").val(),
           }, 
         success: function(data){

        	 location.href="${context}/pay/pay_complete.jsp";
         },
         complete:function(data){
          
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
        //--ajax 
    	
    	
    }
    
    $("#payment11111").on("click",function(){
    	
    	//alert("결제 시도!");
		var IMP = window.IMP; // 생략가능
		IMP.init('imp91347096');
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		IMP.request_pay({
		pg: 'inicis', // version 1.1.0부터 지원.
		/*
		'kakao':카카오페이,
		html5_inicis':이니시스(웹표준결제)
		'nice':나이스페이
		'jtnet':제이티넷
		'uplus':LG유플러스
		'danal':다날
		'payco':페이코
		'syrup':시럽페이
		'paypal':페이팔
		*/
		pay_method: 'card',
		/*
		'samsung':삼성페이,
		'card':신용카드,
		'trans':실시간계좌이체,
		'vbank':가상계좌,
		'phone':휴대폰소액결제
		*/
		merchant_uid: 'merchant_' + new Date().getTime(),
		/*
		merchant_uid에 경우
		https://docs.iamport.kr/implementation/payment
		위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
		참고하세요.
		나중에 포스팅 해볼게요.
		*/
		name: '주문명:결제테스트',
		//결제창에서 보여질 이름
		amount: 1000,
		//가격
		buyer_email: 'iamport@siot.do',
		buyer_name: '구매자이름',
		buyer_tel: '010-1234-5678',
		buyer_addr: '서울특별시 강남구 삼성동',
		buyer_postcode: '123-456'
		/*
		모바일 결제시,
		결제가 끝나고 랜딩되는 URL을 지정
		(카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
		*/
		}, function (rsp) {
		console.log(rsp);
		if (rsp.success) {
			jQuery.ajax({
	    		url: "/payments/complete", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
	    		type: 'POST',
	    		dataType: 'json',
	    		data: {
		    		imp_uid : rsp.imp_uid
		    		//기타 필요한 데이터가 있으면 추가 전달
	    		}
	    	}).done(function(data) {
	    		if ( everythings_fine ) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
					
					alert(msg);
					dosave();
	    		} else {
	    			//[3] 아직 제대로 결제가 되지 않았습니다.
	    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	    			}
	    	});

		} else {
			var msg = '결제에 실패하였습니다.';
			//msg += '에러내용 : ' + rsp.error_msg;
			alert(msg);
			}
		
		});
		
    });
    
    function dosave(){
    	//alert("payment");
    	
    	//if(confirm("결제 하시겠습니까?") == false) return;
    	
    	$.ajax({
            type:"POST",
            url:"${context}/pay/do_save.do",
            dataType:"html",
            data:{
            //"amount":$("#amount").val(),
            "dName":$("#dName").val(),
            "dPhone":$("#dPhone").val(),
            "dPostNum":$("#dPostNum").val(),
            "dAddr":$("#dAddr").val(),
            "dDetAddr":$("#dDetAddr").val(),
            "dMemo":$("#dMemo").val(),
            "payMethod":$("#payMethod").val(),
           }, 
         success: function(data){

        	 location.href="${context}/pay/pay_complete.jsp";
         },
         complete:function(data){
          
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
        //--ajax 
    	
    };
    
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
	               
	               
	               $("[name=dPostNum]").val(data.zonecode);
	               $("[name=dAddr]").val(fullRoadAddr);
	               
	               /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
	               document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	               document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	           }
	        }).open();
	    }
    
    </script>

    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
  </body>
</html>

