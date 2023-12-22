<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 21/12/2023
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">Login Employee</div>
                    <div class="card-body">
                        <form method="POST" action="loginEmployee">
                            <div class="mb-3">
                                <label for="username" class="form-label">User Name</label>
                                <input type="text" name="empUserName" class="form-control" id="username">
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" name="empPassword" id="password">
                            </div>
                            </div>
                            <button type="submit" class="btn btn-primary col-md-12">Login</button>
                        </form>
                    </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
