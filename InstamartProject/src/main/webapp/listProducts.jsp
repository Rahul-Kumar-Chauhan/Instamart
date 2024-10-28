<%@page import="BO.ProductBO" import ="java.util.ArrayList"
import ="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@page 
import ="BO.BrandBO"
import ="BO.CatalogServiceAreaBO"
import ="BO.CatalogServiceSectorBO"
import ="BO.MaterialBO"
import ="BO.ProductCategoryBO"
import ="BO.ProductTypeBO"
import ="Domain.Brand"
import ="Domain.CatalogServiceArea"
import ="Domain.CatalogServiceSector"
import ="Domain.Material"
import ="Domain.Product"
import ="Domain.ProductCategory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Products</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css">
	</head>
	<body>
		<div id="container">
			<div id="left-sec">
				<div id="logo">
					<img alt="logo" src="resources/images/logo.svg" >
				</div><br>
				<div id="nav-list">
					<div><a href="">Dashboard</a></div>
					<div id="productsPage"><a href="Products">Products</a></div>
					<div id="packagePage"><a href="RentalPackages">Packages</a></div>
					<div class="active"><a href="Rentals">My Rentals</a></div>
					<div id="rentalPage"><a href="">My Orders</a></div>
					<div id="settings"><a href="settings.jsp">Settings</a></div>
				</div>			
			</div>
			<% 
				String role=(String)session.getAttribute("role");
			%>
			<div id="middle-sec">
				<div id="page-head">
					<div class="head">Home</div>
					<div><b>&ensp;/&ensp;</b></div>
					<div class="head">Products</div>
					<% if(role.equalsIgnoreCase("Manager")){ %>
					<button id="uploadProduct">UPLOAD</button>
					<%} %>
				</div><br>
				<div>Products
				<% if(role.equalsIgnoreCase("Manager")){ %>
				<button id="createProduct"><a href="createProduct.jsp">ADD NEW</a></button><br><br>
				<%} %>
				</div>
				<%if(role.equalsIgnoreCase("Manager")){ %><br><%} %>
				<% if(role.equalsIgnoreCase("Customer")){ %>
				<form action="Products">
				<div id="filter-sec"><input type="text" name="city" id="city" placeholder="City"><input type="submit" id="filter" value="Filter"></div><br>
				</form>
				<%}%>
				<div id="product-container">
				<%	
					ProductBO pBO=new ProductBO();
					List<Product> pList=new ArrayList<Product>();
					if(role.equalsIgnoreCase("Customer") ){
						pList=pBO.obtainAllProducts(true, true);						}
						for(Product p : pList){
							int i=0;
							i++;
				%>
				<form action="ViewProduct" name="f1">
					<input type="hidden" value="<%=p.getId() %>" name="productId">
					<div class="productBox"><img class="productImg" alt="" src="<%=p.getImageUrl()%>"><br><br><center><b class="product"><%=p.getName() %></b><br><input type="submit" id="productDetail" value="DETAILS"></center></div>
				</form>
				<% }%>
				
			<%
				if(role.equalsIgnoreCase("Manager")){
					pList=pBO.obtainAllProductsForManager();
					for(Product p : pList){
						int i=0;
						i++;
			%>
			<form action="ViewProduct" name="f2">
				<input type="hidden" value="<%=p.getId() %>" name="productId">
				<div class="productBox"><img class="productImg" alt="" src="<%=p.getImageUrl()%>"><br><br><center><b class="product"><%=p.getName() %></b><br><input type="submit" id="productDetail" value="DETAILS"></center></div>
			</form>
			<%} }%>
				</div>
				</div>
			<div id="right-sec">
				<div id="logout"><a href="Logout">LOGOUT</a></div>
			</div>
		</div>
	</body>
</html>