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
            <div class="col-lg-8">
            <!-- 주문자 조회 --> 
              <h3>주문자</h3>
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
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
                
<!-- 				<div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="email" name="compemailany" placeholder="이메일" />
                </div> -->
                
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
	                    <a href="#">최종 결제 금액
	                      <span><fmt:formatNumber pattern="###,###,###" value="${amount }"/> </span>
	                    </a>
	                  </li>
	                </ul>
	                <div class="payment_item">
	                    <h2>결제 수단</h2>
	                </div>
	                <div class="payment_item active">
	                  <div class="radion_btn">
	                    <input type="radio" name="payMethod" id="payMethod"/>
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
       <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    
    $("#payment").on("click",function(){
    	alert("payment");
    	
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
           var jData = JSON.parse(data);
           if(null != jData && jData.msgId=="1"){
             alert(jData.msgMsg);
             location.href="${context}/pay/pay_complete.jsp";

           }else{
             alert(jData.msgId+"|"+jData.msgMsg);
           }
         },
         complete:function(data){
          
         },
         error:function(xhr,status,error){
             alert("error:"+error);
         }
        }); 
        //--ajax  
    	
    });
    
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

