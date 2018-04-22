<%@page import="model.dao.ProductDao"%>
<%@page import="model.Size"%>
<%@page import="model.Ingredient"%>
<%@page import="java.util.HashSet"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<table border = "0">
	<tr>
		<th>Name</th>
		<th>Price</th>
		<th></th>
	</tr>
	<c:forEach var="product" items="${application.getAttribute("products")}">
		<tr>${product.name}</tr>
		<tr>${product.price}</tr>
		<tr align="center"><a href="shoppingcart">Add to cart</a></tr>
		
	</c:forEach>
	
</table>
