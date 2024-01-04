<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 01/12/2023
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<centre>
    <h1>Error</h1>
    <h2><%=exception.getMessage()%></h2>
    <button><a href=<%="Supplier.jsp"%>%></a>"<%="Back"%>"</button>
</centre>

</body>
</html>
