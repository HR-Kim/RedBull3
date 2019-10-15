<%@page import="kr.co.redbull.product.service.Product"%>
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
<body>
	<!--================Header Menu Area =================-->
	<jsp:include page="/main/header.jsp"></jsp:include>
	<h2>상품 등록</h2>
	<br>
	<form id="frm_mng" name="frm_mng"  method="POST" enctype="multipart/form-data">
		<!-- 카테고리 -->
		<div class="form-group">
			<label class="control-label requiredField" for="pCategory">카테고리<span class="asteriskField">*</span></label>
			<input class="form-control" id="pCategory" name="pCategory" type="text" value="${newProduct.pCategory}" />
		</div>
		<!-- 제품명 -->
		<div class="form-group">
			<label class="control-label requiredField" for="pName">제품명<span class="asteriskField">*</span></label>
			<input class="form-control" id="pName" name="pName" type="text" value="${newProduct.pName}" />
		</div>
		<!-- 기본가격 -->
		<div class="form-group">
			<label class="control-label requiredField" for="bPrice">기본가격<span class="asteriskField">*</span></label>
			<input class="form-control" id="bPrice" name="bPrice" type="text" value="${newProduct.bPrice}" />
		</div>
		<!-- 할인율 -->
		<div class="form-group">
			<label class="control-label" for="discount">할인율</label>
			<input class="form-control" id="discount" name="discount" type="text" value="${newProduct.discount}" />
		</div>
		<!-- 배송비 -->
		<div class="form-group">
			<label class="control-label" for="dPrice">배송비</label>
			<input class="form-control" id="dPrice" name="dPrice" type="text" value="${newProduct.dPrice}" />
		</div>
		<!-- 상세정보 -->
		<div class="form-group">
			<label class="control-label requiredField" for="detail">상세정보<span class="asteriskField">*</span></label>
			<textarea id="detail" name="detail" class="form-control" rows="10" cols="40">${newProduct.detail}</textarea>
		</div>
		<!-- 옵션 -->
		<div class="form-group">
			<label class="control-label">옵션</label>
			<button class="btn btn-primary btn-sm" id="add_opt">추가</button>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover" id="optTable">
					<thead>
						<tr>
							<th class="text-center">옵션명</th>
							<th class="text-center">옵션가격</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${newOptList.size()>0 }">
								<c:forEach  var="opt"  items="${newOptList}">
									<tr>
										<td class="text-center"><c:out value="${opt.oName}"/></td>
										<td class="text-center"><c:out value="${opt.oPrice}"/></td>
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
	<label class="control-label">이미지</label>
	<button class="btn btn-primary btn-sm" id="add_image" name="add_image">전송</button>
		<!-- 이미지 -->
		<div class="form-group">
			<input type="hidden" id="pNum" name="pNum" value="${newProduct.pNum}" />
			<div class="table-responsive">
				<input type="hidden" id="root_path" name="root_path" value=<%=uploadPath%> size="15"><br/>
				<input type="file" id="file01" name="file01" />
			</div>
		</div>
	</form>
	<div class="form-group">
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover" id="imageTable">
				<thead>
					<tr>
						<th class="text-center">상품번호</th>
						<th class="text-center">파일명</th>
						<th class="text-center">사이즈</th>
						<th class="text-center">확장자</th>
						<th class="text-center"></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${newImageList.size()>0 }">
							<c:forEach  var="image"  items="${newImageList}">
								<tr>
									<td class="text-center"><c:out value="${image.refNum}"/></td>
									<td class="text-center"><c:out value="${image.orgFileNm}"/></td>
									<td class="text-center"><c:out value="${image.fileSize}"/></td>
									<td class="text-center"><c:out value="${image.extNm}"/></td>
									<td class="text-center"><a href="#this" name="img_delete" class="btn">삭제하기</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="99">이미지가 없습니다.</td>
							</tr>
						</c:otherwise>	
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<div class="form-group">
		<button class="btn btn-primary" id="btn_write" >글 작성</button>
		<a class="btn btn-primary" href="${context}/product/do_cancel_write.do" >취소</a>
	</div>
	<!--================footer Area  =================-->
	<jsp:include page="/main/footer.jsp"></jsp:include>
	<script>
		//이미지 삭제
		$("a[name='img_delete']").on("click",function(e){
			alert("img_delete");	
		    e.preventDefault();
		    tr_imgDelete($(this));
		    
		})
		function tr_imgDelete(obj){
			obj.closest("tr").remove();
		}
		//글작성
		$("#btn_write").on("click", function(e){
			//alert("btn_write");
			e.preventDefault();
			var frm = document.frm_mng;
			frm.action = "${context}/product/do_write.do";
			frm.method = "POST";
			frm.submit();
		});
		//이미지 추가
		var newImageList = new Array();
		$("#add_image").on("click", function(e){
			//alert("add_image");
			e.preventDefault();
			var frm = document.frm_mng;
			//frm.action = "${context}/file/do_img_save.do";
			//frm.method = "POST";
			//frm.submit();
			var data = new FormData(frm);
			
			$.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "${context}/file/do_img_save.do",
	            data: data,
	            processData: false,
	            contentType: false,
	            dataType: "json",
	            cache: false,
	            timeout: 600000,
	            success: function (data) {
	                //alert("complete : "+data.newImage);
	                location.href = "${context}/product/do_product_mng.do";
	            },
	            error: function (e) {
	                console.log("ERROR : ", e);
	                alert("fail");
	            }
	        });
		});
		
		//옵션추가
		$("#add_opt").on("click", function(e){
			//alert("add_opt");
			e.preventDefault();
			var frm = document.frm_mng;
			frm.action = "${context}/product/do_product_option.do";
			frm.submit();
		});
		$(document).ready(function(){
			
		});
	
	</script>
</body>
</html>