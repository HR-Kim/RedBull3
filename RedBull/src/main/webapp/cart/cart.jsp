<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
	User user = (User)session.getAttribute("user");
	out.println("user: " + user);
%>
<html lang="ko">
 <style>
	#updatebtn{
		color: black;
	}
</style> 
	<jsp:include page="/main/header.jsp"></jsp:include>
  <body>
  
    <!--================Header Menu Area =================-->
 
    <!--================Header Menu Area =================-->

    <!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="mb-1 mb-md-0">
              <h2>장바구니</h2>
            </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->
    <!--================Cart Area =================-->
    <section class="cart_area">
      <div class="container">
        <div class="cart_inner">
          <div class="table-responsive">
          <form name="cartFrm">
          <input type="hidden" name="searchDiv" id="searchDiv" />
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
			         <th class="text-center col-md-1 col-xs-1">
					 전체선택<input type="checkbox" id="checkAll" name="checkAll"></th>
			         <th class="text-center col-md-4 col-xs-4 ">상품</th>
			         <th class="text-center col-md-1 col-xs-1">상품가격</th>
			         <th class="text-center col-md-1 col-xs-1">옵션가격</th>
			         <th class="text-center col-md-1 col-xs-1">수량</th>
			         <th class="text-center col-md-1 col-xs-1">배송비</th>
			         <th class="text-center col-md-1 col-xs-1">최종 가격</th>
                </tr>
              </thead>
              <tbody>
             
               <c:choose>
	        	<c:when test="${list.size()>0 }">
	        		<c:set var="sum" value="0"></c:set>
	                <c:forEach var="cart" items="${list}">
	                <tr>
	                <td class="text-center">
	                <input type="checkbox"  name="chBox" id="chBox"  data-cartNum="${cart.cartNum }"/>
	               <%--  <input type="hidden" name="cartNum" id="cartNum" value="${cart.cartNum }"/> --%>
	               <input type="hidden" name="oNum" id="oNum" value="${cart.oNum }"/>
	                </td>
	                  <td>
	                    <div class="media">
	                      <div class="media-left"> 
	                        <img src="${context}/${cart.saveFileNm}" class="media-object" style="width: 80px"  alt="이미지 없음" />
	                      </div>
	                      <div class="media-body">
	                     	 <c:out value="${cart.pName}"/><br/>
	                     	 <c:out value="${cart.oName}"/>
	                      </div>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                  <del><fmt:formatNumber pattern="###,###,###" value="${cart.bPrice }"/></del><br/>
	                  <fmt:formatNumber pattern="###,###,###" value="${cart.bPrice * (1-cart.discount)}"/>원
	                  </td>
	                  <td class="text-center">
	                  <fmt:formatNumber pattern="###,###,###"  value="${cart.oPrice}" />원
	                  </td>
	                  
				       <td class="text-left">
				       <input type="text"  id="cartCnt" name="cartCnt" value="${cart.cartCnt}" size='2' readonly="readonly">
				        <a href="#" onclick="javascript_:change(1);">▲</a><a href="#" onclick="javascript_:change(-1);">▼</a>
				        <button type="button" class="btn btn-success btn-sm" id="updatebtn" >변경</button>
				        <!--버튼을 이미지로 바꾸세요 <img src='이미지경로' onclick='javascript_:change(1);'>-->
				       </td>
				       
<%-- 	                <div class="product_count">
	                   		<input type="text" name="cartCnt" id="cartCnt" readonly="readonly"  value="${cart.cartCnt}" />
							<button onclick="javascript:countUp()" class="increase items-count" type="button">
								<i class="lnr lnr-chevron-up"></i>
							</button>
							<button onclick="javascript:countDown()"  class="reduced items-count" type="button">
								<i class="lnr lnr-chevron-down"></i>
							</button>
							<button type="button" class="btn btn-success btn-sm" id="updatebtn" >변경</button>
	                    </div> --%>
	                  
	                  <td class="text-center">
	                    <fmt:formatNumber pattern="###,###,###" value="${cart.dPrice }"/>원
	                  </td>
	                  <td class="text-center">
	                   <fmt:formatNumber pattern="###,###,###" value="${(cart.bPrice * (1-cart.discount) + cart.oPrice) * cart.cartCnt + cart.dPrice}" />원
	                  </td>
	                </tr>
	                <c:set var="sum" value="${sum+ (cart.bPrice * (1-cart.discount) + cart.oPrice) * cart.cartCnt + cart.dPrice }"></c:set>
	            </c:forEach> 
			    </c:when>
			    
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">장바구니가 비었습니다.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>

              </tbody>
            </table>
            <button type="button" id="do_delete" data-cartNum="${cart.cartNum }">삭제하기</button>
            </form>
            
            <!--  <button type="button" id="do_delete">삭제하기</button> -->
             <hr/>
            	 결제금액 <fmt:formatNumber pattern="###,###,###" value="${sum }" />원
             <hr/>
           	<td>
                <div class="container">
                	<a class="main_btn" href="${context}/product/get_retrieve.do">쇼핑하기</a>
                	<input type="submit" id="payBtn" class="main_btn" value="결제하기"/>
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <!--================End Cart Area =================-->
        <!-- Optional JavaScript -->
		
      	<script type="text/javascript">
        
      	$("#payBtn").on("click",function(){
      		alert("결제하러!");
      		location.href = "${context}/pay/get_retrieve.do";
      		
      	});
      	
      	//수량 수정
 		$("#updatebtn").on("click",function(){
			var oNum = $("#oNum").val();
			var sst = $("#cartCnt").val();
 			
	    	if(false==confirm("수정 하시겠습니까?"))return;
	    	
			$.ajax({
	            type:"POST",
	            url:"${context}/cart/do_update.do",
	            dataType:"html",
	            data:{
	            	   "oNum" : oNum,
	            	   "cartCnt" : sst
	            },
	            success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	                //console.log(data);
	            	//{"msgId":"1","msgMsg":"수정 되었습니다.","totalCnt":0,"num":0}
	            	var parseData = $.parseJSON(data);
	            	if(parseData.msgId=="1"){
	            		alert(parseData.msgMsg);
	            		location.href = "${context}/cart/get_retrieve.do";
	            	}else{
	            		alert(parseData.msgMsg);
	            	}
	            },
	            complete: function(data){//무조건 수행
	             
	            },
	            error: function(xhr,status,error){
	             
	            }
	        });		    	
	    });
      	
      	
 		 function change(num)
 		 {
	 		 var x  = document.cartFrm;
	 		 var y = Number(x.cartCnt.value) + num;
	 		 if(y < 1) y = 1;
	 		 x.cartCnt.value = y;
 		 }
      	
/* 		//수량 Up
		function countUp(){
			var result = document.getElementById('cartCnt');
			var cartCnt = result.value;
			if(!isNaN( cartCnt )){
				result.value++;
				return false;
			}
		}
		//수량Down
		function countDown(){
			var result = document.getElementById('cartCnt');
			var cartCnt = result.value;
			if( !isNaN(cartCnt) && cartCnt > 0 ){
				result.value--;
				return false;
			}
		} */
      	
      	  //체크박스
      	 $("#checkAll").click(function(){
     			if($("#checkAll").is(':checked')==true){
       				$("input[name='chBox']").prop("checked",true); //check
       				
       			}else{
       				$("input[name='chBox']").prop("checked",false); //check해제
       			}
      	  });
		
		//삭제
		$("#do_delete").on("click",function(){
			
			var confirm_val = confirm("정말 삭제하시겠습니까?");
			
			if(confirm_val){
				var checkArr = new Array();
				
				$("input[name='chBox']:checked").each(function(){
					checkArr.push($(this).attr("data-cartNum"));
				});
				
				$.ajax({
					 url : "${context}/cart/do_delete.do",
					 type : "post",
					 data : { chbox : checkArr },
					 success : function(result){
					  if(result == 1) {          
						  alert("상품이 삭제되었습니다.");
					   	  location.href = "${context}/cart/get_retrieve.do";
					  } else {
					   alert("삭제 실패");
					  }
					 }
					});
			}
			
		});
      	  
      	</script>
      	
    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
    
  </body>
</html>
    