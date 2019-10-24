<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath }" />
<html lang="ko">
  <body>
 
    <!--================Header Menu Area =================-->
 	<jsp:include page="/main/header.jsp"></jsp:include>
    <!--================Header Menu Area =================-->

    <!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center">
        <div class="container">
          <div
            class="banner_content d-md-flex justify-content-between align-items-center">
            <div class="mb-3 mb-md-0">
              <h2>����â</h2>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->

    <!--================Checkout Area =================-->
    <section class="checkout_area section_gap">
      <div class="container">
        <div class="billing_details">
          <div class="row">
            <div class="col-lg-8">
            <!-- �ֹ��� ��ȸ --> 
              <h3>�ֹ���</h3>
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
                  <input type="text"  class="form-control" id="dname" name="dname" />
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="dphone"  name="dphone" />
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="email" name="compemailany"/>
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="dpostnum" name="compemailany" />
                </div>
				
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control"  id="daddr" name="add1"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="ddetaddr"  name="add2" />
                </div>
                </div>
                <!-- ���� ���� -->
	             <div class="col-lg-4">
	              <div class="order_box">
	                <h2>���� ����</h2>
	                <ul class="list list_2">
	                  <li>
	                    <a href="#">��ǰ �� �ݾ�
	                      <span>$2160.00</span>
	                    </a>
	                  </li>
	   
	                  <li>
	                    <a href="#">����Ʈ ���
	                      <span>$2210.00</span>
	                    </a>
	                  </li>
	                  <li>
	                    <a href="#">���� ���� �ݾ�
	                      <span>$2210.00</span>
	                    </a>
	                  </li>
	                </ul>
	                <div class="payment_item">
	                    <h2>���� ����</h2>
	                </div>
	                <div class="payment_item active">
	                  <div class="radion_btn">
	                    <input type="radio" id="f-option6" name="selector" />
	                    <label for="f-option6">ī����� </label>
	                    <div class="check"></div>
	                  </div>
	                   <%-- <img src="${context}/resources/img/product/single-product/card.jpg" alt="" /> --%> 
	                </div>
	                <a class="main_btn" href="#">�����ϱ�</a>
	                </div>
	              </div>
              </form>
                
               <!-- ����� �Է� --> 
               <div class="col-lg-8">
               <h3>�����</h3>
              <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
                  <input type="text"  class="form-control" id="dname" name="name" placeholder="�̸�"/>
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="dphone"  name="dphone"  placeholder="��ȭ��ȣ"/>
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input  type="text" class="form-control" id="email" name="compemailany" placeholder="�̸���" />
                </div>
                
				<div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" name="dpostnum" id="dpostnum" name="compemailany" placeholder="�����ȣ"/><br/>
                  <button type="button" style="height:45px; width:130px; font-family: 'Poppins'; font-weight: bold; 
                   padding: 5px; border: none; border-radius: 5px" onclick="execPostCode();">
                   <i class="fa fa-search"></i> �����ȣ ã��</button>
                </div>
				
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control"  id="daddr" name="daddr" placeholder="�ּ�1"/>
                  <!-- <span  class="placeholder" data-placeholder="�ּ�1" ></span> -->
                </div>
                
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="ddetaddr"  name="ddetaddr" placeholder="�ּ�2" />
                 <!--  <span class="placeholder" data-placeholder="�ּ�2" ></span> -->
                </div>

                <div class="col-md-12 form-group">
                  <textarea  class="form-control"  name="dmemo"  id="dmemo" rows="1" placeholder="��۸޸�" ></textarea>
                </div>
              </div>
             </form>
			  
            <%-- <div class="col-lg-4">
              <div class="order_box">
                <h2>���� ����</h2>
                <ul class="list list_2">
                  <li>
                    <a href="#"
                      >��ǰ �� �ݾ�
                      <span>$2160.00</span>
                    </a>
                  </li>
   
                  <li>
                    <a href="#"
                      >����Ʈ ���
                      <span>$2210.00</span>
                    </a>
                  </li>
                  <li>
                    <a href="#"
                      >���� ���� �ݾ�
                      <span>$2210.00</span>
                    </a>
                  </li>
                </ul>
                <div class="payment_item">
                    <h2>���� ����</h2>
                </div>
                <div class="payment_item active">
                  <div class="radion_btn">
                    <input type="radio" id="f-option6" name="selector" />
                    <label for="f-option6">ī����� </label>
                    <div class="check"></div>
                  </div>
                   <img src="${context}/resources/img/product/single-product/card.jpg" alt="" /> 
                </div>
                </div>

                <a class="main_btn" href="#">�����ϱ�</a>
              </div> --%>
          </div>
        </div>
      </div>
    </section>
    <!--================End Checkout Area =================-->
       <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    
	 // �����ȣ ��ȸ
	    function execPostCode() {
		 alert("execPostCode");
	        new daum.Postcode({
	            oncomplete: function(data) {
	               // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.
	
	               // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
	               // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
	               var fullRoadAddr = data.roadAddress; // ���θ� �ּ� ����
	               var extraRoadAddr = ''; // ���θ� ������ �ּ� ����
	
	               // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
	               // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
	               if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
	                   extraRoadAddr += data.bname;
	               }
	               // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
	               if(data.buildingName !== '' && data.apartment === 'Y'){
	                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	               }
	               // ���θ�, ���� ������ �ּҰ� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
	               if(extraRoadAddr !== ''){
	                   extraRoadAddr = ' (' + extraRoadAddr + ')';
	               }
	               // ���θ�, ���� �ּ��� ������ ���� �ش� ������ �ּҸ� �߰��Ѵ�.
	               if(fullRoadAddr !== ''){
	                   fullRoadAddr += extraRoadAddr;
	               }
	
	               // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
	               console.log(data.zonecode);
	               console.log(fullRoadAddr);
	               
	               
	               $("[name=dpostnum]").val(data.zonecode);
	               $("[name=daddr]").val(fullRoadAddr);
	               
	               /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5�ڸ� �������ȣ ���
	               document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
	               document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
	           }
	        }).open();
	    }
    
    </script>

    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
  </body>
</html>

