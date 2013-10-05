<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.whisman.com/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <title>User Profile</title>
</head>
<body>
<div class="row-fluid">
    <div class="span6">
        <div class="w-box">
            <div class="w-box-header">
                <h4>User profile</h4>
            </div>
            <div class="w-box-content cnt_a user_profile">
                <div class="row-fluid">
                    <div class="span2">
                        <div class="img-holder">
                            <img class="img-avatar" alt="" src="${ctx}/static/img/avatars/avatar.png">
                        </div>
                    </div>
                    <div class="span10">
                        <p class="formSep">
                            <small class="muted">Verified:</small>
                            <span class="label label-success">Yes</span></p>
                        <p class="formSep">
                            <small class="muted">Login Name:</small>
                            ${user.loginName}</p>
                        <p class="formSep">
                            <small class="muted">Name:</small>
                            ${user.person.firstName} ${user.person.lastName}</p>
                        <p class="formSep">
                            <small class="muted">Gender:</small>
                            Male
                        </p>
                        <p class="formSep">
                            <small class="muted">Birthday:</small>
                            24/06/1974
                        </p>
                        <p class="formSep">
                            <small class="muted">Email:</small>
                            ${user.person.email}</p>
                        <p class="formSep">
                            <small class="muted">Subscription:</small>
                            Newsletter, System messages
                        </p>
                        <p class="formSep">
                            <small class="muted">Languages:</small>
                            English, French
                        </p>
                        <p class="formSep">
                            <small class="muted">Signature:</small>
                            ${user.person.signature} </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="span6">
        <div class="w-box">
            <div class="w-box-header">
                <h4>User settings</h4>
            </div>
            <div class="w-box-content">
                <form:form id="inputForm" action="${ctx}/profile" method="post" modelAttribute="user">

                    <input type="hidden" name="id" value="${user.id}"/>

                    <div class="formSep">
                        <label>Avatar</label>

                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-new thumbnail" style="width: 60px; height: 60px;"><img
                                    src="${ctx}/static/img/dummy_60x60.gif" alt=""></div>
                            <div class="fileupload-preview fileupload-exists thumbnail"
                                 style="width: 60px; height: 60px;"></div>
                            <span class="btn btn-small btn-file"><span class="fileupload-new">Select image</span><span
                                    class="fileupload-exists">Change</span><input type="file"></span>
                            <a href="#" class="btn btn-small btn-link fileupload-exists" data-dismiss="fileupload">Remove</a>
                        </div>
                    </div>
                    <div class="formSep">
                        <div class="row-fluid">
                            <div class="span4">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="person.firstName" class="span12"
                                       value="${user.person.firstName}"/>
                            </div>
                            <div class="span4">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="person.lastName" class="span12"
                                       value="${user.person.lastName}"/>
                            </div>
                        </div>
                    </div>
                    <div class="formSep">
                        <label for="plainPassword">Password</label>
                        <input type="password" id="plainPassword" name="plainPassword" class="span8"
                               placeholder="...Leave it blank if no change"/>
                        <span class="help-block">Enter Password</span>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="span8"
                               placeholder="...Leave it blank if no change" equalTo="#plainPassword"/>
                        <span class="help-block">Repeat Password</span>
                    </div>
                    <div class="formSep">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="person.email" class="span8" value="${user.person.email}"/>
                    </div>
                    <div class="formSep">
                        <label>Gender</label>
                        <form:bsradiobuttons path="person.gender" items="${genders}" labelCssClass="inline"/>
                    </div>
                    <div class="formSep">
                        <label>I want to receive</label>
                        <label for="u_newsletter" class="checkbox inline"><input type="checkbox" name="u_newsletter"
                                                                                 id="u_newsletter"/> Newsletter</label>
                        <label for="u_system_msg" class="checkbox inline"><input type="checkbox" name="u_system_msg"
                                                                                 id="u_system_msg"/> System
                            Messages</label>
                        <label for="u_other_msg" class="checkbox inline"><input type="checkbox" name="u_other_msg"
                                                                                id="u_other_msg"/> Other
                            Messages</label>
                    </div>
                    <div class="formSep">
                        <label for="signature">Signature</label>
                        <textarea name="person.signature" id="signature" cols="30" rows="4"
                                  class="span8">${user.person.signature}</textarea>
                    </div>
                    <div class="formSep sepH_b">
                        <button class="btn btn-beoro-3" type="submit">Save changes</button>
                        <a href="#" class="btn">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


<!-- file upload widget -->
<script src="${ctx}/static/js/form/bootstrap-fileupload.min.js"></script>

</body>
</html>