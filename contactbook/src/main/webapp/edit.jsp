<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Book</title>
<link rel="icon" href="images/5g.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<main class="auxiliar-settings">
		<h1>Update Contact:</h1>
		<form name="contactform" action="update">
			<div class="form-group">
				<label for="id">ID:</label> <input type="text" id="contactID"
					name="id" readonly value="<%out.print(request.getAttribute("id"));%>">
			</div>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" id="name"
					name="name" value="<%out.print(request.getAttribute("name"));%>">
			</div>
			<div class="form-group">
				<label for="phone">Phone:</label> <input type="text" id="phone"
					name="phone" value="<%out.print(request.getAttribute("phone"));%>">
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="text" id="email"
					name="email" value="<%out.print(request.getAttribute("email"));%>">
			</div>
			<div class="form-group">
				<input type="button" class="button" name="save" value="Save" onclick="isValid()">
			</div>
		</form>
	</main>
	<script src="scripts/validator.js"></script>
</body>
</html>