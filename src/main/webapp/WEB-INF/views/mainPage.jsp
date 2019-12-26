<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="main"/>
<!DOCTYPE html>
<html lang="${param.lang}">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Main Page</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>

    <style>
        .tab-card-header > .nav-tabs > li {
            margin-right: 2px;
        }
        .tab-card-header > .nav-tabs > li > a {
            border: 0;
            border-bottom: 2px solid transparent;
            margin-right: 0;
            color: #737373;
            padding: 2px 15px;
        }
        .tab-card-header > .nav-tabs > li > a:hover {
            color: #007bff;
        }
    </style>

</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top position-fixed">
    <a class="navbar-brand" href="">Apartment Travel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav" style="margin-top: 10px">
        <form id="searchForm" class="form-example">
            <ul class="navbar-nav list-inline">
                <li class="nav-item">
                    <div class="form-group mb-3" style="padding-left: 50px">
                        <div class='input-group date' id="datetimepicker">
                            <input name="checkIn" id="checkIn" type='text' class="form-control"/>
                            <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="form-group pl-5 mb-3">
                        <div class='input-group date' id="datetimepicker1">
                            <input name="checkOut" id="checkOut" type='text' class="form-control"/>
                            <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="form-group pl-5 mb-3">
                        <div class='input-group country'>
                            <select name="code" id="code" class="form-control">
                                <option value=""><fmt:message key="country.all"/></option>
                                <option value="BY"><fmt:message key="country.by"/></option>
                                <option value="RU"><fmt:message key="country.ru"/></option>
                                <option value="US"><fmt:message key="country.us"/></option>
                            </select>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <div class="form-group pl-5 mb-3">
                        <button id="searchbtn" type="button" class="btn btn-light"><fmt:message key="btn.search"/></button>
                    </div>
                </li>
                <li class="nav-item ">
                    <div class="form-group pl-5 mb-3">
                        <div class="btn-group" role="group" aria-label="...">
                            <security:authorize access="isAuthenticated()">
                                <a id="logout" class="btn btn-default"><fmt:message key="btn.logout"/></a>
                            </security:authorize>
                            <security:authorize access="isAnonymous()">
                                <a href="${pageContext.request.contextPath}/login" class="btn btn-default"><fmt:message key="btn.get.started"/></a>
                            </security:authorize>
                        </div>
                    </div>

                </li>
                <li class="nav-item">
                    <div class="btn-group pl-5 mb-3">
                        <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="label.lang"/>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="?land=en"> <fmt:message key="label.lang.en"/></a>
                            <a class="dropdown-item" href="?lang=ru"> <fmt:message key="label.lang.ru"/></a>
                        </div>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</nav>

<div class="container">
    <div class="apartments" style="padding-top: 80px">
        <div id="allApartments" class="row">

        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

<script>
    $(function () {
        var current_date = new Date();
        $('#datetimepicker').datetimepicker().datetimepicker();
        $('#datetimepicker1').datetimepicker({
            useCurrent: false
        });
        $("#datetimepicker").on("dp.change", function (e) {
            $('#datetimepicker1').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker1").on("dp.change", function (e) {
            $('#datetimepicker').data("DateTimePicker").maxDate(e.date);
        });
    });

    $(document).ready(function () {
        $("#logout").click(function () {
            $.ajax({
                url: "logout",
                dataType: "json",
                type: "GET",
                success: function (data) {
                    window.location.reload(true);
                }
            })
        })
        $("#searchbtn").click(function () {
            var formDate = $("form").serializeArray()
            var checkIn = new Date(formDate[0].value);
            var checkOut = new Date(formDate[1].value);
            var monthIn = checkIn.getMonth() + 1;
            var monthOut = checkOut.getMonth() + 1;
            var data = {
                code:formDate[2].value,
                checkInDate:checkIn.getFullYear()+"-"+monthIn+"-"+checkIn.getDate(),
                checkOutDate:checkOut.getFullYear()+"-"+monthOut+"-"+checkOut.getDate()
            }
            var searchData = jQuery.param(data)

            $.ajax({
                url: "apartment?" + searchData,
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var div = $('#allApartments');
                    div.empty();
                    $.each(data, function (i) {
                        var itemUrl = window.location.href + "item?id=" + data[i].id + "&" + searchData;
                        div.append("<div class=\"col-lg-6 col-md-6 mb-6\">" +
                            "<div style='width: 500px;' class=\"card h-80\">" +
                            "<a href=\"" + itemUrl + "\"><img class=\"card-img-top\" src=\"${pageContext.request.contextPath}/file/" + data[i].images[0].url + "\"></a>" +
                            "<div class=\"card-body\">" +
                            "<h4 class=\"location-title\">" +
                            "<a href=\"#\">" + data[i].address.address + "," + data[i].address.city + "</a>" +
                            "</h4>" +
                            "<h5>" + data[i].tariff.costPerNight + "$</h5>" +
                            "</div>" +
                            "</div>" +
                            "</div>"
                        );
                    })
                }
            })
        })
    });
    $(window).load(function () {
        $.ajax({
            url: "apartment",
            dataType: "json",
            type: "GET",
            success: function (data) {
                var div = $('#allApartments');
                div.empty();
                $.each(data, function (i) {
                    var itemUrl = location.protocol + '//' + location.host + location.pathname + "item?id=" + data[i].id;
                    div.append("<div class=\"col-lg-6 col-md-6 mb-6\">" +
                        "<div style='width: 500px;' class=\"card h-80\">" +
                        "<a href=\"" + itemUrl + "\"><img class=\"card-img-top\" src=\"${pageContext.request.contextPath}/file/" + data[i].images[0].url + "\"></a>" +
                        "<div class=\"card-body\">" +
                        "<h4 class=\"location-title\">" +
                        "<a href=\"#\">" + data[i].address.address + "," + data[i].address.city + "</a>" +
                        "</h4>" +
                        "<h5>" + data[i].tariff.costPerNight + "$</h5>" +
                        "</div>" +
                        "</div>" +
                        "</div>"
                    );
                })
            }
        })
    });
</script>
</body>

</html>
