<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

				         <form name="form">
      					<input type="text" id="cartCnt" name="cartCnt" value="1" size="2" >
				        <a href="#"  onclick='javascript_:change(1);'>▲</a><a href="#" onclick='javascript_:change(-1);'>▼</a>
				        <button type="button" class="btn btn-success btn-sm" id="updatebtn" >변경</button>
				        </form>
    
    
  <script language='javascript'>
 function change(num)
 {
 var x  = document.form;
 var y = Number(x.cartCnt.value) + num;
 if(y < 1) y = 1;
 x.cartCnt.value = y;
 }
 </script>

 

</script>

</body>
</html>