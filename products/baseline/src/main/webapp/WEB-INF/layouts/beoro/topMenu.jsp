<%@ page language="java" pageEncoding="UTF-8" %>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <div class="pull-right top-search">
                <form action="" >
                    <input type="text" name="q" id="q-main">
                    <button class="btn"><i class="icon-search"></i></button>
                </form>
            </div>
            <div id="fade-menu" class="pull-left">
                <ul class="clearfix" id="mobile-nav">
                    <li>
                        <a href="javascript:void(0)">Home</a>
                        <ul>
                            <li>
                                <a href="${ctx}/home">Dashboard</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0)">Task</a>
                        <ul>
                            <li>
                                <a href="${ctx}/task">Mytask</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0)">Admin</a>
                        <ul>
                            <li>
                                <a href="${ctx}/admin/user">User</a>
                                <a href="${ctx}/admin/person">Demo</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
