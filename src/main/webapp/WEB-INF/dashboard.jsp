<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Date" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--  mgiht show error-->
<%@ page isErrorPage="true" %>  


<!DOCTYPE html>
<html>

<head>
    <!-- bootstrap -->
    <!-- <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"  crossorigin="anonymous">
    <!-- regularcss -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">

    <!-- bootstrap js -->
    <!-- <script src="/webjars/jquery/jquery.min.js"></script> -->
    <!-- <script src="/webjars/bootstrap/js/bootstrap.min.js"></script> -->

    <!-- regular js -->
    <script type="text/javascript" src="/javascript/script.js"></script>

    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>

    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <a href="/products/add" class="btn btn-primary">New Product</a>
                <a href="/categorys/add" class="btn btn-primary">New Category</a>
                <hr/>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Category</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>
                                <c:forEach var="product" items="${products}">
                                    <ul>- <a href="/products/${product.id}/show">${product.name}</a></ul>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="category" items="${categorys}">
                                    <ul>- <a href="/categorys/${category.id}/show">${category.name}</a></ul>
                                </c:forEach>
                            </td>
                        </tr>
                    </tbody>

                </table>

            </div>
        </div>
        
    </div>
</body>

</html>