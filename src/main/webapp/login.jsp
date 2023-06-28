<%@ page import="model.Users" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row mt-5">
        <div class="col-md-5 m-auto mt-5">
            <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
            <div class="p-4 border mt-4">

<%--                action: đường dẫn mà tham số sẽ được gửi về--%>
<%--                method: phương thức gửi tham số cho đường dẫn khai báo ở action--%>

                    <% String contextPath = request.getContextPath();%>
<%--                    <% String msg = (String) request.getAttribute("msg"); %>--%>
<%--                    <%= msg%>--%>
                    <h1>${msg}</h1>
                    <b>${user.getPassword()}</b>

                    <c:out value="${msg}"/>
                    <c:if test="${user.password=='123'}">
<%--                        Nội dung thỏa điều kiện if--%>
                        Mật khẩu của bạn là ${user.getPassword()}
                    </c:if>

<%--                    items: mảng, list cần duyệt--%>
<%--                    var: biến duyệt từng phần tử--%>

                    <c:forEach items="${list}" var="item" begin="0" end="${list.size()}">
                        <p>${item}</p>

                    </c:forEach>

                <form action="<%= contextPath%>/login" method="post">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="username" value="${username}">
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" class="form-control" name="password" value="${password}">
                    </div>
                    <div class="form-group text-left">
                        <input type="checkbox" name="remember" checked>
                        <label>Remember</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
