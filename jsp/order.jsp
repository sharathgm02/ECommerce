<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<%@ page import="com.via.model.Product"%>
<%@ page import="com.via.order.OrderBean"%>
<%@ page import="com.via.controller.Attribute" %>

<head>
	<title>Buy Book</title>
	<jsp:include page="/includes/head_tags.jsp" />
	
</head>

<body>
	
	<!-- Header -->
	<jsp:include page="/includes/header.jsp" />
	<!-- End Header -->

	<div id="content"> 
	
		<form action=<%=OrderBean.getConfirmationUrl(request)%>  method="post">
			<table style="padding: 10px; border-spacing: 10px; border-collapse: separate;">
				
				<tr>
					<td>
						<font size="4"> Email ID : </font>
					</td>
					<td>
						<input type="text" id="email" name="email" style="width:300px; height:20px;" />
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4"> Shipping Address : </font>
					</td>
					<td>
						<textarea rows="4" cols="50" name="address"> </textarea>
					</td>
				</tr>
				
				<tr>
					<td> 
						<font size="4"> Total Amount : </font>
					</td>
					<td>
						<%=request.getParameter("total")%>
					</td>
				</tr>
				<tr>
					<td>
						<font size="4"> Payment Method : </font>
					</td>
					<td>
						<input type="radio" name="mode" value="CashOnDelivery" /> Cash On Delivery <br />
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="radio" name="mode" value="Credit Card" /> Credit Card <br />
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="hidden" name="totalAmount" value="<%=request.getParameter("total")%>"/>
						<input type="hidden" name="quantity" value="<%=request.getParameter("quantity")%>"/>
						<input type="hidden" name="productId" value="<%=request.getParameter("productId")%>"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="submit" value="submit" name="submit" onclick="return validateMyForm()" />
					</td>
				</tr>
			</table>
		</form>
	
	</div>
	
	<!-- Footer -->
	<jsp:include page="includes/footer.jsp" />
	<!-- End Footer -->
	
	<script>
	function validateMyForm(){
		
		var emailValid = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var alertMsg = " Invalid email. Please Re-enter";
		
		if(!(document.getElementById('email').value.match(emailValid))){
			 alert(alertMsg);
			 return false;
		}
		return true;
	}
	</script>
		
</body>

</html>
			
			
			
			
			
			