<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Domain.Address" import="BO.AddressBO" import="java.util.ArrayList" import="java.io.IOException" import="java.sql.SQLException"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My Cart</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css">
	</head>
	<body>
		<div id="container">
			<div id="left-sec">
				<div id="logo">
					<img alt="logo" src="resources/images/logo.svg" >MyCart
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
					<div class="head"></div>
					<div id="" style="float:right; background: white; align-content: center;text-align:center; height:35px; width: 200px; padding: 5px "><a>MY CART</a></div>
				</div><br>
				<div id="main">
					<div id="myCart">
							<div id="cart-logo"><img alt="Cart Logo" src="resources/images/icons/large-cart.png"></div>
							<div id="payAmt"></div>
					</div><br>
					<h1><img alt="Location-icon" src="resources/images/icons/large-location.png">Choose a delivery address</h1><br>
					<div id="address-containers">
					<%	
						int userId=(int) session.getAttribute("id");
						AddressBO addressBO=new AddressBO();
						System.out.println(request.getAttribute("addressId"));
						ArrayList<Address> addressList = null;
						try {
							addressList=addressBO.findByUserId(userId);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(request.getAttribute("addressId") == null){
							for(Address a : addressList){
							int i=0;
							i++;
					%>				
					    <div id="address">
					    	<form action="MyCart">
					    	<input type="hidden" name="addressId" value="<%=a.getId()%>">
					    	<%=a.getStreet() %><br>
					    	<%=a.getCity() %><br>
					    	<%=a.getState() %><br>
					    	<%=a.getCountry() %>,<%=a.getPincode() %><br><br>
					    	<input type="submit" id="deliver" value="DELIVER HERE"><br>
					    	</form>
					    </div>
					 <%} } %>
					 <% 
					 System.out.println(request.getAttribute("addressId"));
					 	if(request.getAttribute("addressId") != null) {
						 	int addressId=(int) request.getAttribute("addressId");
						 	System.out.println(addressId);
						 	session.setAttribute("addressId", addressId);
						%>
					 <div id="address">
					    	<h1><img alt="Location-icon" src="resources/images/icons/large-location.png">Delivery Address</h1><br>
					    	<%=addressBO.findById(addressId).getStreet() %><br>
					    	<%=addressBO.findById(addressId).getCity() %><br>
					    	<%=addressBO.findById(addressId).getState() %><br>
					    	<%=addressBO.findById(addressId).getCountry() %>,<%=addressBO.findById(addressId).getPincode() %>
					    	</div>
					    <form action="Rentals">
					    <div id="delivery-date-time">
					    	<input type="text" name="deliveryDate" placeholder="dd/MM/yyyy">
					    	<input type="text" name="deliveryTime" placeholder="HH AM/PM">
					    </div>
					    <div id="payment">
					    		<img alt="Card Logo" src="resources/images/icons/large-card.png">Payment
					    		<input type="submit" id="buy" value="PROCEED">
					    </div>
					    </form>
					 	<%} %>
					</div>
				</div>
			<div id="right-sec">
				<div id="logout"><a>LOGOUT</a></div>
			</div>
		</div>
		</body>
</html>