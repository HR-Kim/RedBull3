<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@page import="kr.co.redbull.cmn.Search"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/**������ ������*/
	String pageSize = "10";	
	/**������ ��ȣ*/	
	String pageNum = "1";
	/**�˻�����*/
	String searchDiv = "20";
	/**�˻���*/
	String searchWord = "";	

	Search vo = (Search)request.getAttribute("vo");
	
	if(null!=vo){
		pageSize   = StringUtil.nvl(vo.getPageSize()+"", "10");
		pageNum    = StringUtil.nvl(vo.getPageNum()+"", "1");
		searchDiv  = StringUtil.nvl(vo.getSearchDiv(), "");
		searchWord = StringUtil.nvl(vo.getSearchWord(), "");
	}else{
		pageSize   = "10";
		pageNum    = "1";
		searchDiv  = "20";
		searchWord = "";
	}
	
	//paging
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName
	
	int maxNum      = 0;  //totalCnt
	int bottomCount = 10;
	int currPageNo  = 1;  //pageNum
	int rowPerPage  = 10; //pageSize
	
	String url 	  	  = request.getContextPath()+"/good/get_myGoodList.do";
	String scriptName = "search_page"; 
	
	//totalCnt
	String totalCnt = (request.getAttribute("totalCnt")==null)? "0":request.getAttribute("totalCnt").toString();
	
	maxNum     = Integer.parseInt(totalCnt);
	currPageNo = Integer.parseInt(pageNum);
	rowPerPage = Integer.parseInt(pageSize);
	
%> 
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
	<div class="container">
		<div class="page-header">
       		<h1>���ƿ��� ��ǰ</h1>
     	</div>
    <input type="button" class="btn btn-default btn-sm" id="doGood"
		value="���ƿ� ����" />
		<hr/>
      <!--// �˻����� -->  
      <!-- Grid���� -->
      <div class="table-responsive">
       <table class="table  table-striped table-bordered table-hover">
        <thead class="bg-primary">
         <th class="text-center col-md-3 col-xs-4">��ǰ ����</th>
         <th class="text-center col-md-3 col-xs-4 ">��ǰ �̸�</th>
         <th class="text-center col-md-1 col-xs-4">��ũ</th>
        </thead>
        
		<tbody>
		<c:set var="regId" value="�����"/>
 		<c:choose>
        	<c:when test="${list.size()>0 }">
        	
				<c:forEach var="good" items="${list}">
	        	<tr>
						<td class="text-center"></td>
						<td class="text-center"><c:out value="${B.pName}"/></td>
						<td class="text-center"><c:out value="${B.gLink}"/></td>
						<td class="text-center"></td>
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
 </div>
      <!--// Grid���� -->
      
      <!-- pagenation -->
      <div class="text-center">
       <ul class="pagination">
        <li><a href="#">&lt;</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">&gt;</a></li>
       </ul>
      </div>
      <!--// pagenation -->
      
   </div>
    <!--// div container -->
        <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
     <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
      <script src="${context}/resources/js/bootstrap.min.js"></script>
          <script type="text/javascript">
          	
          	function doGood(){
          		var frm = document.frm;
          		frm.action = "${context}/mypage/get_retrieve.do";
          		frm.submit();
          	}
          
          	$("#doGood").on("click",function(){
          		alert("doGood()");
          		doCart();
          	});
          
	    	$(document).ready(function(){
	    		//alert("ready");
	    		
	    	})
    </script>
</body>
</html>