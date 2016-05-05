
<%@ page import="java.util.Map"%>
<%@ page import="com.via.model.Category" %>
<%@ page import="com.via.product.ProductBean" %>
<%@ page import="com.via.controller.Attribute" %>
<%@ page import="com.via.product.ProductManager" %>

<% Map<Long,Category> categoriesMap = ProductManager.getAllCategories(); %>

<!-- <html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
 -->

	<div>
		<div id="sidebar">
			<ul class="categories">
				<li>
					<h4>Categories</h4>
				<ul>	
					<%
						
						for (Map.Entry<Long, Category> entry : categoriesMap.entrySet())  {
							Category category = entry.getValue();
				
					%>
					<%-- <li><a href="/Category/<%=category.getCategoryID()%>"><%=category.getCategoryName()%></a></li> --%>
					<li><a href="<%=ProductBean.getCategoryUrl(request, category)%>"><%=category.getCategoryName()%></a></li>		
					<%
						}
					%>
				</ul>	
					
				</li>				
			</ul>
		</div>
	</div>
	
<!-- </html> -->