<%--
  Created by IntelliJ IDEA.
  User: kennethhalim
  Date: 11/5/17
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/component/header.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="wrapper row">
        <div>
            <div class="page-title row txt-center height-fit float-container">
                <div class="float-left">
                    <h1 class="inline-block">EDIT PREFERENCES</h1>
                </div>
            </div>
        </div>
        <div class="row">
            <table class="edit-table txt-center preferences-edit">
                <thead>
                <th>No.</th>
                <th>Location</th>
                <th>Actions</th>
                </thead>
                <tbody>
                <% int i = 1; %>
                <c:forEach items="${preferredLocations}" var="item">

                <tr>
                    <td><%= i %></td>
                    <td>
                        <form action="/edit_preferred_location" method="post" id="form-update-${item.getPosition()}">
                            <input type="hidden" value="${item.getPosition()}" name="position">
                            <input value="${item.getLocation()}"  class="form-input" id="edit-input-${item.getPosition()}" disabled name="location">
                        </form>
                    </td>
                    <td>
                            <span><button class="edit-button inline-block" type="button" onclick=toggleEdit(${item.getPosition()})
                                                id="edit-button-${item.getPosition()}"></button></span>
                        <form action="/delete_location" class="display-inline-block" onsubmit="return confirm('Are You Sure')" method="post">
                            <input type="hidden" value="${item.getPosition()}" name="position">
                            <span><button class="delete-button inline-block" type="submit"></button></span>
                        </form>
                    </td>
                </tr>
                <% i++; %>
                </c:forEach>
                </tbody>
            </table>
        </div>
<div class="row">
            <h2>ADD NEW LOCATION</h2>
            <div>
                <form action="add_location" class="location-input" method="POST" onsubmit="return validateForm(this)">
                <input type="text" class="form-input" name="location">
                <button type="submit" class="submit-button"> Submit </button>
                </form>
            </div>
        </div>
        <a href="/profile">
        <button id="back-button" type="button" class="submit-button">Back</button>
        </a>
    </div>
</div>

<%@include file="/component/footer.html"%>