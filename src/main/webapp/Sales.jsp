<%@ page import="com.example.rraretailbusiness.dao.CustomerDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rraretailbusiness.dao.ItemDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Item" %>
<%@ page import="com.example.rraretailbusiness.dao.EmployeeDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Employee" %><%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 27/12/2023
  Time: 6:45 AM
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
                <div class="card-header text-center fs-3">Sales Record</div>
                <div class="card-body">
                    <form method="POST" action="registerSales">

                        <div class="mb-3">
                            <label for="dateOfPurchase" class="form-label">Sales Date</label>
                            <input type="date" name="salesDate" class="form-control" id="dateOfPurchase" required>
                        </div>
                        <div class="mb-3">
                            <label for="customers" class="form-label">Customers</label>
                            <input type="number" name="customerId" class="form-control" id="customers" required>
                        </div>
                        <table class="size">
                            <tr>
                                <td>
                                    <div>
                                        <label for="itemNames" class="form-label">Item Name</label>
                                        <select class="form-select" name="itemList" id="itemNames" required>
                                            <%
                                                // Fetch items from the database using the DAO
                                                ItemDao itemDao = new ItemDao();
                                                List<Item> itemList1 = itemDao.displayAllEmployees();

                                                // Iterate over the items and generate <option> elements
                                                for (Item item : itemList1) {
                                            %>
                                            <option value="<%= item.getItemCode() %>"><%= item.getItemName()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="quantity" class="form-label">Item Quantity</label>
                                        <input type="number" name="quantity" class="form-control" id="quantity" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="itemPrice" class="form-label">Item Price</label>
                                        <input type="number" name="unitPrice" class="form-control" id="itemPrice" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="totalPrice" class="form-label">Item TotalPrice</label>
                                        <input type="number" name="totalPrice" class="form-control" id="totalPrice" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="itemsLeft" class="form-label">Item Available</label>
                                        <input type="number" name="quantityAvailable" class="form-control" id="itemsLeft" required>
                                    </div>

                                    <div class="form_action--button">
                                        <!-- Add this input field inside your form -->
                                        <input type="hidden" id="tableDataInput" name="tableData" value="">
                                        <input type="hidden" id="itemNameInput" name="itemNameData" value="">

                                        <button class="change" id="addItemButton" onclick="onButtonSubmit(event)">Add Item</button>
                                    </div>
                                <td>
                                    <table class="list" id="storeList">
                                        <thead>
                                        <tr>
                                            <th>Item Name</th>
                                            <th>Item Quantity</th>
                                            <th>Item Price</th>
                                            <th>Item TotalPrice</th>
                                            <th>Item Available</th>
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
                            <input type="number" name="salesExecuter" class="form-control" id="employee" required>
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
    // Store item names in a separate array
    var itemNamesArray = [];
    function onButtonSubmit(event){
        event.preventDefault();
        var formData = readFormData();
        if(selectedRow === null){

            insertNewRecord(formData);
        }
        else{
            updateRecord(formData);

        }
        updateHiddenInput(); // Update hidden fields before submitting
        // document.forms[0].submit(); // Submit the form
        return false;

    }

    // Retrieve data
    function readFormData(){
        var formData = {};
        formData["itemNames"] = document.getElementById("itemNames").options[document.getElementById("itemNames").selectedIndex].text;
        formData["quantity"] = document.getElementById("quantity").value;
        formData["itemPrice"] = document.getElementById("itemPrice").value;
        formData["totalPrice"] = document.getElementById("totalPrice").value;
        formData["itemsLeft"] = document.getElementById("itemsLeft").value;
        return formData;

    }

    // insert data

    function insertNewRecord(data){

        var table = document.getElementById("storeList").getElementsByTagName('tbody')[0];
        var newRow = table.insertRow(table.length);

        // Save item name in a variable
        var itemName = data.itemNames;

        // Remove item name from data object
        delete data.itemNames;

        // Insert the item name into the first cell
        var cell1 = newRow.insertCell(0);
        cell1.innerHTML = itemName;

        // Insert other data into subsequent cells
        var cellIndex = 1;
        for (var key in data) {
            var cell = newRow.insertCell(cellIndex);
            cell.innerHTML = data[key];
            cellIndex++;
        }

        // Add the current itemName to the array
        itemNamesArray.push(itemName);

        // Store other data in the array
        tableData.push(data.quantity, data.itemPrice, data.totalPrice, data.itemsLeft);

        // // Merge both arrays into the main tableData array
        // tableData.push(itemNameArray, otherDataArray);

        // Update the hidden input with the tableData
        updateHiddenInput();
    }

    // Update existing record
    function updateRecord(formData) {
        var table = document.getElementById("storeList").getElementsByTagName('tbody')[0];
        var cells = selectedRow.cells;
        cells[0].innerHTML = formData.itemNames;
        cells[1].innerHTML = formData.quantity;
        cells[2].innerHTML = formData.itemPrice;
        cells[3].innerHTML = formData.totalPrice;
        cells[4].innerHTML = formData.itemsLeft;
        selectedRow = null; // Clear the selectedRow after update
    }

    function updateHiddenInput() {
        // Update the hidden input with both arrays
        document.getElementById("tableDataInput").value = JSON.stringify(tableData);
        document.getElementById("itemNameInput").value = JSON.stringify(itemNamesArray);
    }


</script>
</body>
</html>

