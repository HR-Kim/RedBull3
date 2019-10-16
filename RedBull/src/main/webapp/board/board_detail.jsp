<%@page import="kr.co.redbull.cmn.Search"%>
<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@page import="kr.co.redbull.board.service.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
	/** 페이지 사이즈 */
	String pageSize   = "10"  ; 	
	/** 페이지 번호 */
	String pageNum    = "1"  ;	
	/** 검색조건 */
	String searchDiv  = "" ;
	/** 검색어 */
	String searchWord = "" ;
	
 	Search search = (Search)request.getAttribute("searchVO");
 	Board board =  (Board)request.getAttribute("vo");
 	
	if(null !=search){
		pageSize   = StringUtil.nvl(search.getPageSize()+"","10");
		pageNum    = StringUtil.nvl(search.getPageNum()+"","1");
		searchDiv  = StringUtil.nvl(search.getSearchDiv(),"");
		searchWord = StringUtil.nvl(search.getSearchWord(),"");		
	}else{
		pageSize   = "10";
		pageNum    = "1";
		searchDiv  = "";
		searchWord = "";
	}

	int  maxNum = 0;     //총글수
	int  currPageNo = 1; //현재페이지
	int  rowPerPage = 10;//한페이지에 보여질 글수
	int  bottomCount= 10;//바닥에  보여질 페이지수
	//호출url
	String url       =request.getContextPath()+"/board/get_selectOne.do";
	String scriptName="search_page";
	
	String tmpMaxNum = (request.getAttribute("totalCnt")==null)?"0"
			:request.getAttribute("totalCnt").toString();
	
	maxNum     = Integer.valueOf(tmpMaxNum);
	currPageNo = Integer.valueOf(pageNum);
	rowPerPage = Integer.valueOf(pageSize);
%>
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
${vo }
	<form name="pagingForm" id="pagingForm">
		<input type="hidden" id="pageNum" name="pageNum"/>
		<input type="hidden" id="searchDiv" name="searchDiv"/>
		<input type="hidden" id="searchWord" name="searchWord"/>
		<input type="hidden" id="tNum" name="tNum"/>
		<input type="hidden" id="bNum" name="bNum"/>
		
	</form>
	<form name="updateForm" id="updateForm">
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
				<td class="text-left col-md-1 col-xs-1" style="font-size: 9pt;"></td>
				<td class="text-left col-md-1 col-xs-1" style="font-size: 9pt;">${vo.readCnt}</td>
				<td class="text-left col-md-5 col-xs-5" style="font-size: 9pt;">${vo.category}</td>
				<td class="text-center col-md-2 col-xs-2">
					<button type="button" class="btn btn-default btn-sm" id="do_update" name="do_update">수정</button>  
					<button type="button" class="btn btn-default btn-sm" id="do_delete" name="do_delete">삭제</button>  
				</td>
			</tr> 
			<tr>
				<td colspan="6" class="text-left col-md-12 col-xs-12">
					<div class="container"><%=board.getContents().replace("\n", "<br>") %></div>
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
												<table class="table table-striped table-sm" id="listTable">
										 			
										 </c:when>
										 <c:when test="${vo.cLevel==2 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" id="listTable" style="width: 95%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel==3 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" id="listTable" style="width: 90%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel==4 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" id="listTable" style="width: 85%; float: right;">
										 </c:when>
										 <c:when test="${vo.cLevel>=5 }">
											 <div class="container-fluid">
													<table class="table table-striped table-sm" id="listTable" style="width: 80%; float: right;">
										 </c:when>
									</c:choose>
									<tbody>
										<tr class="orgComment">
											<td class="text-left col-md-1 col-xs-1 cNum">${vo.cNum }</td>
											<td class="text-left col-md-1 col-xs-1">${vo.regId }</td>
											<td class="text-left col-md-1 col-xs-1">${vo.regDt }</td>
											<td class="text-left col-md-6 col-xs-6">
											<td class="text-right col-md-4 col-xs-4">
												<button type="button" class="btn btn-default btn-sm commentAdd" id="commentAdd">댓글달기</button>
												<button type="button" class="btn btn-default btn-sm commentUpdate" id="commentUpdate">수정</button>  
												<button type="button" class="btn btn-default btn-sm commentDelete" id="commentDelete">삭제</button>  
											</td>
											<td class="hide cLevel">${vo.cLevel}</td>
										</tr>
										<tr>
											<td colspan="12" class="readContents">${vo.contents}</td>
										</tr>
										<tr class="hide editComment">
											<td colspan="6">
												<div class="container-fluid">
													<div class="row">
														<div class="form-group col-xs-10">
															<input type="text" name="contents" id="contents" class="form-control input-sm contents"/>
											   			</div>
														<button type="button" class="btn btn-default btn-sm do_save_comment" id="do_save_comment">등록</button>
														<button type="button" class="btn btn-default btn-sm do_update_comment" id="do_update_comment">수정</button>
													</div>
											   	</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</td>
		</tr>
		<!-- //댓글목록 -->
		
		<!-- pagenation -->
		<tr>
			<td colspan="6">
				<div class="container-fluid text-center ">
					<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
				</div>
			</td>
		</tr>
		<!--// pagenation -->
		
		<!-- 댓글입력 폼 -->
		<tr>
			<td colspan="6">
				<div class="container-fluid">
					<table class="table" id="listTable" style="border-top: none;">
						<tbody>
							<tr class="hide orgComment" >
								<td class="text-left col-md-1 col-xs-1 cNum">0</td>
								<td class="text-left col-md-1 col-xs-1"></td>
								<td class="text-left col-md-1 col-xs-1"></td>
								<td class="text-left col-md-6 col-xs-6"></td>
								<td class="text-right col-md-4 col-xs-4"></td>
								<td class="hide cLevel">0</td>
							</tr>
							<tr class="hide">
								<td colspan="12"></td>
							</tr>
							<tr class="editComment">
								<td colspan="6">
									<div class="container-fluid">
										<div class="row">
											<div class="form-group col-xs-11">
												<input type="text" name="contents" id="contents" class="form-control input-sm contents"/>
								   			</div>
											<button type="button" class="btn btn-default btn-sm do_save_comment" id="do_save_comment">등록</button>
										</div>
								   	</div>
								</td>
							</tr>
						</tbody>
					</table>
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
	
		//paging
		function search_page(url, pageNum){
			var bNum = $("#num").text();
			var tNum = ${vo.tNum};
			var frm = document.pagingForm;
	    	frm.pageNum.value = pageNum;
	    	frm.searchWord.value = bNum;
	    	frm.bNum.value=bNum;
	    	frm.tNum.value=tNum;
	    	
	    	frm.action = url;
	    	frm.submit();
		}
		
		//버튼 누르면 대댓글 등록창 열기
		$("#listTable>tbody").on("click", ".commentAdd",function(){
			var table = $(this).parent().parent().parent().parent();
			var editComment = table.find('.editComment');
			editComment.removeClass( 'hide' );
			
			var contents = table.find('.readContents').text();
			var writeContents = table.find('.contents');
			writeContents.val("");
			
			var updateButton = table.find('.do_update_comment');
			var saveButton = table.find('.do_save_comment');
			saveButton.removeClass('hide');
			updateButton.addClass('hide');
			
		});
		
		//버튼 누르면 댓글 수정창 열기
		$(".commentUpdate").on("click", function(){
			//alert('commentUpdate');
			var table = $(this).parent().parent().parent().parent();
			
			//버튼 클릭하면 수정 창 보여주기
			var editComment = table.find('.editComment');
			editComment.removeClass('hide');
		
			//입력 창에 기존의 댓글 내용
			var contents = table.find('.readContents').text();
			var writeContents = table.find('.contents');
			writeContents.val(contents);
			
			//등록버튼은 숨기고 수정버튼만 보이기
			var updateButton = table.find('.do_update_comment');
			var saveButton = table.find('.do_save_comment');
			updateButton.removeClass('hide');
			saveButton.addClass('hide');
		});
		
		//버튼 누르면 댓글 수정
		$(".do_update_comment").on("click", function(){

			var table = $(this).parent().parent().parent().parent().parent().parent();
			var contents = table.find('.contents').val();
			var cNum = table.find('.cNum').text();
			
			console.log(cNum);
			
			//나중에 세션에서 가져와야 함!!
			var modId = "test";
				
  			//validation
			if (confirm("댓글을 수정하시겠습니까?") == false) return;
			
 			$.ajax({
				type : "GET",
				url : "${context}/comment/do_update.do",
				dataType : "html",
				data : {
					"contents" : contents,
					"modId" : modId,
					"cNum" : cNum
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
		});
		
		//버튼 누르면 댓글 삭제
		$(".commentDelete").on("click", function(){
			//alert('commentDelete');
			var table = $(this).parent().parent().parent().parent();
			var cNum = table.find('.cNum').text();
			console.log(cNum);
			
 			//validation
			if (confirm("댓글을 삭제하시겠습니까?") == false) return;
			
 			$.ajax({
				type : "GET",
				url : "${context}/comment/do_delete.do",
				dataType : "html",
				data : {
					"cNum" : cNum
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
		});
		
		//버튼 누르면 댓글 등록
		$(".do_save_comment").on("click", function(){

			var table = $(this).parent().parent().parent().parent().parent().parent();
			var tNum = ${vo.tNum};
			var refNum = $("#num").text();
			var contents = table.find('.contents').val();
			var hcNum = table.find('.cNum').text();
			var cLevel = Number(table.find('.cLevel').text())+1;
			
			//나중에 세션에서 가져와야 함!!
			var regId = "test";
				
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
					"hcNum" : hcNum,
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
		});
		
		//본문 수정
		$("#do_update").on("click", function(){
			//alert('update');	
			var bNum = $("#num").text();
			
			var frm = document.updateForm;
			frm.bNum.value=bNum;
			frm.action = "${context}/board/do_write.do";
			frm.submit();
		});
		
		//본문 삭제
		$("#do_delete").on("click", function(){
			
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