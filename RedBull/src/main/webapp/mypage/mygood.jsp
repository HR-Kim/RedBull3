<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@page import="kr.co.redbull.cmn.Search"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>내가 좋아요한 상품</title>
	<!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">	
</head>
<body>
	<jsp:include page="/main/header.jsp"></jsp:include>
	<section class="good_area">
      <div class="container">
        <div class="good_inner">
          <div class="table-responsive">
          <form name="goodFrm">
          <input type="hidden" name="searchDiv" id="searchDiv" />
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
			         <th class="text-center col-md-1 col-xs-1">
					 전체선택<input type="checkbox" id="checkAll" name="checkAll"></th>
			         <th class="text-center col-md-4 col-xs-4 ">상품</th>
			         <th class="text-center col-md-1 col-xs-1">상품가격</th>
			         <th class="text-center col-md-1 col-xs-1">할인율</th>
			         <th class="text-center col-md-1 col-xs-1">배송비</th>
                </tr>
              </thead>

              <tbody>
               <c:choose>
	        	<c:when test="${list.size()>0 }">
	        		<c:set var="sum" value="0"></c:set>
	                <c:forEach var="good" items="${list}">
	                <tr>
	                <td class="text-center">
	                <input type="checkbox"  name="chBox" id="chBox"  data-cartNum="${good.gNum}"/>
	                <c:out value="${good.gNum}"/>
	               <%--  <input type="hidden" name="cartNum" id="cartNum" value="${cart.cartNum }"/> --%>
	                </td>
	                  <td>
	                    <div class="media">
	                      <div class="media-left"> 
	                        <img src="${context}/${good.saveFileNm}" class="media-object" style="width: 80px"  alt="이미지 없음" />
	                      </div>
	                      <div class="media-body">
	                     	 <c:out value="${good.pName}"/>
	                      </div>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                  <del><fmt:formatNumber pattern="###,###,###" value="${good.bPrice}"/></del><br/>
	                  <fmt:formatNumber pattern="###,###,###" value="${good.bPrice*(1-good.discount)}"/>원
	                  
	                  </td>
	                  <td class="text-center">
	                  	<c:out value="${good.discount*100}"/>%
	                  </td>
	                  <td class="text-center">
	                    <fmt:formatNumber groupingUsed="true" value = "${good.dPrice}"/>원
	                  </td>
	                </tr>
	            </c:forEach> 
			    </c:when>
			    
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">좋아요한 상품이 없습니다.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>

              </tbody>
            </table>
            <button type="button" id="do_delete" data-cartNum="${good.gNum}">삭제하기</button>
            </form>
            
            <!--  <button type="button" id="do_delete">삭제하기</button> -->
           
           	<td>
                <div class="container">
                	<a class="main_btn" href="${context}/product/get_retrieve.do">쇼핑하기</a>
                </div>
                <div class="container">
                	<a class="main_btn" href="${context}/good/get_retrieve.do">조회하기</a>
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="/main/footer.jsp"></jsp:include>
    <!--// div container -->
        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
     <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
      <script src="${context}/resources/js/bootstrap.min.js"></script>
          <script type="text/javascript">
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
 					 url : "${context}/good/do_delete.do",
 					 type : "post",
 					 data : { chbox : checkArr },
 					 success : function(result){
 					  if(result == 1) {          
 						  alert("상품이 삭제되었습니다.");
 					   	  location.href = "${context}/good/get_retrieve.do";
 					  } else {
 					   alert("삭제 실패");
 					  }
 					 }
 					});
 			}
 			
 		});
    </script>
</body>
</html>