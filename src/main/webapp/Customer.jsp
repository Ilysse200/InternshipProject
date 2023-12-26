<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 22/12/2023
  Time: 11:43 AM
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
                <div class="card-header text-center fs-3">Customer Register</div>
                <div class="card-body">
                    <form method="POST" action="registerCustomer">
                        <div class="mb-3">
                            <label for="firstname" class="form-label">Name</label>
                            <input type="text" name="customerName" class="form-control" id="firstname", required>
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Mobile</label>
                            <input type="tel" class="form-control" name="customerTel" id="phoneNumber", required>
                        </div>

                        <div class="mb-3">
                            <label for="mail" class="form-label">Email</label>
                            <input type="email" class="form-control" name="customerMail" id="mail", required>
                        </div>

                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" name="customerAddress" id="address", required>
                        </div>
                        <div class="mb-3">
                            <label for="tin" class="form-label">Tin</label>
                            <input type="text" class="form-control" name="customerTin" id="tin", PLACEHOLDER="Enter the tin or the phone number", required>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1", required>
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>