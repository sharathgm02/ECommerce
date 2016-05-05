<%@ page import="com.via.util.UrlUtility" %>
<%@ page import="com.via.util.EnumUtility" %>
<%@ page import="com.via.controller.Action" %>
<!-- <html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
 -->
	<!-- Header -->
	<div id="header" class="shell">
		<div id="logo"><h1><a href="<%= UrlUtility.getActionUrl(request, EnumUtility.parseEnum("home", Action.class, Action.HOME))%>">BestSeller</a></h1><span></span></div>
		<!-- Navigation -->
		<div id="navigation">
			<ul>
				<li><a href="<%= UrlUtility.getActionUrl(request, EnumUtility.parseEnum("home", Action.class, Action.HOME))%>">Home</a></li>
				<!-- <li><a href="/home">Products</a></li> -->
				<li><a href="#">Promotions</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contacts</a></li>
			</ul>
		</div>
		<!-- End Navigation -->
		
		<div class="cl">&nbsp;</div>
		<!-- Login-details -->
		<div id="login-details">
			<p>Welcome, <a href="#" id="user">Guest</a> .</p><p><a href="#" class="cart" ><img src="/static/images/cart-icon.png" alt="" /></a>Shopping Cart (0) <a href="#" class="sum">$0.00</a></p>
		</div>
		<!-- End Login-details -->
	
	</div>
	<!-- End Header -->
	
	<!-- Main -->
	<div id="main" class="shell">
		<jsp:include page="category_list.jsp" />
	
	

<!-- </html> -->