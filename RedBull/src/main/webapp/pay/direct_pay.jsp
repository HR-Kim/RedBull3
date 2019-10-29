<%@page import="kr.co.redbull.cart.service.Cart"%>
<%@page import="kr.co.redbull.pay.service.Pay"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
<%
	User user = (User)session.getAttribute("user");
	//out.println("user: " + user);
	
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
    
     <section class="checkout_area section_gap">
       <div class="container">
        <div class="billing_details">
          <div class="row">
            <div class="col-lg-8">
            <!-- 주문자 조회 --> 
              <h3>주문자</h3>
                <div class="col-md-12 form-group p_star">
             	    이름 <input  type="text" class="form-control" id="user_name" name="user_name" readonly="readonly" value="${user.uname }" />
                </div>
                
                <div class="col-md-12 form-group p_star">
             	        이메일 <input  type="text" class="form-control" id="user_email" name="user_email" readonly="readonly" value="${user.rid }"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  	 휴대전화<input  type="text" class="form-control" id="user_dphone"  name="user_dphone" readonly="readonly" value="${user.phone}"/>
                </div>
                </div>
                
               <!-- 배송지 입력 --> 
              <form class="row contact_form" id="payForm" method="post" >
	              	<c:choose>
		              <c:when test="${list.size()>0 }">
		              <c:set var="sum" value="0"/>
		              	 <c:forEach var="cart" items="${list}">
		               		<c:set var="sum" value="${sum+ (cart.bPrice * (1-cart.discount) + cart.oPrice) * cart.cartCnt + cart.dPrice }"></c:set>
		               	 </c:forEach>
					  </c:when>
					   <c:otherwise></c:otherwise>
				    </c:choose>
				    
				     <input type="hidden" id="amount" name="amount"  value="${(pay.bPrice*(1-pay.discount)+pay.oPrice)*pay.cartCnt+pay.dPrice }" />
              		 <input type="hidden"  name="pName"  value="${pay.pName }" />
              		 <input type="hidden"  name="uName" value="${user.uname }" />
              		  <input type="hidden"  name="oNum" id="oNum" value="${pay.oNum }" />
              		  <input type="hidden"  name="cartCnt" id="cartCnt" value="${pay.cartCnt }" /> 
               <div class="col-lg-8">
               <h3>배송지</h3>
	                <div class="col-md-12 form-group p_star">
	                  <input type="text"  class="form-control" id="dName" name="dName" placeholder="이름"/>
	                </div>
	                
	                <div class="col-md-12 form-group p_star">
	                  <input  type="tel" class="form-control" id="dPhone"  name="dPhone"  placeholder="010-0000-0000" />
	                </div>
	                
					<div class="col-md-12 form-group p_star">
	                  <input type="text" class="form-control" id="dPostNum" name="dPostNum" placeholder="우편번호"  /><br/>
	                  <button type="button" style="height:45px; width:130px; font-family: 'Poppins'; font-weight: bold; 
	                   padding: 5px; border: none; border-radius: 5px" onclick="execPostCode();">
	                   <i class="fa fa-search"></i> 우편번호 찾기</button>
	                </div>
					
	                <div class="col-md-12 form-group p_star">
	                  <input type="text" class="form-control"  id="dAddr" name="dAddr" placeholder="주소" />
	                </div>
	                
	                <div class="col-md-12 form-group p_star">
	                  <input type="text" class="form-control" id="dDetAddr"  name="dDetAddr" placeholder="상세주소"  />
	                </div>
	
	                <div class="col-md-12 form-group">
	                  <textarea  class="form-control"  name="dMemo"  id="dMemo" rows="1" placeholder="배송메모" ></textarea>
	                </div>
              	</div>
              	
                 <!-- 결제 수단 -->
	             <div class="col-lg-4">
	              <div class="order_box">
	                <h2>최종 결제</h2>
	                <input type="text" style="text-align:center; border:0px;" readonly="readonly" value="<fmt:formatNumber pattern="###,###,###" value="${(pay.bPrice*(1-pay.discount)+pay.oPrice)*pay.cartCnt+pay.dPrice }" />" />
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
		           <div>
	               		<input type="submit" class="main_btn"  id="payment" value="결제하기" />
	               </div>
	             </div>
	            </div>
	            </form> 
      		</div>
      	</div>
      </div>
   </section>
    <!--================End Checkout Area =================-->
    
    <!-- 우편번호 API -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
       
     <!-- 결제시스템(아임포트) API -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    
    <!-- jQuery validate -->
	<script src="${context}/resources/js/jquery.validate.js"></script>
    <script src="${context}/resources/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
    <script src="${context}/resources/vendors/jquery-validation/dist/additional-methods.min.js"></script>
    
 	<!-- 결제시스템(아임포트) API -->
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
	   
    <script type="text/javascript">
    
    	 $("#payForm").validate({
 			//validation 종료 후 submit 직전에 할 작업 ex)메세지 표시
 			submitHandler:function(){
 			var f = confirm('결제 하시겠습니까?');
 			if(f){
 				return payment();
 			}else{
 				return false;
 			}
 		},
 			rules:{
 				dName:{
 					required: true
 				},
 				dPhone:{
 					required: true
 				},
 				dPostNum:{
 					required: true
 				},
 				dAddr:{
 					required: true
 				},
 				dDetAddr:{
 					required: true
 				},
 				payMethod:{
 					required: true
 				}
 			},
 			message:{
 				dName:{ 
  					required: "필수 항목입니다."
 				},
 				dPhone:{
 					required: "필수 항목입니다."
 				},
 				dPostNum:{
  					required: "필수 항목입니다."
 				},
 				dAddr:{
 						required: "필수 항목입니다."
 				},
 				dDetAddr:{
 						required: "필수 항목입니다."
 				},
 				payMethod:{
						required: "필수 항목입니다."
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
     
    function payment(){
    	//if(confirm("결제 하시겠습니까?") == false) return;
		var d_name=$("input[name=pName]").val();
		var amount=parseInt($("input[name=amount]").val());
		
		var u_name =$("input[name=uName]").val();
		
		console.log("amount: "+ amount);
		
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
		name: d_name,
		//결제창에서 보여질 이름
		amount: amount,
		//가격
		buyer_email: 'iamport@siot.do',
		buyer_name: u_name,
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
					
	    		} else {
	    			//[3] 아직 제대로 결제가 되지 않았습니다.
	    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	    			}
	    	});
			$.ajax({
	            type:"POST",
	            url:"${context}/pay/direct_save.do",
	            dataType:"html",
	            data:{
	            amount:amount,
	            "dName":$("#dName").val(),
	            "dPhone":$("#dPhone").val(),
	            dPostNum:$("#dPostNum").val(),
	            "dAddr":$("#dAddr").val(),
	            "dDetAddr":$("#dDetAddr").val(),
	            "dMemo":$("#dMemo").val(),
	            "payMethod":$("#payMethod").val(),
	            
	            "oNum": $("#oNum").val(),
	            cartCnt: $("#cartCnt").val()
	            
	           }, 
	         success: function(data){
	        	 location.href="${context}/pay/pay_complete.jsp";
	         },
	         complete:function(data){
	          
	         },
	         error:function(xhr,status,error){
	             alert("error:"+error);
	         }
	        }); //--ajax 
		} else {
			var msg = '결제에 실패하였습니다.';
			//msg += '에러내용 : ' + rsp.error_msg;
			alert(msg);
			}
		
		});
    }
    
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
