<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 26/12/2023
  Time: 11:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">Items Register</div>
                <div class="card-body">
                    <form method="POST" action="registerItem">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" name="itemName" class="form-control" id="name", required>
                        </div>
                        <div class="mb-3">
                            <label for="origin" class="form-label">Item Origin</label>
                            <input type="tel" class="form-control" name="itemOrigin" id="origin", required>
                        </div>

                        <div class="mb-3">
                            <label for="measure" class="form-label">Measure</label>
                            <select class="form-select" name="itemMeasure" id="measure" required>
                                <option value="box">Box</option>
                                <option value="pack">Pack</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="unit" class="form-label">Item Unit</label>
                            <input type="number" class="form-control" name="itemUnit" id="unit", required>
                        </div>
                        <div class="mb-3">
                            <label for="tax" class="form-label">Tax</label>
                            <input type="text" class="form-control" name="itemTaxType" id="tax", required>
                        </div>
                        <div class="mb-3">
                            <label for="date" class="form-label"> Registered date</label>
                            <input type="date" class="form-control" name="registeredDate" id="date", required>
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
