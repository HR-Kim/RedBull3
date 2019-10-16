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
	<h2>옵션 추가</h2>
	<br>
	<form id="opt_frm" name="opt_frm" enctype="multipart/form-data">
		<input type="hidden" name="root_path" value=<%=uploadPath%> size="15"><br/>
		<input type="hidden" id="nextPnum" name="nextPnum" value="${nextPnum}" size="15"><br/>
		<input type="hidden" id="nextInum" name="nextInum" value="${nextInum}" size="15"><br/>
		<input type="hidden" id="nextOnum" name="nextOnum" value="${nextOnum}" size="15"><br/>
		<table class="table" id="optAddTable">
			<tbody>
				<c:forEach var="opt" items="${newOptList}">
					<tr name="tr_opt">
						<td><input type="text" name="oName" placeholder="옵션명" value="${opt.oName}" /></td>
						<td><input type="text" name="oPrice" placeholder="가격(원)" value="${opt.oPrice}" /></td>
						<td><a href="#this" name="oFile_delete" class="btn">삭제하기</a></td>
					</tr>
				</c:forEach>
				<tr name="tr_opt"><!--OPT : ONAME,OPRICE,PNUM,INUM // IMAGE : REFNUM,ORG_FILE_NM,SAVE_FILE_NM,FILE_SIZE,EXT_NM-->
					<td><input type="text" name="oName" placeholder="옵션명" /></td>
					<td><input type="text" name="oPrice" placeholder="가격(원)" /></td>
					<td><a href="#this" name="oFile_delete" class="btn">삭제하기</a></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="99"><input type="button" id="input_add" value="옵션 추가"/></td>
				</tr>
			</tfoot>
		</table>
	</form>
	<div class="form-group">
		<a class="btn btn-primary" href="javascript:saveOpt();">등록</a>
		<a class="btn btn-primary" href="${context}/product/product_mng.jsp">취소</a>
	</div>
	<!--================footer Area  =================-->
	<jsp:include page="/main/footer.jsp"></jsp:include>
	<script>
		//등록
		function saveOpt(){
			//alert("saveOpt");
			//입력값 확인
			if(form_validate()==false)return;
			
			var arr = new Array();
			
			$("tr[name='tr_opt']").each(function (i){
				var obj = new Object();
				
				obj.oName  = $("input[name='oName']").eq(i).val();
				obj.oPrice = $("input[name='oPrice']").eq(i).val();
				obj.pNum   = $("#nextPnum").val();
				
				arr.push(obj);
				
			});
			
			//--ajax
			$.ajax({
				type : "POST",
				url : "${context}/product/do_save_option.do",
				dataType : "json",
	            contentType: false,
				data : JSON.stringify(arr),
				success : function(data) {
					//alert(JSON.stringify(arr));
					if(null != data || data.msgId == "10"){
						//alert(data.msgMsg);
						location.href = "${context}/product/do_product_mng.do";
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
		}
		//옵션 input 추가
		var inputNum = 1;
		$("#input_add").on("click",function(e){
			//alert("oFile_add");	
			e.preventDefault();
			var str = "<tr name='tr_opt'><td><input type='text' name='oName' placeholder='옵션명' /></td><td><input type='text' name='oPrice' placeholder='가격(원)' /></td><td><a href='#this' name='oFile_delete' class='btn'>삭제하기</a></td></tr>";
			$("#optAddTable").append(str);
			inputNum++;
			
			$("a[name='oFile_delete']").on("click",function(e){
		           e.preventDefault();
		           fn_fileDelete($(this));
		           inputNum--;
		       })
		});
		$("a[name='oFile_delete']").on("click",function(e){
			//alert("detail_photo_add");	
		    e.preventDefault();
		    fn_fileDelete($(this));
		    inputNum--;
		})
		
		function fn_fileDelete(obj){
			obj.closest("tr").remove();
		}
		
		//form validate
		function form_validate(){
			var ok = true;
			$("input[name='oName']").each(function (i){
				var oName = $("input[name='oName']").eq(i).val();
				if(oName=="" || oName==null){
					ok = false;
				}
			});	
			$("input[name='oPrice']").each(function (i){
				var oName = $("input[name='oPrice']").eq(i).val();
				if(oName=="" || oName==null){
					ok = false;
				}
			});
			if(ok==false)alert("입력값을 확인하세요.");
			return ok;
		}
		
	</script>
</body>
</html>