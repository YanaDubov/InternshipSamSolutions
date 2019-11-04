<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Test</title>
    <link rel="stylesheet" type="text/css" href="crudStyle.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#post").click(function (e) {
                var data = {
                    id: $("#id").val(),
                    name: $("#name").val()
                };
                var payload = JSON.stringify(data);
                console.log(payload);

                $.ajax({
                    url: "test",
                    type: "POST",
                    data: payload,
                    datatype: "json",
                    processData: false,
                    contentType: "application/json; charset=utf-8",
                    success: function (json) {
                        console.log("post")
                    }
                });
            });

            $("#put").click(function (e) {
                var data = {
                    id: $("#id").val(),
                    name: $("#name").val()
                };
                var payload = JSON.stringify(data);
                console.log(payload);

                $.ajax({
                    url: "test",
                    type: "PUT",
                    data: payload,
                    datatype: "json",
                    processData: false,
                    contentType: "application/json; charset=utf-8",
                    success: function (json) {
                        console.log("put")
                    }
                });
            })
            $("#delete").click(function (e) {

                var value = $("#idtoget").val();
                $.ajax({
                    url: "test/" + value,
                    dataType: "json",
                    type: "DELETE",
                    success: function (data) {
                        console.log("delete")
                    }
                })

            })

            $("#get").click(function (e) {

                var value = $("#idtoget").val();
                $.ajax({
                    url: "test/" + value,
                    dataType: "json",
                    type: "GET",
                    success: function (data) {
                        console.log(data.id + " " + data.name);
                        var table = $('#table');
                        table.find("tbody tr").remove();
                        table.append("<tr><td>" + data.id + "</td><td>" + data.name + "</td></tr>");
                    }
                })
            })
            $("#showAll").click(function () {

                $.ajax({
                    url: "test",
                    dataType: "json",
                    type: "GET",
                    success: function (data) {
                        var table = $('#table');
                        table.find("tbody tr").remove();
                        $.each(data, function (i) {
                            table.append("<tr><td>" + data[i].id + "</td><td>" + data[i].name + "</td></tr>");
                        })
                    }
                })
            })

        })

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-5">
            <h1>CRUD test app</h1>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="idtoget">ID:</label>
                        <input type="text" class="form-control" id="idtoget">
                    </div>
                </div>
                <div class="col-md-6">
                    <br>
                    <div class="btn-group" role="group" style="margin-top: 5px;">
                        <button id="get" type="button" class="btn btn-primary">Get</button>
                        <button id="showAll" type="button" class="btn btn-primary">Get All</button>
                        <button id="delete" type="button" class="btn btn-primary">Delete</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-5">
            <div class="col-md-5">
                <div class="form-group">
                    <label for="id">ID:</label>
                    <input type="text" class="form-control" id="id">
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="test" class="form-control" id="name">
                </div>
            </div>
            <div class="col-md-7">
                <div class="btn-group " role="group" style="margin-top: 98px;">
                    <button id="post" type="button" class="btn btn-primary">Add</button>
                    <button id="put" type="button" class="btn btn-primary">Update</button>
                </div>
            </div>


        </div>
    </div>
    <br>
    <br>
    <div class="container">
        <table class="table" id="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>