<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE HTML>
<html lang="en-US">
    <head>

        <meta charset="UTF-8">
        <title><sitemesh:title default="Whisman"/></title>
        <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <link rel="icon" type="image/ico" href="${ctx}/static/favicon.ico">
        
    <!-- common stylesheets-->
        <!-- bootstrap framework css -->
            <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css">
            <link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap-responsive.min.css">
        <!-- iconSweet2 icon pack (16x16) -->
            <link rel="stylesheet" href="${ctx}/static/img/icsw2_16/icsw2_16.css">
        <!-- splashy icon pack -->
            <link rel="stylesheet" href="${ctx}/static/img/splashy/splashy.css">
        <!-- flag icons -->
            <link rel="stylesheet" href="${ctx}/static/img/flags/flags.css">
        <!-- power tooltips -->
            <link rel="stylesheet" href="${ctx}/static/js/lib/powertip/jquery.powertip.css">
        <!-- google web fonts -->
            <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Abel">
            <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300">

    <!-- aditional stylesheets -->


        <!-- main stylesheet -->
            <link rel="stylesheet" href="${ctx}/static/css/beoro.css">

        <!--[if lte IE 8]><link rel="stylesheet" href="${ctx}/static/css/ie8.css"><![endif]-->
        <!--[if IE 9]><link rel="stylesheet" href="${ctx}/static/css/ie9.css"><![endif]-->
            
        <!--[if lt IE 9]>
            <script src="${ctx}/static/js/ie/html5shiv.min.js"></script>
            <script src="${ctx}/static/js/ie/respond.min.js"></script>
            <script src="${ctx}/static/js/lib/flot-charts/excanvas.min.js"></script>
        <![endif]-->


            <!-- Common JS -->
            <!-- jQuery framework -->
            <script src="${ctx}/static/js/jquery.min.js"></script>
            <script src="${ctx}/static/js/jquery-migrate.js"></script>
            <!-- bootstrap Framework plugins -->
            <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
            <!-- top menu -->
            <script src="${ctx}/static/js/jquery.fademenu.js"></script>
            <!-- top mobile menu -->
            <script src="${ctx}/static/js/selectnav.min.js"></script>
            <!-- actual width/height of hidden DOM elements -->
            <script src="${ctx}/static/js/jquery.actual.min.js"></script>
            <!-- jquery easing animations -->
            <script src="${ctx}/static/js/jquery.easing.1.3.min.js"></script>
            <!-- power tooltips -->
            <script src="${ctx}/static/js/lib/powertip/jquery.powertip-1.1.0.min.js"></script>
            <!-- date library -->
            <script src="${ctx}/static/js/moment.min.js"></script>
            <!-- common functions -->
            <script src="${ctx}/static/js/beoro_common.js"></script>

        <sitemesh:head/>

    </head>
    <body class="bg_d">
    <!-- main wrapper (without footer) -->    
        <div class="main-wrapper">
        <!-- top bar -->

            <%@ include file="/WEB-INF/layouts/beoro/topMenu.jsp"%>

            <%@ include file="/WEB-INF/layouts/beoro/header.jsp"%>

        <!-- breadcrumbs -->
            <div class="container">
                <ul id="breadcrumbs">
                    <li><a href="javascript:void(0)"><i class="icon-home"></i></a></li>
                    <li><a href="javascript:void(0)">Content</a></li>
                    <li><a href="javascript:void(0)">Article: Lorem ipsum dolor...</a></li>
                    <li><a href="javascript:void(0)">Comments</a></li>
                    <%--<li><span>Lorem ipsum dolor sit amet...</span></li>--%>
                </ul>
            </div>
        <!-- main content -->
            <div class="container">
                <sitemesh:body/>
            </div>

            <div class="footer_space"></div>
        </div>

        <!-- footer -->
        <%@ include file="/WEB-INF/layouts/beoro/footer.jsp"%>



    </body>
</html>