<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Task</title>

    <!-- datatables -->
    <link rel="stylesheet" href="${ctx}/static/js/lib/datatables/css/datatables_beoro.css">
    <link rel="stylesheet" href="${ctx}/static/js/lib/datatables/extras/TableTools/media/css/TableTools.css">

</head>

<body>
<div class="row-fluid">
    <div class="span12">
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success">
                <button data-dismiss="alert" class="close">×</button>
                    ${message}</div>
        </c:if>
            <div class="w-box-header">Task</div>
            <div class="w-box-content">
                <table id="dt_basic" class="dataTables_full table table-striped">
                    <thead>
                    <tr>
                        <th><input name="1"></th>
                        <th><input name="2"></th>
                        <th><input name="3"></th>
                    </tr>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>User name</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="w-box-footer">
                <div class="f-left">
                    <a class="btn btn-beoro-3" href="${ctx}/task/create">Add Task</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- datatables -->
<script src="${ctx}/static/js/lib/datatables/js/jquery.dataTables.min.js"></script>
<!-- datatables column reorder -->
<script src="${ctx}/static/js/lib/datatables/extras/ColReorder/media/js/ColReorder.min.js"></script>
<!-- datatables column toggle visibility -->
<script src="${ctx}/static/js/lib/datatables/extras/ColVis/media/js/ColVis.min.js"></script>
<!-- datatable table tools -->
<script src="${ctx}/static/js/lib/datatables/extras/TableTools/media/js/TableTools.min.js"></script>
<script src="${ctx}/static/js/lib/datatables/extras/TableTools/media/js/ZeroClipboard.js"></script>
<!-- datatables bootstrap integration -->
<script src="${ctx}/static/js/lib/datatables/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="${ctx}/static/js/custom/datatable.js"></script>


<script type="text/javascript">

    $(document).ready(function () {
        //* datatables
        beoro_datatables.basic();

        $('.dataTables_filter input').each(function () {
            $(this).attr("placeholder", "Enter search terms here");
        })
    });

    //* datatables
    beoro_datatables = {
        basic: function () {
            if ($('#dt_basic').length) {
                $('#dt_basic').dataTable({
                    "sPaginationType": "bootstrap_full",
                    "bProcessing": true,
                    "bServerSide": true,
                    "bFilter": true,
                    "sAjaxSource": "${ctx}/task/page",
                    "fnServerData": fnDataTablesPipeline,
                    "aoColumns": [
                        { "mData": "title" },
                        { "mData": "description" },
                        { "mData": "user.loginName" }
                    ],
                    "aoColumnDefs": [
                        {
                            "aTargets": [ 0 ],
                            "mData": "id",
                            "mRender": function (data, type, full) {
//                            return data;
                                return genLink(data, full.id);
                            }
                        }
                    ]
                });
            }
        }
    };

    function genLink(title, id) {
        var url = "${ctx}/task/update/" + id;
        var link = $("<a></a>").attr('href', url).text(title);
        return  link.get(0).outerHTML;
    }

</script>

</body>
</html>
