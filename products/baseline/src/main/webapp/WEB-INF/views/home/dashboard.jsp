<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Dashboard</title>

    <!-- aditional stylesheets -->
    <!-- colorbox -->
    <link rel="stylesheet" href="${ctx}/static/js/lib/colorbox/colorbox.css">
    <!--fullcalendar -->
    <link rel="stylesheet" href="${ctx}/static/js/lib/fullcalendar/fullcalendar_beoro.css">

</head>

<body>


<div class="row-fluid">
    <div class="span8">
        <div class="w-box ">
            <div class="w-box-header">
                <h4>Analytics</h4>
                <i class="icsw16-graph icsw16-white pull-right"></i>
            </div>
            <div class="w-box-content cnt_a">
                <div class="slidewrap">
                    <ul class="slider" id="sliderName">
                        <li class="slide">
                            <span class="hide headName">Pageviews</span>

                            <div class="row-fluid">
                                <div class="span12">
                                    <div id="ch_pages" class="chart_a"></div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 offset1">
                                    <div class="row-fluid">
                                        <div class="span4">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 24h<span class="up">+12%</span></p>

                                                <p class="anlt_content">2 131</p>
                                            </div>
                                        </div>
                                        <div class="span4">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 7 days<span class="down">-5%</span></p>

                                                <p class="anlt_content">14 483</p>
                                            </div>
                                        </div>
                                        <div class="span4">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last Month<span class="up">+14%</span></p>

                                                <p class="anlt_content">64 250</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="slide">
                            <span class="hide headName">Users</span>

                            <div class="row-fluid">
                                <div class="span12">
                                    <div id="ch_users" class="chart_a"></div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 offset1">
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 24h<span class="up">+8%</span></p>

                                                <p class="anlt_content">184</p>
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 7 days<span class="up">+20%</span></p>

                                                <p class="anlt_content">1468</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="slide">
                            <span class="hide headName">Sales</span>

                            <div class="row-fluid">
                                <div class="span12">
                                    <div id="ch_sales" class="chart_a"></div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 offset1">
                                    <div class="row-fluid">
                                        <div class="span6">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 24h<span class="up">+20%</span></p>

                                                <p class="anlt_content">$1 843</p>
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="anlt_box">
                                                <p class="anlt_heading">Last 7 days<span class="down">-10%</span></p>

                                                <p class="anlt_content">$11 638</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="span4">
        <div class="w-box w-box-green hideable">
            <div class="w-box-header">
                <h4>Todo list</h4>

                <div class="pull-right">
                    <span class="label"><span class="jQ-todoAll-count"></span> tasks</span>
                </div>
            </div>
            <div class="w-box-content todo-list">
                <div class="add_box input-append">
                    <input class="span10" type="text" placeholder="Add item" id="addTask"/>
                    <button class="btn btn-small" type="button"><i class="icon-plus"></i></button>
                </div>
                <h4>Personal (<span class="todo-nb"></span>)</h4>
                <ul class="connectedSortable">
                    <li class="high-pr"><input type="checkbox" class="todo-check"/> Buy groceries</li>
                    <li class="low-pr completed"><input type="checkbox" checked class="todo-check"/> Do laundry</li>
                    <li class="low-pr"><input type="checkbox" class="todo-check"/> Meeting with Macy</li>
                    <li class="high-pr"><input type="checkbox" class="todo-check"/> Pick up kids</li>
                </ul>
                <h4>Work (<span class="todo-nb"></span>)</h4>
                <ul class="connectedSortable">
                    <li class="medium-pr"><input type="checkbox" class="todo-check"/> Send press releases</li>
                    <li class="low-pr"><input type="checkbox" class="todo-check"/> Buy books</li>
                    <li class="high-pr completed"><input type="checkbox" checked class="todo-check"/> Update main site
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span5">
        <div class="w-box w-box-orange">
            <div class="w-box-header">
                <h4>Chat</h4>

                <div class="pull-right">
                    <div class="toggle-group">
                        <span data-toggle="dropdown" class="dropdown-toggle">Actions <span class="caret"></span></span>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="w-box-content">
                <div class="ch-messages">
                    <div class="ch-message-item clearfix">
                        <img src="img/avatars/avatar_1.png" alt="" class="ch-image img-avatar"/>

                        <div class="ch-content">
                            <p class="ch-name">
                                <strong>Summer Throssell</strong>
                                <span class="ch-time">10:54</span>
                            </p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam.
                        </div>
                    </div>
                    <div class="ch-message-item clearfix">
                        <img src="img/avatars/avatar_2.png" alt="" class="ch-image img-avatar"/>

                        <div class="ch-content">
                            <p class="ch-name">
                                <strong>Declan Pamphlett</strong>
                                <span class="ch-time">10:54</span>
                            </p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam. Praesent pretium dapibus ornare. Morbi pharetra nulla at elit tincidunt eget auctor
                            eros sollicitudin. Vestibulum in velit neque.
                        </div>
                    </div>
                    <div class="ch-message-item clearfix">
                        <img src="img/avatars/avatar_3.png" alt="" class="ch-image img-avatar"/>

                        <div class="ch-content">
                            <p class="ch-name">
                                <strong>Erin Church</strong>
                                <span class="ch-time">10:54</span>
                            </p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam. Praesent pretium dapibus ornare. Morbi pharetra nulla at elit tincidunt eget auctor
                            eros sollicitudin. Vestibulum in velit neque.
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam. Praesent pretium dapibus ornare. Morbi pharetra nulla at elit tincidunt eget auctor
                            eros sollicitudin. Vestibulum in velit neque.
                        </div>
                    </div>
                    <div class="ch-message-item clearfix">
                        <img src="img/avatars/avatar_4.png" alt="" class="ch-image img-avatar"/>

                        <div class="ch-content">
                            <p class="ch-name">
                                <strong>Anthony Pound</strong>
                                <span class="ch-time">10:54</span>
                            </p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam. Praesent pretium dapibus ornare. Morbi pharetra nulla at elit tincidunt eget auctor
                            eros sollicitudin. Vestibulum in velit neque.
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet nisl nec nunc
                            sollicitudin bibendum. Pellentesque orci massa, venenatis nec porttitor eget, euismod id
                            diam. Praesent pretium dapibus ornare. Morbi pharetra nulla at elit tincidunt eget auctor
                            eros sollicitudin. Vestibulum in velit neque.
                        </div>
                    </div>
                </div>
                <div class="ch-message-item clearfix" id="ch-message-temp" style="display:none">
                    <img src="img/avatars/avatar.png" alt="" class="ch-image img-avatar"/>

                    <div class="ch-content">
                        <p class="ch-name">
                            <strong>Jonathan Smith</strong>
                            <span class="ch-time"></span>
                        </p>
                        <span class="ch-text"></span>
                    </div>
                </div>
            </div>
            <div class="w-box-footer">
                <div class="ch-message-add control-group">
                    <div class="input-append">
                        <input type="text" class="span9 ch-message-input" placeholder="Add message">
                        <button type="button" class="btn ch-message-send">Send</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="span7">
        <div class="w-box">
            <div class="w-box-header">
                <h4>Latest images</h4>

                <div class="pull-right">
                    <div class="toggle-group">
                        <span data-toggle="dropdown" class="dropdown-toggle">Actions <span class="caret"></span></span>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="w-box-content cnt_a">
                <ol id="small-filters" class="nav-beoro">
                    <li data-filter="single_item" class="active">All</li>
                    <li data-filter="this_week">This week</li>
                    <li data-filter="last_week">Last week</li>
                </ol>
                <div id="small_grid" class="wmk_grid">
                    <ul>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image01.jpg"><img src="${ctx}/static/gallery/Image01_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image02.jpg"><img src="${ctx}/static/gallery/Image02_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image03.jpg"><img src="${ctx}/static/gallery/Image03_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item last_week">
                            <a href="${ctx}/static/gallery/Image04.jpg"><img src="${ctx}/static/gallery/Image04_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item last_week">
                            <a href="${ctx}/static/gallery/Image05.jpg"><img src="${ctx}/static/gallery/Image05_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item last_week">
                            <a href="${ctx}/static/gallery/Image06.jpg"><img src="${ctx}/static/gallery/Image06_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image07.jpg"><img src="${ctx}/static/gallery/Image07_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image08.jpg"><img src="${ctx}/static/gallery/Image08_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image09.jpg"><img src="${ctx}/static/gallery/Image09_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image10.jpg"><img src="${ctx}/static/gallery/Image10_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item last_week">
                            <a href="${ctx}/static/gallery/Image11.jpg"><img src="${ctx}/static/gallery/Image11_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image12.jpg"><img src="${ctx}/static/gallery/Image12_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                        <li class="single_item this_week">
                            <a href="${ctx}/static/gallery/Image13.jpg"><img src="${ctx}/static/gallery/Image13_tn.jpg" alt=""/></a>

                            <p class="imgInfo">Lorem ipsum dolor...</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span4">
        <div class="w-box hideable">
            <div class="w-box-header">
                <h4>Latest comments</h4>
                <i class="icsw16-speech-bubble icsw16-white pull-right"></i>
            </div>
            <div class="w-box-content">
                <table class="table table-striped table-list">
                    <tbody>
                    <tr>
                        <td class="list-image"><a href="javascript:void(0)" title="Comment by Summer Throssell"
                                                  class="ptip_ne"><i class="splashy-comments"></i></a></td>
                        <td>
                            <a href="javascript:void(0)" class="list-text">Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Donec cursus dictum rhoncus...</a>
                            <span class="minor">on October 24 @ 7:23</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-image"><a href="javascript:void(0)" title="Comment by Summer Throssell"
                                                  class="ptip_ne"><i class="splashy-comments"></i></a></td>
                        <td>
                            <a href="javascript:void(0)" class="list-text">Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Donec cursus dictum rhoncus...</a>
                            <span class="minor">on October 24 @ 7:23</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-image"><a href="javascript:void(0)" title="Comment by Summer Throssell"
                                                  class="ptip_ne"><i class="splashy-comments"></i></a></td>
                        <td>
                            <a href="javascript:void(0)" class="list-text">Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Donec cursus dictum rhoncus. Duis quis pretium massa. Integer laoreet
                                erat id neque interdum...</a>
                            <span class="minor">on October 24 @ 7:23</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-image"><a href="javascript:void(0)" title="Comment by Summer Throssell"
                                                  class="ptip_ne"><i class="splashy-comments"></i></a></td>
                        <td>
                            <a href="javascript:void(0)" class="list-text">Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Donec cursus dictum rhoncus...</a>
                            <span class="minor">on October 24 @ 7:23</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="list-image"><a href="javascript:void(0)" title="Comment by Summer Throssell"
                                                  class="ptip_ne"><i class="splashy-comments"></i></a></td>
                        <td>
                            <a href="javascript:void(0)" class="list-text">Lorem ipsum dolor sit amet...</a>
                            <span class="minor">on October 24 @ 7:23</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="span8">
        <div class="w-box w-box-blue">
            <div class="w-box-header">
            </div>
            <div class="w-box-content">
                <div id="calendar_widget"></div>
            </div>
        </div>
    </div>
</div>

<!-- Dashboard JS -->
<!-- jQuery UI -->
<script src="${ctx}/static/js/lib/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>
<!-- touch event support for jQuery UI -->
<script src="${ctx}/static/js/lib/jquery-ui/jquery.ui.touch-punch.min.js"></script>
<!-- colorbox -->
<script src="${ctx}/static/js/lib/colorbox/jquery.colorbox.min.js"></script>
<!-- fullcalendar -->
<script src="${ctx}/static/js/lib/fullcalendar/fullcalendar.min.js"></script>
<!-- flot charts -->
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.js"></script>
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.resize.js"></script>
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.pie.js"></script>
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.orderBars.js"></script>
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.tooltip.js"></script>
<script src="${ctx}/static/js/lib/flot-charts/jquery.flot.time.js"></script>
<!-- responsive carousel -->
<script src="${ctx}/static/js/lib/carousel/plugin.min.js"></script>
<!-- responsive image grid -->
<script src="${ctx}/static/js/lib/wookmark/jquery.imagesloaded.min.js"></script>
<script src="${ctx}/static/js/lib/wookmark/jquery.wookmark.min.js"></script>

<script src="${ctx}/static/js/pages/beoro_dashboard.js"></script>


</body>
</html>

