<%@ page import="com.via.product.ProductBean" %>
<%@ page import="com.via.model.Product"%>
<%@ page import="com.via.controller.Attribute" %>

<% Product product = (Product) request.getAttribute(Attribute.ALL_PRODUCTS.toString()); %>
	<a href="<%=ProductBean.getProductUrl(request,product)%>" class="info">
		<span class="holder">
			<img src="<%=product.getImageUrl()%>" alt="Image" />
			<span class="book-name"><%=product.getProductName()%></span>
			<span class="author">by <%=product.getAuthor()%></span>
			<span class="description"><%=product.getSummary()%><br /> <br /></span>
		</span>
	</a>
	<a href="#" class="buy-btn">BUY NOW <span class="price"><span class="low">Rs.</span><%=product.getUnitPrice()%><span class="high"></span></span></a>
						