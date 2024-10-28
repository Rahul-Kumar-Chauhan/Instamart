<%@page import="BO.ProductCategoryBO" import="Domain.ProductCategory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page
    	import="java.util.ArrayList"
		import= "java.util.List"
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css">
	</head>
	<body>
		<div id="container">
			<div id="left-sec">
				<div id="logo">
					<img alt="logo" src="resources/images/logo.svg" >
				</div><br>
				<div id="nav-list">
					<div><a >Dashboard</a></div>
					<div id="productsPage"><a href="Products">Products</a></div>
					<div id="packagePage"><a href="RentalPackages">Packages</a></div>
					<div id="location"><a>Locations</a></div>
					<div id="brand"><a>Brands</a></div>
					<div id="catalog"><a>Catalogs</a></div>
				</div>
			</div>
			<div id="middle-sec">
				<div id="page-head">
					<div class="head">Home</div>
					<div><b>&ensp;/&ensp;</b></div>
					<div class="head">Categories</div>
				</div><br>
				<div>Categories</div><br>
				<div id="category-container">
				<% 	
					ProductCategory pc=new ProductCategory();
					ProductCategoryBO pcBO=new ProductCategoryBO();
					List<ProductCategory> pcList=pcBO.listAllProductCategories();
					for(ProductCategory i:pcList){
						String category=i.getCategory();
						if(category.equals("Furniture")){
				%>
							<div><center><img class="eye" alt="" src="resources/images/icons/eye.png"><br><img class="product-category" alt="" src="resources/images/products/chair.png"><br><br><%=i.getCatalogServiceArea().getCategory() %>-<%=category %></center></div>
				<%
						}
						if(category.equals("Electronics")){
				%>
							<div><center><img class="eye" alt="" src="resources/images/icons/eye.png"><br><img class="product-category" alt="" src="resources/images/products/camera.png"><br><br><%=i.getCatalogServiceArea().getCategory() %>-<%=category %></center></div>

				<% 
						}
						if(category.equals("Appliances")){
				%>
							<div><center><img class="eye" alt="" src="resources/images/icons/eye.png"><br><img class="product-category" alt="" src="resources/images/products/washing.png"><br><br><%=i.getCatalogServiceArea().getCategory() %>-<%=category%></center></div>
						
					<%}} %>
			</div>
				</div>
			<div id="right-sec">
				<div id="logout"><a href="Logout">LOGOUT</a></div>
			</div>
		</div>
	</body>
</html>