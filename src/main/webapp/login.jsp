<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="ISO-8859-1">
	<title>Login</title>
    </head>
    <h2>
		<% String error = (String) session.getAttribute("error");
		if(error != null){
			out.print(error);
			session.setAttribute("error",null);
		}
		%>
 	</h2>
 	<body>
 	    <form method=post action="UserLoginServlet">
 	    		User name : <input type="text" name="username"> <br>
				Password : <input type="password" name="password"><br>
				<input type="submit">
 	    </form>
 	</body>
 
    </html>