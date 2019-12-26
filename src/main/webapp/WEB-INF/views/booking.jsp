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

    <title>Apartment Page</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"/>


</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top position-fixed">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">Apartment Travel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav" style="margin-top: 10px">
        <div class="container">
            <div class="col-lg-2 offset-lg-8">
                <form id="searchForm" class="form-example">
                    <ul class="navbar-nav list-inline">
                        <li class="nav-item">
                            <div class="form-group pl-2 mb-3">
                                <security:authorize access="isAuthenticated()">
                                    <a id="logout" class="btn btn-default"><fmt:message key="btn.logout"/></a>
                                </security:authorize>
                                <security:authorize access="isAnonymous()">
                                    <a href="${pageContext.request.contextPath}/login" class="btn btn-default"><fmt:message key="btn.get.started"/></a>
                                </security:authorize>                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="btn-group pl-5 mb-3">
                                <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    <fmt:message key="label.lang"/>
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" onclick="changeLang('en')"> <fmt:message key="label.lang.en"/></a>
                                    <a class="dropdown-item" onclick="changeLang('ru')" > <fmt:message key="label.lang.ru"/></a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</nav>


<div class="container " style="padding-top: 60px">
    <h1 class="my-4" id="name"></h1>
    <div class="row" id="main">
        <div style='width: 900px;' class="col-md-8" id="mainImage">
        </div>
        <div class="col-md-4">
            <div id="desc">
                <h3 class="my-3"><fmt:message key="section.desc"/></h3>
            </div>
            <br>
            <br>
            <br>
            <div class="pl-5 position-fixed">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><fmt:message key="order.summary"/></h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-condensed ">
                                <thead>
                                <tr>
                                    <td class="text-center"><strong><fmt:message key="order.price.per.night"/></strong></td>
                                    <td class="text-center"><strong><fmt:message key="order.night"/></strong></td>
                                    <td class="text-right"><strong><fmt:message key="order.total"/></strong></td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td id="perNight" class="text-center"></td>
                                    <td id="count" class="text-center"></td>
                                    <td id="total" class="text-right"></td>
                                </tr>
                                </tbody>
                            </table>
                            <security:authorize access="isAuthenticated()">
                                <button  type="button" class="btn btn-dark  btn-block" data-toggle="modal" data-target="#bookingModal" ><fmt:message key="btn.book"/></button>
                            </security:authorize>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <h3 class="my-4"><fmt:message key="section.pics"/></h3>

    <div class="row">

        <div id="carousel-demo" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner" id="carousel-image">

            </div>
            <a class="left carousel-control " style="opacity: 0" href="#carousel-demo" data-slide="prev">
            </a>
            <a class="right carousel-control" style="opacity: 0" href="#carousel-demo" data-slide="next">
            </a>
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- Modal -->
<div class="modal fade" id="bookingModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLongTitle">Booking</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="order_info" class="modal-body">
            </div>
            <div id="modal_footer" class="modal-footer">
                <button id="bookbtn" type="button" class="btn btn-dark">Book</button>
                <button id="ok" style="display: none" type="button" class="btn btn-dark">Ok</button>
            </div>
        </div>
    </div>
</div>
<!-- /.container -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script>
    function changeLang(lan){
        var url = new URL(window.location.href);
        var lang = url.searchParams.get("lang");

        if (lang===null) {
            window.location.href += "&lang="+lan;
        } else {
            url.searchParams.set("lang", lan);
            window.location.href = url.toString();
        }
    }
    var totalPrice;
    $(document).ready(function () {
        $("#logout").click(function () {
            $.ajax({
                url: "logout",
                dataType: "json",
                type: "GET",
                success: function () {
                    location.reload();
                }
            })
        })
        $("#bookbtn").click(function (e) {
            var url = new URL(window.location.href);
            var id = url.searchParams.get("id");
            var checkIn = new Date(url.searchParams.get("checkInDate"));
            var checkOut = new Date(url.searchParams.get("checkOutDate"));
            var currentDate = new Date();
            var data = {
                creationDate:currentDate.toISOString(),
                checkInDate:checkIn.toISOString(),
                checkOutDate:checkOut.toISOString(),
                price:totalPrice,
                apartmentId:id
            }

            var payload = JSON.stringify(data);
            $.ajax({
                url: "order",
                type: "POST",
                data: payload,
                datatype: "json",
                contentType: "application/json; charset=utf-8",
                success: function (json) {
                    var order_info = $("#order_info")
                    order_info.append("<h5 style='color: #007000'>Apartment successfully booked!</h5>")//todo admin contact
                    $("#bookbtn").css("display", "none");
                    $("#ok").css("display", "block");
                }

            });
        });
        $("#ok").click(function (e) {
            var url = window.location.origin + "/yana_internship_app_war/";
            window.location.href = url
        })
    })

    $(window).load(function () {
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        if (url.searchParams.has("checkInDate")) {
            var checkIn = url.searchParams.get("checkInDate");
            var checkOut = url.searchParams.get("checkOutDate");
            var params = "id=" + id + "&checkInDate=" + checkIn + "&checkOutDate=" + checkOut;
        }

        $.ajax({
            url: "apartment/" + id,
            dataType: "json",
            type: "GET",
            success: function (data) {
                var h2 = $('#name');
                h2.empty();
                h2.append(data.name);
                var mainImage = $('#mainImage');
                mainImage.empty();
                mainImage.append(
                    "<img class='img-fluid'  src='${pageContext.request.contextPath}/file/" + data.images[0].url + "' >"
                );
                var desc = $('#desc');
                desc.append(
                    "<h4>"+data.address.address + "," + data.address.city+"</h4>\n"+
                    "<p>" + data.description + "</p>"
                );
                var slider = $('#carousel-image');
                slider.empty();
                $.each(data.images, function (i) {
                    if (i !== 0) {
                        var isActive = i === 1 ? "active" : "";
                        slider.append(
                            "<div class=\"item " + isActive + "\">" +
                            "<img src=\"${pageContext.request.contextPath}/file/" + data.images[i].url + "\">" +
                            "</div>")
                    }
                })
                var perNight = $('#perNight');
                perNight.append(
                    data.tariff.costPerNight
                );
                var cout = $('#count');
                cout.append(

                );
                if (url.searchParams.has("checkInDate")) {
                    $.ajax({
                        url: "order/price?" + params,
                        dataType: "json",
                        type: "GET",
                        success: function (data) {
                            totalPrice=data;
                            var total = $('#total');
                            total.append(
                                data + "$"
                            );
                            var order_info = $("#order_info")
                            order_info.append("<h5>Book apartment from "+checkIn+" to "+ checkOut+". Total price is "+totalPrice+"$</h5>")

                        }
                    })
                }

            }
        })

    });
</script>
</body>

</html>
