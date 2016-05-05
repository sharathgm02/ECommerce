<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.via.model.Product"%>
<%@ page import="com.via.order.OrderBean"%>
<%@ page import="com.via.controller.Attribute" %>
<%@ page import="com.via.controller.Action" %>
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

<head>
	<title>Buy Book</title>
	<jsp:include page="/includes/head_tags.jsp" />
	
</head>

<body>
	
	<!-- Header -->
	<jsp:include page="/includes/header.jsp" />
	<!-- End Header -->

	<div id="content"> 
			<!-- Products -->
			<div class="products">
				<h3>Cart</h3>
				<ul>
					<%  Product product = (Product)request.getAttribute(Attribute.PRODUCT_VIEW.toString()); %>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src="<%=product.getImageUrl()%>" alt="Image" width="160px" height="200px" />
								</span>	
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<!-- <span class="holder"> -->
								<h3> <%=product.getProductName()%></h3>
								<br />
								<h4> by <%=product.getAuthor()%> </h4>
								<br />
								<span class="description"><%=product.getSummary()%><br /> <br /></span>
								<br /> <br />				
							<!-- </span> -->
						</div>
					</li>
					<li>
						<div class="product">		
							<br /><br /> <br /> <br />
							<h3> Quantity </h3>
							
								<input type="text" id="quantity" value="1"/>
								<button type="button" onclick="getAmount(<%=product.getUnitPrice()%>)"> Save </button> 
						
							<br /><br /><br /> <br />
							<!-- <a href="#" class="buy-btn">BUY NOW</a> -->
						</div>	
					</li>
				</ul>
			</div>
			
			<font size="4"> Total Amount : Rs. </font>
			<font size="4" id="amount"> <%=product.getUnitPrice()%>  </font> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form action="<%=OrderBean.getOrderUrl(request)%>" method="post">
				<input type="hidden" name="prodId" value="<%=product.getProductID()%>" />
				<input type="hidden" id="total" name="total" value="<%=product.getUnitPrice()%>" />
				<input type="hidden" id="quant" name="quant" value="1"/>
				<input type="submit" value="Place Order" style="width:100px; height:40px; font-size:large;" />
			</form>
	</div>
	<!-- End Content -->	
		
	
	<!-- Footer -->
	<jsp:include page="includes/footer.jsp" />
	<!-- End Footer -->
	
	<script>
			function getAmount(unitPrice) {
				var quantity = document.getElementById("quantity").value;
				
				if(!(/^[0-9]*[1-9][0-9]*$/.test(quantity))){
					document.getElementById("quantity").value = quantity = 1;
					alert("Quantity is not a valid number.Please Re-enter");
				}
				
				if(quantity><%=product.getQuantity()%>){
					document.getElementById("quantity").value = quantity = 1;
					alert("Quantity exceeds the Stock available with us. Please Re-enter");
				}
				
				document.getElementById("quant").value = quantity;
    			document.getElementById("amount").innerHTML = quantity * unitPrice;
    			document.getElementById("total").value = quantity * unitPrice;
			

			}
	</script>
</body>

</html>