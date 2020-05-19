<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Error Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>


	<ul class="nav nav-pills" role="tablist">
		<li role="presentation" class="active"><a href="home.jsp">Home</a></li>
		<li role="presentation"><a href="login">Login</a></li>
		<li role="presentation"><a href="registration">Registration</a></li>
	</ul>
	</br>
	</br>
	</br>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${errorMessage}</td>
		</tr>
	</table>
</body>
</html>
