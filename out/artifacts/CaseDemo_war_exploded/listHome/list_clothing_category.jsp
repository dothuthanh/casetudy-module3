<%--
  Created by IntelliJ IDEA.
  User: BipVuong
  Date: 6/26/2020
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clothing List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="../index.css">
    <link rel="stylesheet" type="text/css" href="../footer.css">
    <style>
        .btn {
            background-color: DodgerBlue;
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }


        .btn:hover {
            background-color: RoyalBlue;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" style="font-size: 30px; font-weight: bold; color: white">THE B<i class='fas fa-cannabis' style='font-size:48px;color:red'></i>UTIQUE</a>

        <ul class="navbar-nav ">
            <li class="nav-item " style="font-size: 25px; font-weight: bold; color: white">
                <a class="nav-link" href="${pageContext.request.contextPath}/clothing"><button type=button class="btn">HOME</button></a>
            </li>
            <li class="nav-item " style="font-size: 25px; font-weight: bold; color: white">
                <a class="nav-link" href="${pageContext.request.contextPath}/clothing?action=listClothing"><button type=button class="btn">CLOTHING</button></a>
            </li>
            <li class="nav-item " style="font-size: 25px; font-weight: bold; color: white">
                <a class="nav-link" href="${pageContext.request.contextPath}/category?action=listCategory"><button type=button class="btn">CATEGORY</button></a>
            </li>
            <li class="nav-item active" style="font-size: 25px; font-weight: bold; color: white">
                <a class="nav-link" href="contact.html"><button type=button class="btn">CART</button>  <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item" style="font-size: 25px; font-weight: bold; color: white">
                <a class="nav-link" href="contact.html"><button type=button class="btn">CONTACT</button> </a>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/clothing" method="get">

            <select name="category_id" style="width: 70px; height: 40px">
                <c:forEach items='${requestScope["categories"]}' var="category">
                    <option value="${category.getId()}">${category.getName()}</option>

                </c:forEach>

            </select>&emsp;

            <button type="submit" name="action" value="findByCategoryName" class="btn">Tìm kiếm</button>

            <a class="btn btn-success btn-sm ml-3" href="cart.html">
                <i class="fa fa-shopping-cart"></i> Cart
                <span class="badge badge-light">3</span>
            </a>
        </form>
    </div>

</nav>
<div class="container-fluid">

    <table class="table table-bordered">
        <thead>
        <tr class="success" style="text-align: center">

            <td>NAME</td>
            <td>DESCRIPTION</td>
            <td>PICTURE</td>
            <td>PRICE</td>
            <td>ORIGIN</td>
            <td>CATEGORY</td>
            <td>STATUS</td>
        </tr>
        </thead>
        <c:forEach items='${requestScope["clothing"]}' var="clothing">
            <tbody>
            <tr>

                <td>${clothing.getName()}</td>
                <td>${clothing.getDescription()}</td>
                <td class="img-thumbnail" style="text-align: center"><img src="${clothing.getPicture()}" alt="img" width="40%" height="auto"></td>
                <td>${clothing.getPrice()}<span>.000đ</span></td>
                <td>${clothing.getOrigin()}</td>
                <td>${clothing.getCategory()}</td>
                <td>${clothing.getStatus()}</td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

<section id="footer">
    <div class="container">
        <div class="row text-center text-xs-center text-sm-left text-md-left">
            <div class="col-xs-12 col-sm-4 col-md-4">
                <h5>Quick links</h5>
                <ul class="list-unstyled quick-links">
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Home</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>About</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>FAQ</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Get Started</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Videos</a></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4">
                <h5>Quick links</h5>
                <ul class="list-unstyled quick-links">
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Home</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>About</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>FAQ</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Get Started</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Videos</a></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-4 col-md-4">
                <h5>Quick links</h5>
                <ul class="list-unstyled quick-links">
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Home</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>About</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>FAQ</a></li>
                    <li><a href=""><i class="fa fa-angle-double-right"></i>Get Started</a></li>
                    <li><a href="https://wwwe.sunlimetech.com" title="Design and developed by"><i class="fa fa-angle-double-right"></i>Imprint</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-5">
                <ul class="list-unstyled list-inline social text-center">
                    <li class="list-inline-item"><a href=""><i class="fa fa-facebook"></i></a></li>
                    <li class="list-inline-item"><a href=""><i class="fa fa-twitter"></i></a></li>
                    <li class="list-inline-item"><a href=""><i class="fa fa-instagram"></i></a></li>
                    <li class="list-inline-item"><a href=""><i class="fa fa-google-plus"></i></a></li>
                    <li class="list-inline-item"><a href="" target="_blank"><i class="fa fa-envelope"></i></a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-2 text-center text-white">
                <p><u><a href="https://www.nationaltransaction.com/">National Transaction Corporation</a></u> is a Registered MSP/ISO of Elavon, Inc. Georgia [a wholly owned subsidiary of U.S. Bancorp, Minneapolis, MN]</p>
                <p class="h6">&copy All right Reversed.<a class="text-green ml-2" href="https://www.sunlimetech.com" target="_blank">Sunlimetech</a></p>
            </div>
        </div>
    </div>
</section>
</body>
</html>



