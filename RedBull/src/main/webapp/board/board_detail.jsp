<%@page import="kr.co.redbull.board.service.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>게시판</title>

    <!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<form name="boardForm" id="boardForm"> 
		<input type="hidden" id="bNum" name="bNum"/>
	</form>
	 <div class="container">
	 
		<table class="table table-bordered table-sm">
			<!-- 본문 -->
			<tr>
				
				<td id="num" style="display:none;">${vo.bNum}</td>
				<td colspan="6" class="text-center col-md-1 col-xs-12" style="font-size: 12pt; font-weight: bold;">${vo.title}</td></tr>
			<tr>
				<td class="text-left col-md-1 col-xs-1" style="font-size: 9pt;">${vo.regId}</td>
				<td class="text-left col-md-2 col-xs-2" style="font-size: 9pt;">${vo.regDt}</td>
				<td class="text-left col-md-1 col-xs-1" style="font-size: 9pt;">댓글수</td>
				<td class="text-left col-md-1 col-xs-1" style="font-size: 9pt;">${vo.readCnt}</td>
				<td class="text-left col-md-5 col-xs-5" style="font-size: 9pt;">${vo.category}</td>
				<td class="text-center col-md-2 col-xs-2">
					<button type="button" class="btn btn-default btn-sm" id="do_update" name="do_update">수정</button>  
					<button type="button" class="btn btn-default btn-sm" id="do_delete" name="do_delete">삭제</button>  
				</td>
			</tr> 
			<tr>
				<td colspan="6" class="text-left col-md-12 col-xs-12">
					<div class="container">
						${vo.contents}
					</div>
				</td>
			</tr>
			<!-- //본문 -->
			<!-- 댓글목록 -->			
			<tr>			
				<td colspan="6" class="text-left col-md-12 col-xs-12">
					 <div class="container-fluid">
 					 	<c:choose>
         					<c:when test="${commentList.size()>0}">
         						<c:forEach var="vo" items = "${commentList}">
								 	<c:choose>
								 		<c:when test="${vo.cLevel==1 }">
								 			<div class="container-fluid">
												<table class="table table-striped table-sm">
										 			
										 </c:when>
										 <c:when test="${vo.cLevel==2 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" style="width: 95%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel==3 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" style="width: 90%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel==4 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" style="width: 85%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel>=5 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" style="width: 80%; float: right;">
										 </c:when>
									</c:choose>
									<tr>
												 		<td class="text-left col-md-1 col-xs-1">${vo.cNum }</td>
												 		<td class="text-left col-md-1 col-xs-1">${vo.regId }</td>
												 		<td class="text-left col-md-1 col-xs-1">${vo.regDt }</td>
												 		<td class="text-left col-md-6 col-xs-6">
												 		<td class="text-right col-md-4 col-xs-4">
												 			<button type="button" class="btn btn-default btn-sm" id="do_commentComment">댓글달기</button>
															<button type="button" class="btn btn-default btn-sm" id="do_commentUpdate">수정</button>  
															<button type="button" class="btn btn-default btn-sm" id="do_commentDelete">삭제</button>  
												 		</td>
											 		</tr>
											 		<tr>
											 			<td colspan="12">${vo.contents}</td>
											 		</tr>
											 		<tr>
														<td colspan="6">
															<div class="container-fluid">
																<div class="row">
																	<div class="form-group col-xs-11">
																		<form class="form-horizontal" name="commentForm" id="commentForm" method="get" >
																			<input type="hidden" name="tNum" id="tNum" />
																			<input type="hidden" name="refNum" id="refNum"/>
																			<input type="text" name="contents" id="contents" class="form-control input-sm"/>
																			<input type="hidden" name="hcNum" id="hcNum"/>
														       				<input type="hidden" name="regId" id="regId" />
														       				<input type="hidden" name="cLevel" id="cLevel"/>
														     			</form>
													       			</div>
													       			<button type="button" class="btn btn-default btn-sm" id="do_save_comment">등록</button>
																</div>
												        	</div>
														</td>
													</tr>
											 	</table>
										 	</div>
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
				</td>
			</tr>
			<!-- //댓글목록 -->
			<!-- 댓글입력 폼 -->
			<tr>
				<td colspan="6">
					<div class="container-fluid">
						<div class="row">
							<div class="form-group col-xs-11">
								<form class="form-horizontal" name="commentForm" id="commentForm" method="get" >
									<input type="hidden" name="tNum" id="tNum" />
									<input type="hidden" name="refNum" id="refNum"/>
									<input type="text" name="contents" id="contents" class="form-control input-sm"/>
									<input type="hidden" name="hcNum" id="hcNum"/>
				       				<input type="hidden" name="regId" id="regId" />
				       				<input type="hidden" name="cLevel" id="cLevel"/>
				     			</form>
			       			</div>
			       			<button type="button" class="btn btn-default btn-sm" id="do_save_comment">등록</button>
						</div>
		        	</div>
				</td>
			</tr>
			<!-- 댓글입력 폼 -->
		</table>
		</div>
		
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- jQuery validate -->
	<script src="${context}/resources/js/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		//버튼 누르면 대댓글 창 나타나기
		$("#do_commentComment").on("click", function(){
			
			/* var 
			frm.text.style.visibility="visible"; */
		});
		
		//댓글쓰기
		function addComment(){
			var tNum = ${vo.tNum};
			var refNum = $("#num").text();
			var contents = $("#contents").val();
			//나중에 세션에서 가져와야 함
			var regId = "test";
			//var hcNum = 
			//if(hcNum==)
			//var cLevel = 
			
			//validation
			if (confirm("댓글을 등록하시겠습니까?") == false) return;
			
			$.ajax({
				type : "GET",
				url : "${context}/comment/do_save.do",
				dataType : "html",
				data : {
					"tNum" : tNum,
					"refNum" : refNum,
					"contents" : contents,
					"hcNum" : 0,
					"regId" : regId,
					"cLevel" : cLevel
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.reload();
					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  
		}
		
		//버튼 클릭하면 댓글 등록 이벤트
		$("#do_save_comment").on("click", function(){
			
//			var tNum = ${vo.tNum};
//			var regId = "test";
/* 			//validation
			if (confirm("댓글을 등록하시겠습니까?") == false) return;
			
			//alert('댓글등록');

			$.ajax({
				type : "GET",
				url : "${context}/comment/do_save.do",
				dataType : "html",
				data : {
					"tNum" : tNum,
					"refNum" : $("#num").text(),
					"contents" : $("#contents").val(),
					"hcNum" : 0,
					"regId" : regId,
					"cLevel" : 1
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.reload();
					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax   */
			addComment();
		});
		
		//수정
		$("#do_update").on("click", function(){
			//alert('update');	
			var bNum = $("#num").text();
			
			var frm = document.boardForm;
			frm.bNum.value=bNum;
			frm.action = "${context}/board/do_write.do";
			frm.submit();
		});
		
		//삭제
		$("#do_delete").on("click", function(){
			
			var tNum = ${vo.tNum};
			var bNum = $("#num").text();
			
			//validation
			if (confirm("삭제 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "GET",
				url : "${context}/board/do_delete.do",
				dataType : "html",
				data : {
					"bNum" : bNum
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/board/get_retrieve.do?searchDiv="+tNum;

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  

		});
		
		$(document).ready(function() {
			
		});
	
	</script>
	
</body>
</html>