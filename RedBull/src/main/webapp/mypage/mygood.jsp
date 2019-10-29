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
	<title>���� ���ƿ��� ��ǰ</title>
	<!-- ��Ʈ��Ʈ�� -->
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
					 ��ü����<input type="checkbox" id="checkAll" name="checkAll"></th>
			         <th class="text-center col-md-4 col-xs-4 ">��ǰ</th>
			         <th class="text-center col-md-1 col-xs-1">��ǰ����</th>
			         <th class="text-center col-md-1 col-xs-1">������</th>
			         <th class="text-center col-md-1 col-xs-1">��ۺ�</th>
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
	                        <img src="${context}/${good.saveFileNm}" class="media-object" style="width: 80px"  alt="�̹��� ����" />
	                      </div>
	                      <div class="media-body">
	                     	 <c:out value="${good.pName}"/>
	                      </div>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                  <del><fmt:formatNumber pattern="###,###,###" value="${good.bPrice}"/></del><br/>
	                  <fmt:formatNumber pattern="###,###,###" value="${good.bPrice*(1-good.discount)}"/>��
	                  
	                  </td>
	                  <td class="text-center">
	                  	<c:out value="${good.discount*100}"/>%
	                  </td>
	                  <td class="text-center">
	                    <fmt:formatNumber groupingUsed="true" value = "${good.dPrice}"/>��
	                  </td>
	                </tr>
	            </c:forEach> 
			    </c:when>
			    
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">���ƿ��� ��ǰ�� �����ϴ�.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>

              </tbody>
            </table>
            <button type="button" id="do_delete" data-cartNum="${good.gNum}">�����ϱ�</button>
            </form>
            
            <!--  <button type="button" id="do_delete">�����ϱ�</button> -->
           
           	<td>
                <div class="container">
                	<a class="main_btn" href="${context}/product/get_retrieve.do">�����ϱ�</a>
                </div>
                <div class="container">
                	<a class="main_btn" href="${context}/good/get_retrieve.do">��ȸ�ϱ�</a>
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="/main/footer.jsp"></jsp:include>
    <!--// div container -->
        <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
     <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
      <script src="${context}/resources/js/bootstrap.min.js"></script>
          <script type="text/javascript">
          //üũ�ڽ�
       	 $("#checkAll").click(function(){
      			if($("#checkAll").is(':checked')==true){
        				$("input[name='chBox']").prop("checked",true); //check
        				
        			}else{
        				$("input[name='chBox']").prop("checked",false); //check����
        			}
       	  });	
          
       //����
 		$("#do_delete").on("click",function(){
 			
 			var confirm_val = confirm("���� �����Ͻðڽ��ϱ�?");
 			
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
 						  alert("��ǰ�� �����Ǿ����ϴ�.");
 					   	  location.href = "${context}/good/get_retrieve.do";
 					  } else {
 					   alert("���� ����");
 					  }
 					 }
 					});
 			}
 			
 		});
    </script>
</body>
</html>