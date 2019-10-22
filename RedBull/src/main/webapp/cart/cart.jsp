<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html lang="en">

  <body>
  <jsp:include page="/main/header.jsp"></jsp:include>
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
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
			         <th class="text-center col-md-1 col-xs-1">
					 전체선택<input type="checkbox" id="checkAll" name="checkAll" onclick="checkAll();"></th>
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
	                <input type="checkbox"  name="check" id="check" value="${cart.cartNum }"/>
	                <input type="hidden" name="cartNum" id="cartNum" value="${cart.cartNum }"/>
	                </td>
	                  <td>
	                    <div class="media">
	                      <div class="media-left">
	                        <img
	                          src="${context}/${cart.saveFileNm}"
	                          class="media-object"
	                          style="width: 80px"
	                          alt=""
	                        />
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
	                  <td>
	                    <div class="product_count">
	                    	<form name="form" method="get">
	                    	<input type="text" id="numBox"  name="amount" value="${cart.cartCnt}" readonly = "readonly">
<%-- 	                    	<button type="button" id="plus">+</button>
	                    	<input type="text" id="numBox"  name="amount" value="${cart.cartCnt}" readonly = "readonly">
	                    	<button type="button" id="minus">-</button>
	                    	<button type="button" id="update">변경</button> --%>
	                    	<!-- <input type="button" value="+" onclick="add();"><input type="button" value="-" onclick="del();"> -->
	                    	</form>
<!-- 	                      <input
	                        type="text"
	                        name="qty"
	                        id="sst"
	                        maxlength="12"
	                        value="1"
	                        title="Quantity:"
	                        class="input-text qty"
	                      />
	                      <button
	                        onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
	                        class="increase items-count"
	                        type="button"
	                      >
	                        <i class="lnr lnr-chevron-up"></i>
	                      </button>
	                       <button
	                        onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
	                        class="reduced items-count"
	                        type="button"
	                      >
	                        <i class="lnr lnr-chevron-down"></i>
	                      </button> -->
	                    </div>
	                  </td>
	                  <td class="text-center">
	                    <fmt:formatNumber pattern="###,###,###" value="${cart.dPrice }"/>원
	                  </td>
	                  <td class="text-center">
	                   <fmt:formatNumber pattern="###,###,###" value="${(cart.bPrice * (1-cart.discount) + cart.oPrice) * cart.cartCnt + cart.dPrice}" />원
	                  </td>
	                </tr>
	            </c:forEach> 
			    </c:when>
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">장바구니가 비었습니다.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>
                </tr>

              </tbody>
            </table>
            
             <button type="button" id="do_delete">삭제하기</button>
             <hr/>
            	 결제금액
             <hr/>
           	<td>
                <div class="container">
                	<input type="button" class="btn btn-Success" value="쇼핑하기"/>
                	<input type="submit" id="payBtn" class="btn btn-Success" value="결제하기"/>
<!--                    <input type="button" class="gray_btn" id="gray_btn" value="Continue Shopping" href="#" />
                  <input type="button" class="main_btn" id="main_btn" value="결제하기" href="#" /> -->
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <!--================End Cart Area =================-->
        <!-- Optional JavaScript -->
		
	
      	<script type="text/javascript">
      	
      	//결제
      	$("#payBtn").on("click",function(){
      		alert("결제창으로!");
      		
    		$.ajax({
				type : "POST",
				url : "${context}/pay/get_retrieve.do",
				dataType : "html",
				data : {
					//"pNum" : $("#pNum").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						//alert(jData.msgMsg);
						location.href = "${context}/pay/pay_list.jsp";

					} else {
						alert("로딩 실패");
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
      		
      	});
      	
      	
      	$("#plus").click(function(){
      		//alert("수량증가");
      	   var num = $("#numBox").val(); //5
      	   var plusNum = Number(num) + 1;//6
      	  });
      	  
      	  $("#minus").click(function(){
      	   var num = $("#numBox").val();
      	   var minusNum = Number(num) - 1;
      	   
      	});
      	
      	  //삭제
      	  function checkAll(){
     			if($("#checkAll").is(':checked')==true){
       				$("input[name='check']").prop("checked",true); //check
       				
       			}else{
       				$("input[name='check']").prop("checked",false); //check해제
       			}
      	  }
      	  
       	$("#do_delete").on("click",function(){
        	var check = "";
       		//var cartNum = document.getElementByName("check");
       		$("input[name='check']:checked").each(function(){
       			check = check + $(this).val()+",";
       			});
       		check = check.substring(0,check.lastIndexOf(","));
       		
       		if(check == ''){
       			alert("삭제할 대상을 선택하세요.");
       			return false;
       			}
       		console.log("check => " + check);

       		if(confirm("삭제하시겠습니까?") == false) return;
		   
       		$.ajax({
	               type:"POST",
	               url:"${context}/cart/do_delete.do",
	               dataType:"html",
	               data:{
	               cartNum :check
	              }, 
	            success: function(data){
	              var jData = JSON.parse(data);
	              if(null != jData && jData.msgId=="1"){
	                alert(jData.msgMsg);
	                location.href="${context}/cart/get_retrieve.do";

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
       	
      	</script>
      	
    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
    
  </body>
</html>
    