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
	/**페이지 사이즈*/
	String pageSize = "10";	
	/**페이지 번호*/	
	String pageNum = "1";
	/**검색조건*/
	String searchDiv = "20";
	/**검색어*/
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
	<title>내가 좋아요한 상품</title>
	<!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">	
</head>
<body>
	<div class="container">
		<div class="page-header">
       		<h1>좋아요한 상품</h1>
     	</div>
    <input type="button" class="btn btn-default btn-sm" id="doGood"
		value="좋아요 들어가기" />
		<hr/>
      <!--// 검색영역 -->  
      <!-- Grid영역 -->
      <div class="table-responsive">
       <table class="table  table-striped table-bordered table-hover">
        <thead class="bg-primary">
         <th class="text-center col-md-3 col-xs-4">상품 사진</th>
         <th class="text-center col-md-3 col-xs-4 ">상품 이름</th>
         <th class="text-center col-md-1 col-xs-4">링크</th>
        </thead>
        
		<tbody>
		<c:set var="regId" value="등록자"/>
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
		         		<td class="text-center" colspan="99">좋아요한 상품이 없습니다.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>
	</tbody>
   </table>
 </div>
      <!--// Grid영역 -->
      
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
        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
     <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
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