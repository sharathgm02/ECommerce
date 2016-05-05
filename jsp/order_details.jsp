<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.via.model.Order"%>
<%@ page import="com.via.model.OrderDetail"%>
<%@ page import="com.via.controller.Attribute" %>
<%@ page import="com.via.order.OrderBean"%>
<%@ page import="java.util.ArrayList,java.util.List"%>

<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

<head>
	<title>Best Seller Book Store</title>
	<jsp:include page="/includes/head_tags.jsp" />
	<%  Order order = (Order)request.getAttribute(Attribute.ORDER.toString()); %>
	<%  List<OrderDetail> orderDetailList = order.getOrderDetailList(); %>
</head>

<body>
	
	<!-- Header -->
	<jsp:include page="/includes/header.jsp" />
	<!-- End Header -->
		
	<div id="content"> 
		<div class="products">
			<h3>Order Details</h3>
				
			<form action="#" method="post">
				<table style="padding: 10px; border-spacing: 10px; border-collapse: separate;">
					<tr>
						<td>Order ID:</td>
						<td><%=order.getOrderID()%></td>
					</tr>
					<tr>
						<td>Order Date</td>
						<td><%=order.getCreationTime() %></td>
					</tr>
					<tr>
						<td>Total Amount</td>
						<td><%=order.getTotalAmount() %></td>
					</tr>
				</table>	
			</form>
			<br/><br/><br/>
			
			
			<%	
			 if(orderDetailList != null){ 
				for (OrderDetail orderDetail : orderDetailList) { %>
			<table style="padding: 10px; border-spacing: 10px; border-collapse: separate;">
				<tr> 
					<td> Product ID: </td>
					<td><%= orderDetail.getProductID()%></td>
				</tr>
				<tr>
					<td> Unit Price:</td>
					<td> <%=orderDetail.getUnitPrice()%></td>
				</tr>
				<tr>
					<td> Quantity: </td>
					<td> <%= orderDetail.getQuantity() %></td>
				</tr>
			</table>
			<br/><br/><br/>
			<% } 
			 } %>
			
		</div>
		
	</div>
	<jsp:include page="includes/footer.jsp" />


</body>

</html>
