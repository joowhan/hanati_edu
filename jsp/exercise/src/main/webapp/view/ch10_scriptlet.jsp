<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	

	<%!
		int i=0;
		String str = "ABCDEF";
	%>
	<%
		//error
		/* out.println(asd);*/
	
		out.println(i + " "+str);
		for(int i=1;i<10;i++){
			for(int j= 1;j<10;j++){
				out.println(i + " X "+j + " = "+(i*j)+"<br/>");
			}
			out.println("<br/>");
		}
	%>
	
	<%--<%@ include file="include.jsp" %> --%>
	<%int asd = 12; %>

</body>
</html>