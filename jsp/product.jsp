<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.ArrayList,java.util.List"%>
<%@ page import="com.via.model.Product"%>
<%@ page import="com.via.model.ProductDetail"%>
<%@ page import="com.via.controller.Attribute" %>
<%@ page import="com.via.order.OrderBean"%>
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

<head>
	<title>Product Details</title>
	<jsp:include page="/includes/head_tags.jsp" />
	<% Product product = (Product)request.getAttribute(Attribute.PRODUCT_VIEW.toString()); %>
	<% ProductDetail productDetail = product.getProductDetail(); %>
</head>

<body>
	<!-- Header -->
	<jsp:include page="/includes/header.jsp" />
	<!-- End Header -->
	
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			<div class="products">
								
				<h3> <%=product.getProductName()%></h3>
				<ul>	
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src="<%=product.getImageUrl()%>" alt="Image" width="160px" height="200px" />
								 </span>
								 <% if(productDetail!=null){ %> 
								 	<img src="<%=productDetail.getImageUrls()%>" alt="Image" style=" width:100px; height:100px;" /> 
								 <%} %>							
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<!-- <span class="holder"> -->
								<br />
								<h4> by <%=product.getAuthor()%> </h4>
								<br />
								<span class="description"><%=product.getSummary()%><br /> <br /></span>
								<br />
								<h3> Rs. <%=product.getUnitPrice() %> </h3>
												
							<!-- </span> -->
						</div>
					</li>

					<li>
						<div class="product">	
							<br/>
							<h3>  <%=product.getStatus() %> </h3>
							<br/>	
							<h4> Choose Quantity </h4>
							<form method="post" action="<%=OrderBean.getOrderUrl(request)%>">
							
								<!-- <input type="text" id="quantity" name="quantity" value="1" style="width:30px; height:20px;"/> -->
								<select id="quantity" name="quantity" onchange="getAmount(<%=product.getUnitPrice()%>)" style="width:30px; height:20px;">
									<%for(int i=1;i<=10 && i<=product.getQuantity();i++){%>
									<option value="<%=i%>"><%=i%></option>
									<%} %>
								</select>
								<%-- <button type="button" onclick="getAmount(<%=product.getUnitPrice()%>)"> Save </button> --%>
								<br/><br/>
								<div id="viewAmount" style="display:none;">
								<font size="4"> Total : Rs. </font>
								<font size="4" id="amount"> <%=product.getUnitPrice()%>  </font> 
								</div>
								<br /><br /><br /> <br />
							 
								<input type="hidden" name="productId" value=<%=product.getProductID()%> />
								<input type="hidden" id="total" name="total" value="<%=product.getUnitPrice()%>" />
								<input class="buy-btn" type="submit" value="BUY NOW" />
							</form>
						</div>	
					</li>		
				</ul>
			</div>
			<ul>	
				<li>
					<% if(productDetail!=null){ %>
						<div class="product">
							<label> <%=productDetail.getDescription() %></label>	
						</div>
					<%} %>
				</li>
			</ul>
		</div>
		<!-- End Content -->	
		
	
	<!-- Footer -->
	<jsp:include page="includes/footer.jsp" />
	<!-- End Footer -->
	
		<script>
			function getAmount(unitPrice) {
				var quantity = document.getElementById("quantity");
				
				if(!(/^[0-9]*[1-9][0-9]*$/.test(quantity.value))){
					quantity.value = 1;
					alert("Quantity is not a valid number.Please Re-enter");
				}
				
				if(quantity.value><%=product.getQuantity()%>){
					quantity.value = 1;
					alert("Quantity exceeds the Stock available with us. Please Re-enter");
				}
				
    			document.getElementById("amount").innerHTML = quantity.value * unitPrice;
    			document.getElementById("total").value = quantity.value * unitPrice;
				document.getElementById("viewAmount").style.display = "block";

			}
		</script>
	
</body>

</html>