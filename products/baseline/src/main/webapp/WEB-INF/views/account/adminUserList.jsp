<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>User Management</title>
</head>

<body>
<div class="row-fluid">
    <div class="span12">
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success">
                <button data-dismiss="alert" class="close">×</button>
                    ${message}</div>
        </c:if>

        <div class="w-box w-box-blue">
            <div class="w-box-header">User Management</div>
            <div class="w-box-content cnt_a">

                <c:if test="${not empty message}">
                    <div id="message" class="alert alert-success">
                        <button data-dismiss="alert" class="close">×</button>
                            ${message}</div>
                </c:if>

                <table id="contentTable" class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>Login Name</th>
                        <th>User Name</th>
                        <th>Register Date </th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><a href="${ctx}/admin/user/update/${user.id}">${user.loginName}</a></td>
                            <td>${user.person.firstName}  ${user.person.lastName}</td>
                            <td>
                                <fmt:formatDate value="${user.registerDate}" pattern="MM/dd/yyyy"/>
                            </td>
                            <td><a href="${ctx}/admin/user/delete/${user.id}">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
