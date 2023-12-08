<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 01/12/2023
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Retail-Business Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> Retail-Business Application </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Employees</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Dob</th>
                <th>Password</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="employee" items="${listEmployee}">

                <tr>
                    <td><c:out value="${employee.empID}" /></td>
                    <td><c:out value="${employee.empFirstName}" /></td>
                    <td><c:out value="${employee.empLastName}" /></td>
                    <td><c:out value="${employee.empUserName}" /></td>
                    <td><c:out value="${employee.empPassword}" /></td>
                    <td><c:out value="${employee.empMail}" /></td>
                    <td><c:out value="${employee.empDOB}" /></td>


                    <td><a href="edit?id=<c:out value='${employee.empID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${employee.empID}' />">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>