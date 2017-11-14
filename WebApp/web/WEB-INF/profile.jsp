<%--
  Created by IntelliJ IDEA.
  User: ireneedriadr
  Date: 11/3/17
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/component/header.html"%>
<%@ include file="/component/nav.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="wrapper row">
        <div>
            <div class="page-title row txt-center height-fit float-container">
                <div class="float-left">
                    <h1 class="inline-block">MY PROFILE</h1>
                </div>
                <div class="float-right txt-right height-100">
                    <a href="edit_profile">
                        <img src='img/edit-orange.svg' class='edit-icon inline-block'>
                    </a>
                </div>
            </div>
            <div id="profile-body" class="row txt-center">
                <c:choose>
                <c:when test="${userProfile.getImage() != null}">
                <img src='${userProfile.getImage()}' class='profile-image' height='80' alt='profile-image'>
                </c:when>
                <c:otherwise>
                    <img src='img/default-profile.png' class='profile-image' height='80' alt='profile-image'>
                </c:otherwise>
                </c:choose>
                <br>
                <h3>@${userProfile.getUsername()}</h3>

                ${userProfile.getName()}
                <br>
                <span class="profile-rating">
                <c:if test="${userProfile.getIsDriver()}">
                    Driver | <img src='img/gold-star.svg' height='15'>
                    <span class="color-orange">
                        <fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${driverProfile.getRating()}" />
                    </span>
                    &nbsp; (${driverProfile.getVote()} votes)
                </c:if>
                <c:if test="${!userProfile.getIsDriver()}">
                    Non-Driver
                </c:if>
            </span>
                <br>
                <span> <img src="img/mail.svg" alt=" " >${userProfile.getEmail()}</span>
                <br>
                <span> <img src="img/phone.svg" alt=" ">${userProfile.getPhoneNumber()}</span>
            </div>

            <c:if test="${userProfile.getIsDriver()}">
            <div id='preferred-loc' class='row txt-center'>
                <div class='page-title row txt-center float-container'>
                    <h2 class='inline-block float-left'>PREFERRED LOCATIONS:</h2>
                    <span class='float-right'>
                    <a href="preferred_location">
                    <img src='img/edit-orange.svg' class='edit-icon inline-block'>
                        </a>
                    </span>
                </div>
            </div>
            <ul class='preferred-list'>
                <% int tab = 0; %>
               <c:forEach items="${preferredLocations}" var="item">
                   <li style="margin-left: <%= tab%>px">${item.getLocation()}
                       <% tab = tab + 20;%>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="/component/footer.html"%>
<script>
  window.onload = toggleActive("nav-profile");
</script>