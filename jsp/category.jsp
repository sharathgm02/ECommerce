<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="java.util.ArrayList,java.util.List"%>
<%@ page import="com.via.model.Product"%>
<%@ page import="com.via.controller.Attribute" %>
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

<head>
	<title>Best Seller Book Store</title>
	<jsp:include page="includes/head_tags.jsp" />
	<% List<Product> productList = (List<Product>) request.getAttribute(Attribute.ALL_PRODUCTS.toString()); %>
</head>

<body>
	
	<!-- Header -->
	<jsp:include page="includes/header.jsp" />
	<!-- End Header -->
	
			<!-- Content -->
		<div id="content">
		<div class="products">
				<!-- Products -->
				<h3>Featured Products</h3>
				<ul>			
					<% 
					 
						for (Product product : productList) {
							request.setAttribute(Attribute.ALL_PRODUCTS.toString(), product);
					%>
					<li>
						<div class="product">
		<jsp:include page="/includes/product_list.jsp" />
		</div>
					</li>
					<%
						}
					%>

				</ul>
			<!-- End Products -->
			</div>	
			<div class="cl">&nbsp;</div>
		</div>
		<!-- End Content -->
		
	
	<!-- Footer -->
	<jsp:include page="includes/footer.jsp" />
	<!-- End Footer -->
</body>

</html>
		
