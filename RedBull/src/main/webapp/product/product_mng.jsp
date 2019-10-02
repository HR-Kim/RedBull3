<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//서버에 저장
	//String uploadPath = request.getRealPath(request.getContextPath());
	//uploadPath = uploadPath+"/resources/img/product";
	
	//git에 저장
	String uploadPath = "git/RedBull3/RedBull/src/main/webapp/resources/img/product";
	String user =  System.getProperty("user.home");
	uploadPath = user+File.separator+uploadPath;
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>상품 등록</title>
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2>상품 등록</h2>
	<br>
	<form id="detail_photo_frm" enctype="multipart/form-data">
		<input type="hidden" name="root_path" value=<%=uploadPath%> size="15"><br/>
		<input type="hidden" name="nextPnum" value=${nextPnum} size="15"><br/>
		
		<!-- 카테고리 -->
		<div class="form-group">
			<label class="control-label requiredField" for="pCategory">카테고리<span class="asteriskField">*</span></label>
			<input class="form-control" id="pCategory" name="pCategory" type="text" />
		</div>
		<!-- 제품명 -->
		<div class="form-group">
			<label class="control-label requiredField" for="pName">제품명<span class="asteriskField">*</span></label>
			<input class="form-control" id="pName" name="pName" type="text" />
		</div>
		<!-- 기본가격 -->
		<div class="form-group">
			<label class="control-label requiredField" for="bPrice">기본가격<span class="asteriskField">*</span></label>
			<input class="form-control" id="bPrice" name="bPrice" type="text" />
		</div>
		<!-- 할인율 -->
		<div class="form-group">
			<label class="control-label" for="discount">할인율</label>
			<input class="form-control" id="discount" name="discount" type="text" />
		</div>
		<!-- 배송비 -->
		<div class="form-group">
			<label class="control-label" for="dPrice">배송비</label>
			<input class="form-control" id="dPrice" name="dPrice" type="text" />
		</div>
		<!-- 상세정보 -->
		<div class="form-group">
			<label class="control-label requiredField" for="detail">상세정보<span class="asteriskField">*</span></label>
			<textarea id="detail" name="detail" class="form-control" rows="10" cols="40"></textarea>
		</div>
		<!-- 옵션 -->
		<div class="form-group">
			<label class="control-label" for="opt">옵션</label>
			<button class="btn btn-primary btn-sm " id="opt_add" >추가</button>
			${optList}
			<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover" id="optTable">
				<tbody>
					<c:choose>
						<c:when test="${optList.size()>0 }">
							<c:forEach  var="opt"  items="${optList}">
								<tr>
									<td class="text-center"><c:out value="${opt.oName}"/></td>
									<td class="text-center"><c:out value="${opt.oPrice}"/></td>
									<td class="text-left"><input type="file" /></td>
									<td class="text-left"><a href="#this" name="opt_delete" class="btn">삭제하기</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="99">옵션이 없습니다.</td>
							</tr>
						</c:otherwise>	
					</c:choose>
				</tbody>
			</table>
		</div>
		</div>
	</form>
	<div class="form-group">
		<button class="btn btn-primary" >글 작성</button>
		<button class="btn btn-primary" >취소</button>
	</div>
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script>
		$("#opt_add").on("click",function(){
			//alert("opt_add");
			var frm = document.productFrm;
			frm.action = "${context}/product/do_product_option.do";
    		frm.submit();			
		});
		
		$(document).ready(function(){
			
		});
	
	</script>
</body>
</html>