<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 20/12/2023
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">Employee Register</div>
                <div class="card-body">
                    <form method="POST" action="registerEmployee">
                        <div class="mb-3">
                            <label for="firstname" class="form-label">First Name</label>
                            <input type="text" name="empFirstName" class="form-control" id="firstname">
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" name="empLastName" id="lastName">
                        </div>

                        <div class="mb-3">
                            <label for="UserName" class="form-label">User Name</label>
                            <input type="text" class="form-control" name="empUserName" id="UserName">
                        </div>

                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <input type="email" class="form-control" name="empMail" id="exampleInputEmail1">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <input type="password" class="form-control" name="empPassword" id="exampleInputPassword1">
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Date of birth </label>
                            <input type="date" class="form-control" name="empDOB" id="dob" >
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>
                        <a href="login.jsp">Have an account? Sign In</a>
                    </form>

                </div>
                </div>
                </div>
                </div>
            </div>
</div>
</body>
</html>




