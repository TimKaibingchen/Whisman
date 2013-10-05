<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link rel="icon" type="image/ico" href="${ctx}/static/favicon.ico">
    <title>Login</title>
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet'>
    <!-- jQuery framework -->
        <script src="${ctx}/static/js/jquery.min.js"></script>
    <!-- validation -->
        <script src="${ctx}/static/js/lib/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript">
        (function (a) {
            a.fn.vAlign = function () {
                return this.each(function () {
                    var b = a(this).height(), c = a(this).outerHeight(), b = (b + (c - b)) / 2;
                    a(this).css("margin-top", "-" + b + "px");
                    a(this).css("top", "50%");
                    a(this).css("position", "absolute")
                })
            }
        })(jQuery);
        (function (a) {
            a.fn.hAlign = function () {
                return this.each(function () {
                    var b = a(this).width(), c = a(this).outerWidth(), b = (b + (c - b)) / 2;
                    a(this).css("margin-left", "-" + b + "px");
                    a(this).css("left", "50%");
                    a(this).css("position", "absolute")
                })
            }
        })(jQuery);

        $(document).ready(function() {
            if($('#login-wrapper').length) {
                $("#login-wrapper").vAlign().hAlign()
            };
            if($('#login-validate').length) {
                $('#login-validate').validate({
                    onkeyup: false,
                    errorClass: 'error',
                    rules: {
                        username: { required: true },
                        password: { required: true }
                    }
                })
            }
            if($('#forgot-validate').length) {
                $('#forgot-validate').validate({
                    onkeyup: false,
                    errorClass: 'error',
                    rules: {
                        forgot_email: { required: true, email: true }
                    }
                })
            }
            $('#pass_login').click(function() {
                $('.panel:visible').slideUp('200',function() {
                    $('.panel').not($(this)).slideDown('200');
                });
                $(this).children('span').toggle();
            });
        });
    </script>
</head>
<body>
    <div id="login-wrapper" class="clearfix">
        <div class="main-col">
            <img src="${ctx}/static/img/beoro.png" alt="" class="logo_img" />
            <div class="panel">
                <p class="heading_main">Account Login</p>
                <form id="login-validate" action="${ctx}/login" method="post">
                    <label for="username">Login</label>
                    <input type="text" id="username" name="username" value="${username}"/>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" />
                    <label for="rememberMe" class="checkbox">
                        <input type="checkbox" id="rememberMe" name="rememberMe" />
                        Remember me
                    </label>
                    <div class="submit_sect">
                        <button type="submit" class="btn btn-beoro-3">Login</button>
                    </div>
                </form>
            </div>
            <div class="panel" style="display:none">
                <p class="heading_main">Can't sign in?</p>
                <form id="forgot-validate" method="post">
                    <label for="forgot_email">Your email adress</label>
                    <input type="text" id="forgot_email" name="forgot_email" />
                    <div class="submit_sect">
                        <button type="submit" class="btn btn-beoro-3">Request New Password</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="login_links">
            <a href="javascript:void(0)" id="pass_login"><span>Forgot password?</span><span style="display:none">Account login</span></a>
        </div>
    </div>
</body>
</html>