<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> list = (ArrayList<JavaBeans>) request.getAttribute("getAllContacts");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Book</title>
<link rel="icon" href="images/5g.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<main class="main-settings">
		<h1>Contact Book</h1>
		<span id="contact-header">
			<a href="new.html" class="button">Add</a> <a href="report"
			target="_blank" id="delete">Report</a>
		</span>
		<table id="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Phone</th>
					<th>E-mail</th>
					<th>Options</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td><%=list.get(i).getId()%></td>
					<td><%=list.get(i).getName()%></td>
					<td><%=list.get(i).getPhone()%></td>
					<td><%=list.get(i).getEmail()%></td>
					<td><a href="select?id=<%=list.get(i).getId()%>"
						class="button">Edit</a> <a
						href="javascript: confirmer(<%=list.get(i).getId()%>)"
						class="button" id="delete" onclick="confirm.js">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</main>
	<script src="scripts/confirm.js"></script>
</body>
</html>