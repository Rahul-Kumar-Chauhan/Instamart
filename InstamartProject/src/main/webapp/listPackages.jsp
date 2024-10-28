<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import ="Domain.RentalPackage" import="BO.RentalPackageBO" import ="java.util.ArrayList" import="java.util.List"
    
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Packages</title>
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
			<div id="middle-sec">
				<div id="page-head">
					<div class="head">Home</div>
					<div><b>&ensp;/&ensp;</b></div>
					<div class="head">packages</div>
				</div><br>
				<div>Packages</div><br>
				
				<div id="package-container">
				<% 	
					RentalPackage rp=new RentalPackage();
					RentalPackageBO rpBO=new RentalPackageBO();
					List<RentalPackage> rpList=rpBO.obtainAllPackages(true, true);
					for(RentalPackage i:rpList){
				%>
					<div><a href="HidePackage"><img id="visibility" class="view" alt="" src="resources/images/icons/eye.png"></a><img id="package" class="packages" alt="" src="<%=i.getImageUrl()%>"><br><br><center><b><%=i.getName() %></b><br><br><input type="submit" name="detail" Value="DETAIL"></center></div>
				<%} %>
				</div>
			</div>
			<div id="right-sec">
				<div id="logout"><a href="Logout">LOGOUT</a></div>
			</div>
		</div>
	</body>
</html>