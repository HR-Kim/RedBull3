<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
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
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
                  <input type="text"  class="form-control" id="dname" name="dname" />
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="dphone"  name="dphone" />
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="email" name="compemailany"/>
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="dpostnum" name="compemailany" />
                </div>
				
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control"  id="daddr" name="add1"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="ddetaddr"  name="add2" />
                </div>
                </div>
                <!-- 결제 수단 -->
	             <div class="col-lg-4">
	              <div class="order_box">
	                <h2>최종 결제</h2>
	                <ul class="list list_2">
	                  <li>
	                    <a href="#">상품 총 금액
	                      <span>$2160.00</span>
	                    </a>
	                  </li>
	   
	                  <li>
	                    <a href="#">포인트 사용
	                      <span>$2210.00</span>
	                    </a>
	                  </li>
	                  <li>
	                    <a href="#">최종 결제 금액
	                      <span>$2210.00</span>
	                    </a>
	                  </li>
	                </ul>
	                <div class="payment_item">
	                    <h2>결제 수단</h2>
	                </div>
	                <div class="payment_item active">
	                  <div class="radion_btn">
	                    <input type="radio" id="f-option6" name="selector" />
	                    <label for="f-option6">카드결제 </label>
	                    <div class="check"></div>
	                  </div>
	                   <%-- <img src="${context}/resources/img/product/single-product/card.jpg" alt="" /> --%> 
	                </div>
	                <a class="main_btn" href="#">결제하기</a>
	                </div>
	              </div>
              </form>
                
               <!-- 배송지 입력 --> 
               <div class="col-lg-8">
               <h3>배송지</h3>
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
                  <input type="text"  class="form-control" id="dname" name="name" placeholder="이름"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="dphone"  name="dphone"  placeholder="전화번호"/>
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="email" name="compemailany" placeholder="이메일" />
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" name="dpostnum" id="dpostnum" name="compemailany" placeholder="우편번호"/><br/>
                  <button type="button" style="height:45px; width:130px; font-family: 'Poppins'; font-weight: bold; 
                   padding: 5px; border: none; border-radius: 5px" onclick="execPostCode();">
                   <i class="fa fa-search"></i> 우편번호 찾기</button>
                </div>
				
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control"  id="daddr" name="daddr" placeholder="주소1"/>
                  <!-- <span  class="placeholder" data-placeholder="주소1" ></span> -->
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="ddetaddr"  name="ddetaddr" placeholder="주소2" />
                 <!--  <span class="placeholder" data-placeholder="주소2" ></span> -->
                </div>

                <div class="col-md-12 form-group">
                  <textarea  class="form-control"  name="dmemo"  id="dmemo" rows="1" placeholder="배송메모" ></textarea>
                </div>
              </div>
             </form>
			  
            <%-- <div class="col-lg-4">
              <div class="order_box">
                <h2>최종 결제</h2>
                <ul class="list list_2">
                  <li>
                    <a href="#"
                      >상품 총 금액
                      <span>$2160.00</span>
                    </a>
                  </li>
   
                  <li>
                    <a href="#"
                      >포인트 사용
                      <span>$2210.00</span>
                    </a>
                  </li>
                  <li>
                    <a href="#"
                      >최종 결제 금액
                      <span>$2210.00</span>
                    </a>
                  </li>
                </ul>
                <div class="payment_item">
                    <h2>결제 수단</h2>
                </div>
                <div class="payment_item active">
                  <div class="radion_btn">
                    <input type="radio" id="f-option6" name="selector" />
                    <label for="f-option6">카드결제 </label>
                    <div class="check"></div>
                  </div>
                   <img src="${context}/resources/img/product/single-product/card.jpg" alt="" /> 
                </div>
                </div>

                <a class="main_btn" href="#">결제하기</a>
              </div> --%>
          </div>
        </div>
      </div>
    </section>
    <!--================End Checkout Area =================-->
       <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    
	 // 우편번호 조회
	    function execPostCode() {
		 alert("execPostCode");
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
	               
	               
	               $("[name=dpostnum]").val(data.zonecode);
	               $("[name=daddr]").val(fullRoadAddr);
	               
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

