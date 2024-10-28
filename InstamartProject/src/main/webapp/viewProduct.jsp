<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Domain.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css">
	</head>
	
	</body><body>
		<%Product p=(Product)request.getAttribute("product"); %>
		<div id="container">
			<div id="left-sec">
				<div id="logo">
					<img alt="logo" src="resources/images/logo.svg" >
				</div><br>
				<div id="nav-list">
					<div><a href="customerHome.jsp">Dashboard</a></div>
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
					<div class="head">Products</div>
					<div><b>&ensp;/&ensp;</b></div>
					<div class="head"><%=p.getName() %></div>
					<div id="" style="float:right; background: white; align-content: center;text-align:center; height:35px; width: 200px; padding: 5px "><a>MY CART</a></div>
				</div><br>
				<div>
					<div id="productView">
							<div><img alt="" src="<%=p.getImageUrl() %>" width="100%" height="200px"></div>
							<div>
								<h3 class="headLines"><%=p.getName() %></h3>
								<input style="border: 0px; background: #CEDCED" type="text" id="originalRate" readonly="readonly" value="&#8377;<%=request.getAttribute("costPerMonth") %>/month"><br><br>
								<table>
									<tr><td><b>Material : </b> </td><td id="material"><%=p.getMaterial().getName() %>
									<tr><td><b>Brand : </b> </td><td id="brand"><%=p.getBrand().getName() %>
									<tr><td><b>Dimensions : </b> </td id="dimentions"><td><%=p.getDimensions() %>
									<tr><td><b>Product Category : </b> </td id="pro-category"><td><%=p.getProductCategory().getCategory() %>
								</table>
							</div>
					</div><br>
					<h3>Make your RMI(Rental Monthly Installment plan)</h3><br>
					<div id="tenure">					
					    <%int price=(int)request.getAttribute("costPerMonth") ;
					    double costPerMonthFor6MonthPlan=price-price*0.04;
					    double costPerMonthFor9MonthPlan=price-price*0.06;
					    double costPerMonthFor12MonthPlan=price-price*0.10;%>
						<form action="MyCart"><input type="hidden" name="tenure" value=6><%session.setAttribute("totCost",costPerMonthFor6MonthPlan*6); %><div><center><br><p><b>6 month plan</b></p><br><del>&#8377;<%=price %>/month</del><br><b style="font-size: 30px">&#8377;<%=costPerMonthFor6MonthPlan %>/</b>month<br><br><p>&#8377;<%=costPerMonthFor6MonthPlan*6 %> for 6 months</p><br> <input type="hidden" name="monthCost" value="<%=session.getAttribute("costPerMonth")%>"><input type="hidden" name="totCost" value="<%=costPerMonthFor6MonthPlan*6 %>"> <input type="submit" id="addToCart" value="ADD TO CART"></center></div></form>
						<form action="MyCart"><input type="hidden" name="tenure" value=9><%session.setAttribute("totCost",costPerMonthFor9MonthPlan*9); %><div><center><br><p><b>9 month plan</b></p><br><del>&#8377;<%=price %>/month</del><br><b style="font-size: 30px">&#8377;<%=costPerMonthFor9MonthPlan %>/</b>month<br><br><p>&#8377;<%=costPerMonthFor9MonthPlan*9 %> for 9 months</p><br><input type="hidden" name="monthCost" value="<%=session.getAttribute("costPerMonth") %>"><input type="hidden" name="totCost" value="<%=costPerMonthFor9MonthPlan*9 %>"><input type="submit" id="addToCart" value="ADD TO CART"></center></div></form>
						<form action="MyCart"><input type="hidden" name="tenure" value=12><%session.setAttribute("totCost",costPerMonthFor12MonthPlan*12); %><div><center><br><p><b>12 month plan</b></p><br><del>&#8377;<%=price %>/month</del><br><b style="font-size: 30px">&#8377;<%=costPerMonthFor12MonthPlan %>/</b>month<br><br><p>&#8377;<%=costPerMonthFor12MonthPlan*12 %> for 12 months</p><br><input type="hidden" name="monthCost" value="<%=session.getAttribute("costPerMonth")%>"><input type="hidden" name="totCost" value="<%=costPerMonthFor12MonthPlan*12 %>"><br><input type="submit" id="addToCart" value="ADD TO CART"></center></div></form>
						</div>
					</div>
				</div>
			<div id="right-sec">
				<div id="logout"><a>LOGOUT</a></div>
			</div>
		</div>
	</body>
</html>