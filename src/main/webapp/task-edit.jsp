<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href=<c:url value="/bootstrap/dist/css/bootstrap.min.css"/> rel="stylesheet">
    <!-- Menu CSS -->
    <link href=<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"/> rel="stylesheet">
    <!-- animation CSS -->
    <link href=<c:url value="/css/animate.css" />rel="stylesheet">
    <!-- Custom CSS -->
    <link href=<c:url value="/css/style.css" /> rel="stylesheet">
    <!-- color CSS -->
    <link href=<c:url value="/css/colors/blue-dark.css"/> id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <div class="navbar-header">
            <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse"
               data-target=".navbar-collapse">
                <i class="fa fa-bars"></i>
            </a>
            <div class="top-left-part">
                <a class="logo" href="<c:url value="/"/>">
                    <b>
                        <img src="<c:url value="/plugins/images/pixeladmin-logo.png"/>" alt="home" />
                    </b>
                    <span class="hidden-xs">
                            <img src="<c:url value="/plugins/images/pixeladmin-text.png"/>" alt="home" />
                        </span>
                </a>
            </div>
            <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                <li>
                    <form role="search" class="app-search hidden-xs">
                        <input type="text" placeholder="Search..." class="form-control">
                        <a href="">
                            <i class="fa fa-search"></i>
                        </a>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li>
                    <div class="dropdown">
                        <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
                            <img src="<c:url value="/plugins/images/users/varun.jpg"/>" alt="user-img" width="36"
                                 class="img-circle" />
                            <b class="hidden-xs">Cybersoft</b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/user/profile"/>">Thông tin cá nhân</a></li>
                            <li><a href="#">Thống kê công việc</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/logout"/>">Đăng xuất</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <!-- /.navbar-header -->
        <!-- /.navbar-top-links -->
        <!-- /.navbar-static-side -->
    </nav>
    <!-- Left navbar-header -->
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse slimscrollsidebar">
            <ul class="nav" id="side-menu">
                <li style="padding: 10px 0 0;">
                    <a href="<c:url value="/"/>" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                </li>
                <li>
                    <a href="<c:url value="/user"/>" class="waves-effect"><i class="fa fa-user fa-fw"
                                                                     aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                </li>
                <li>
                    <a href="<c:url value="/role"/>" class="waves-effect"><i class="fa fa-modx fa-fw"
                                                                      aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                </li>
                <li>
                    <a href="<c:url value="/jobs"/>" class="waves-effect"><i class="fa fa-table fa-fw"
                                                                     aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                </li>
                <li>
                    <a href="<c:url value="/task"/>" class="waves-effect"><i class="fa fa-table fa-fw"
                                                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                </li>
                <li>
                    <a href="blank.html" class="waves-effect"><i class="fa fa-columns fa-fw"
                                                                 aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                </li>
                <li>
                    <a href="<c:url value="/403"/>" class="waves-effect"><i class="fa fa-info-circle fa-fw"
                                                               aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Chỉnh sửa công việc</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->

            <!-- Khai báo biến role, isSuccess kiểm tra trả về true false để đánh giá quyền chỉnh sửa -->
            <c:set var="role" value="${role}"/>
            <c:set var="isSuccess" value="false"/>

            <c:if test="${role=='ROLE_USER'}">
                <c:set var="isSuccess" value="true"/>
            </c:if>

            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form action="<c:url value="/task/edit?id=${taskModel.getId()}"/>" method="post" class="form-horizontal form-material">

                            <div class="form-group">
                                <label class="col-md-12">ID</label>
                                <div class="col-md-12">
                                    <input name="taskId" type="text" placeholder="ID"
                                           class="form-control form-control-line" value="${taskModel.getId()}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">
                                    <select name="jobId" class="form-control form-control-line" >
                                        <c:forEach var="item" items="${jobList}">
                                            <!-- Nếu role == ROLE_USE(isSuccess = true) và không trùng với id đang select thì thêm class "disabled" -->
                                            <option value="${item.getId()}" ${item.getId()==taskModel.getJobId()?"selected":""}
                                                    ${item.getId()!=taskModel.getJobId()&& isSuccess?"disabled":""} >

                                                    ${item.getName()}

                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">
                                    <input name="taskName" type="text" placeholder="Tên công việc"
                                           class="form-control form-control-line" value="${taskModel.getName()}" ${isSuccess?"readonly":""}>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select name="userId" class="form-control form-control-line">
                                        <c:forEach var="item" items="${userList}">
                                            <option value="${item.getId()}" ${item.getId()==taskModel.getUserId()?"selected":""}
                                                    ${item.getId()!=taskModel.getUserId()&&isSuccess?"disabled":""}>


                                                    ${item.getFullname()}

                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input name="startDate" type="text" placeholder="yyyy/MM/dd"
                                           class="form-control form-control-line" value="${taskModel.getStartDate()}" ${isSuccess?"readonly":""}>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input name="endDate" type="text" placeholder="yyyy/MM/dd"
                                           class="form-control form-control-line" value="${taskModel.getEndDate()}" ${isSuccess?"readonly":""}>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Trạng thái</label>
                                <div class="col-md-12">
                                    <select name="statusId" class="form-control form-control-line">
                                            <c:forEach var="item" items="${statusList}">
                                                <option value="${item.getId()}" ${item.getId()==taskModel.getStatusId()?"selected":""}>${item.getName()}</option>
                                            </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Lưu lại</button>
                                    <a href="<c:url value="/task"/>" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<!-- Menu Plugin JavaScript -->
<script src="<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"/>"></script>
<!--slimscroll JavaScript -->
<script src="<c:url value="/js/jquery.slimscroll.js"/>"></script>
<!--Wave Effects -->
<script src="<c:url value="/js/waves.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<c:url value="/js/custom.min.js"/>"></script>
</body>

</html>