<%@ page import="com.example.rraretailbusiness.dao.EmployeeDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rraretailbusiness.dao.ItemDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Item" %>
<%@ page import="com.example.rraretailbusiness.dao.SupplierDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Supplier" %><%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 27/12/2023
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Purchases</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <style>
        .container{
            /*display: flex;*/
            justify-content: space-between;
        }
        .size{
           width: 100%;
            margin: 40px auto;
            background: rgb(124, 154, 167);
        }
        .list{
            text-align: center;
            width: 100%;

        }
        td{
            border: 1px solid rgb(204, 200, 200);
            text-align: left;
            padding: 8px 15px;

        }
        tr:nth-child(even), .list thread> tr{
            background-color: rgb(204, 200, 200) ;

        }
        /*table{*/
        /*    width: 100%;*/
        /*}*/
        input[type="text"], input[type="number"]{
            /*width: 91%;*/
            padding: 7px 20px;
            margin: 4px 0;
            display: inline-block;
            border: 1px solid rgb(165, 161, 161);
            border-radius: 4px;
        }
        .change{
            background: #eee;
            padding: 6px 12px;
            margin: 15px 0 10px;
            display: inline-block;
            border: none;
            font-size: 1rem;
            cursor: pointer;
            outline: none;
        }
        button:hover{
            background: #4b99d8;
        }
        .form-select {
            /*width: 80%;*/
            padding: 7px 10px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid rgb(165, 161, 161);
            border-radius: 4px;
        }
    </style>
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">Purchases Record</div>
                <div class="card-body">
                    <form method="POST" action="registerPurchase">

                        <div class="mb-3">
                            <label for="dateOfPurchase" class="form-label">Purchase Date</label>
                            <input type="date" name="purchaseDate" class="form-control" id="dateOfPurchase" required>
                        </div>
                        <div class="mb-3">
                            <label for="suppliers" class="form-label">Suppliers</label>
                            <input type="number" name="supplierId" class="form-control" id="suppliers" required>
                        </div>
                        <table class="size">
                            <tr>
                                <td>
                                    <div class="mb-3">
                                        <label for="itemNames" class="form-label">Items</label>
                                        <select class="form-select" name="itemList" id="itemNames" required>
                                            <%
                                                // Fetch items from the database using the DAO
                                                ItemDao itemDao = new ItemDao();
                                                List<Item> items = itemDao.displayAllEmployees();

                                                // Iterate over the items and generate <option> elements
                                                for (Item item : items) {
                                            %>
                                            <option value="<%= item.getItemCode() %>"><%= item.getItemName() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                        <label for="itemQuantity" class="form-label">Item Quantity</label>
                                        <select class="form-select" name="itemList" id="itemQuantity" required>
                                            <%
                                                // Fetch items from the database using the DAO
                                                ItemDao itemDao1 = new ItemDao();
                                                List<Item> itemList = itemDao.displayAllEmployees();

                                                // Iterate over the items and generate <option> elements
                                                for (Item item : items) {
                                            %>
                                            <option value="<%= item.getItemCode() %>"><%= item.getItemMeasure() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                        <label for="itemsUnitPrices" class="form-label">ItemPrices</label>
                                        <select class="form-select" name="itemList" id="itemsUnitPrices" required>
                                            <%
                                                // Fetch items from the database using the DAO
                                                ItemDao itemDao2 = new ItemDao();
                                                List<Item> itemList1 = itemDao.displayAllEmployees();

                                                // Iterate over the items and generate <option> elements
                                                for (Item item : itemList1) {
                                            %>
                                            <option value="<%= item.getItemCode() %>"><%= item.getItemUnit() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form_action--button">
                                        <!-- Add this input field inside your form -->
                                        <input type="hidden" id="tableDataInput" name="tableData" value="">
                                        <button class="change" id="addItemButton" onclick="onButtonSubmit(event)">Add Item</button>
                                    </div>
                                    <td>
                                    <table class="list" id="storeList">
                                        <thead>
                                        <tr>
                                            <th>Item Name</th>
                                            <th>Item Quantity</th>
                                            <th>Item Price</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>

                               </td>
                            </tr>
                        </table>


                        <div class="mb-3">
                            <label for="employee" class="form-label">Employee</label>
                            <input type="number" name="empPurchase" class="form-control" id="employee" required>
                        </div>
                        <!-- Add this input field inside your form -->
                        <input type="hidden" id="countInput" name="count" value="">
                        <button type="submit" class="btn btn-primary col-md-12">Save</button>
                    </form>

                </div>
            </div>
        </div>
            </div>
        </div>
    </div>

<script>
    var tableData=[]; //Array to store table data
    var selectedRow = null;
    var count = 0;
function onButtonSubmit(event){
    event.preventDefault();
    var formData = readFormData();
    if(selectedRow === null){
        count = document.getElementById("countInput").value;

        insertNewRecord(formData);
        count++;
        console.log(count);
    }
    else{
        updateRecord(formData);

    }
    return false;

}

// Retrieve data
function readFormData(){
    var formData = {};
    formData["itemNames"] = document.getElementById("itemNames").options[document.getElementById("itemNames").selectedIndex].text;
    formData["itemQuantity"] = document.getElementById("itemQuantity").options[document.getElementById("itemQuantity").selectedIndex].text;
    formData["itemsUnitPrices"] = document.getElementById("itemsUnitPrices").options[document.getElementById("itemsUnitPrices").selectedIndex].text;
    return formData;

}

// Insert data
function insertNewRecord(data){
    var table = document.getElementById("storeList").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    var cell1 = newRow.insertCell(0);
        cell1.innerHTML = data.itemNames;
    var cell2 = newRow.insertCell(1);
        cell2.innerHTML = data.itemQuantity;
    var cell3 = newRow.insertCell(2);
        cell3.innerHTML = data.itemsUnitPrices;

    // Store the data in the array
    tableData.push({
        itemName: data.itemNames,
        itemQuantity: data.itemQuantity,
        itemUnitPrice: data.itemsUnitPrices
    });

    // Update the hidden input with the tableData
    updateHiddenInput();

}
// Update existing record
function updateRecord(formData) {
        var table = document.getElementById("storeList").getElementsByTagName('tbody')[0];
        var cells = selectedRow.cells;
        cells[0].innerHTML = formData.itemNames;
        cells[1].innerHTML = formData.itemQuantity;
        cells[2].innerHTML = formData.itemsUnitPrices;
        selectedRow = null; // Clear the selectedRow after update
    }

</script>

</body>
</html>
