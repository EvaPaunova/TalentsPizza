<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Shopping Cart</title>
</head>
<body>
<table border="1">
		<tr>
			<th>Name</th>
			<th>Ingredients</th>
			<th>Size</th>
			<th>Price</th>
			<th>Quantity</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${session.getAttribute("cart")}">
			<tr>
				<td><c:out value="${item.name}"></c:out></td>
				<td>
				<c:forEach var="ingredient" items="${item.ingredients}">
						<c:out value="${ingredient.name }"></c:out>
				</c:forEach>
				</td>
				<td><c:out value="${ item.size }"></c:out></td>
				<td><c:out value="${ item.price }"></c:out></td>
				<td></td>
				<td>
					<form action="delete" method="post">
					
						<input type="hidden" name="productId" value="${ item.id }" />
						<input type="hidden" name="currentQuantity" value="${ item.quantity }" />
						<input type="hidden" name="Quantity" min="1" max="${ item.quantity }" />
						<input type="submit" name="delete" value="Delete" />
					</form>				
						
				</td>
			</tr>
		</c:forEach>
</table>
</body>
</html>