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
	<title>���� ������ ��ǰ</title>
	<!-- ��Ʈ��Ʈ�� -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">	
</head>
<body>
	<jsp:include page="/main/header.jsp"></jsp:include>
	<section class="pay_area">
      <div class="container">
        <div class="pay_inner">
          <div class="table-responsive">
          <form name="payFrm">
          <input type="hidden" name="searchDiv" id="searchDiv" />
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
					 <th class="text-center col-md-1 col-xs-1">�ֹ���ȣ</th>
			         <th class="text-center col-md-4 col-xs-4 ">��ǰ</th>
			         <th class="text-center col-md-1 col-xs-1">��ǰ����</th>
			         <th class="text-center col-md-1 col-xs-1">������</th>
			         <th class="text-center col-md-1 col-xs-1">��ۺ�</th>
			         <th class="text-center col-md-1 col-xs-1">�Ѿ�</th>
                </tr>
              </thead>

              <tbody>
               <c:choose>
	        	<c:when test="${list.size()>0 }">
	        		<c:set var="sum" value="0"></c:set>
	                <c:forEach var="pay" items="${list}">
	                <tr>
	                <td class="text-center">
	                	<c:out value="${pay.orderNum}"/>
	                </td>
	                  <td>
	                    <div class="media">
	                      <div class="media-left"> 
	                        <img src="${context}/${pay.saveFileNm}" class="media-object" style="width: 80px"  alt="�̹��� ����" />
	                      </div>
	                      <div class="media-body">
	                     	 <c:out value="${pay.pName}"/><br/>
	                     	 �ɼ�:<c:out value="${cart.oName}"/>
	                      </div>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                  <del><fmt:formatNumber pattern="###,###,###" value="${pay.bPrice}"/></del><br/>
	                  <fmt:formatNumber pattern="###,###,###" value="${pay.bPrice*(1-pay.discount)}"/>��
	                  
	                  </td>
	                  <td class="text-center">
	                  	<c:out value="${pay.discount*100}"/>%
	                  </td>
	                  <td class="text-center">
	                    <fmt:formatNumber groupingUsed="true" value = "${pay.dPrice}"/>��
	                  </td>
	                  <td class="text-center">
	                    <fmt:formatNumber groupingUsed="true" value = "${pay.afterPrice}"/>��
	                  </td>
	                </tr>
	            </c:forEach> 
			    </c:when>
			    
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">������ ��ǰ�� �����ϴ�.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>

              </tbody>
            </table>
            </form>
           
           	<td>
                <div class="container">
                	<a class="main_btn" href="${context}/product/get_retrieve.do">�����ϱ�</a>
                	<a class="main_btn" href="${context}/cart/get_retrieve.do">��ٱ��� ����</a>
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="/main/footer.jsp"></jsp:include>
</body>
</html>