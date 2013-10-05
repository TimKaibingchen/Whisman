<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- header -->
<header>
    <div class="container">
        <div class="row">
            <div class="span3">
                <div class="main-logo"><a href="${ctx}/home"><img src="${ctx}/static/img/beoro_logo.png" alt="Beoro Admin"></a></div>
            </div>
            <div class="span5">
                <nav class="nav-icons">
                    <ul>
                        <li><a href="javascript:void(0)" class="ptip_s" title="Dashboard"><i class="icsw16-home"></i></a></li>
                        <li class="dropdown">
                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"><i class="icsw16-create-write"></i> <span class="caret"></span></a>
                            <ul role="menu" class="dropdown-menu">
                                <li role="presentation"><a href="#" role="menuitem">Action</a></li>
                                <li role="presentation"><a href="#" role="menuitem">Another action</a></li>
                                <li class="divider" role="presentation"></li>
                                <li role="presentation"><a href="#" role="menuitem">Separated link</a></li>
                            </ul>
                        </li>
                        <li><a href="javascript:void(0)" class="ptip_s" title="Mailbox"><i class="icsw16-mail"></i><span class="badge badge-info">6</span></a></li>
                        <li><a href="javascript:void(0)" class="ptip_s" title="Comments"><i class="icsw16-speech-bubbles"></i><span class="badge badge-important">14</span></a></li>
                        <li class="active"><span class="ptip_s" title="Statistics (active)"><i class="icsw16-graph"></i></span></li>
                        <li><a href="javascript:void(0)" class="ptip_s" title="Settings"><i class="icsw16-cog"></i></a></li>
                    </ul>
                </nav>
            </div>
            <div class="span4">
                <div class="user-box">
                    <div class="user-box-inner">
                        <img src="${ctx}/static/img/avatars/avatar.png" alt="" class="user-avatar img-avatar">
                        <div class="user-info">
                            Welcome, <strong><shiro:principal property="name"/></strong>
                            <ul class="unstyled">
                                <li><a href="${ctx}/profile">Profile</a></li>
                                <li>&middot;</li>
                                <li><a href="/logout">Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
