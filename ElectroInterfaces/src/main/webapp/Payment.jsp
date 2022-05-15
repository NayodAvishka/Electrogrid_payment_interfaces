<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.Payment"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="views/Form.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Components/Payment.js"></script>

</head>
<body>

<div class="container">
<div class="panel panel-default">
  <div class="panel-heading"> 
  	<h1>Payment Register Form</h1>
  	<br>
  <div class="panel-body">

			<form class="form-container" id="formPayment" name="formPayment" method="post" action="Payment.jsp">
				
		PaymentName: <input
			id="PaymentName" name="paymentName" type="text"
			class="form-control form-control-sm"> <br> 
			
		Paymenttype: <input
			id="Paymenttype" name="paymenttype" type="text" 
			class="form-control form-control-sm"> <br> 
			
			
		PaymentDesc: <input
			id="PaymentDesc" name="paymentDesc" type="text" 
			class="form-control form-control-sm"> <br>	
			
		Payment date: <input 
			id="Paymentdate" name="paymentdate" type="text"
			class="form-control form-control-sm"> <br> 
			
			<input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> 
			
			<input type="hidden"
			id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
			
			<input id="btnreset" name="reset" type="reset" value="Reset"
			class="btn btn-danger">
			
	</form>
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	
	</div>
	</div>
	</div>
	
	
	<br>

<%
 Payment PaymentObj = new Payment(); 
 out.print(PaymentObj.readPayment()); 
%>

</div>
</body>
</html>