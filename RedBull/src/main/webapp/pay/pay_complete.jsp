<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	<style>
		textarea {
			width: 50%;
			height: 200px;
			padding: 10px;
			box-sizing: border-box;
			border: solid 2px #1E90FF;
			border-radius: 5px;
			font-size: 16px;
			resize: both;
			position: center;
		}
	</style>

</head>
<body>
 	<jsp:include page="/main/header.jsp"></jsp:include>

<hr/>


	<h1>������ �Ϸ�Ǿ����ϴ�.</h1>
	<form>
		<textarea>���⿡ �ؽ�Ʈ�� �Է��մϴ�!</textarea>
	</form>

<hr/>

   	<jsp:include page="/main/footer.jsp"></jsp:include>
</body>
</html>