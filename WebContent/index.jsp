<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Openmatics REST serice DEMO</title>
<link rel="stylesheet" type="text/css" href="Novy3.css">
</head>
<body>

	<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>

	<%
		String action = request.getParameter("action");
		if (action !=null && action.equals("submitted")) {
				request.getRequestDispatcher("/servlet").forward(request,	response);
			}
		
	%>

	<br>
	<br>
	
	
	<div class="login-block">
	<form action="/RESTwheatherYahooDemoServlet/index.jsp" method="post">
		
		<input type="hidden" name="action" value="submitted" />
		<h1>Yahoo Weather API - insert city in Czech Republic</h1>
		
		<input type="text" name="city" value="Plzen" /> </p>
		<button>Find</button>
		
	</form>
	</div>



</body>
</html>