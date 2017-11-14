<%--
  Created by IntelliJ IDEA.
  User: ireneedriadr
  Date: 11/3/17
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/component/header.html"%>
<%@ include file="/component/nav.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="wrapper row">
        <div>
            <div class="page-title row txt-center height-fit float-container">
                <div class="float-left">
                    <h1 class="inline-block">TRANSACTION HISTORY</h1>
                </div>
            </div>
            <div class="row inline-flex history-menu">
                <a href="/history">

                    <div>
                        My Previous Order

                    </div>
                </a>
                <a href="/driver_history">
                    <div class="active">
                        Driver History
                    </div>
                </a>
            </div>
            <div class="row txt-center">
                <div class="history-list-group">
                    <form action="driver_hide_history" method="post">
                    <c:forEach var="item" items="${orderBeans}">
                    <div class="history-list-item">
                        <c:choose>
                            <c:when test="${item.getImage() != null}">
                                <img src='${item.getImage()}' class='driver-profile' alt='profile-image'>
                            </c:when>
                            <c:otherwise>
                                <img src='img/default-profile.png' class='driver-profile' alt='profile-image'>
                            </c:otherwise>
                        </c:choose>
                        <span class="color-grey date">
                              <fmt:formatDate value="${item.getTimestamp()}" var="formattedDate" type="date" pattern="EEEE, MMMM dd YYYY"/>
                            ${formattedDate}
                        </span>
                        <button class="submit-button button-red" type="submit" name="orderId" value="${item.getId()}">
                            HIDE
                        </button>
                        <span>${item.getName()}</span>
                        <span class="location">
                            <div>
                                    ${item.getPickup()}
                                <img src="img/right-arrow.svg" alt=">" height="1" class="arrow-icon">
                                ${item.getDestination()}
                            </div>
                        </span>
                        <span class="rating">
                            gave &nbsp; <span>${item.getRating()}</span> &nbsp; stars for this order
                        </span>

                        <span class="comment">
                            and left comment: <br>
                            <span>${item.getComment()}</span>
                        </span>
                    </div>
                    </c:forEach>
                    </form>
                </div>
            </div>
        </div>
    </div>

<%@include file="/component/footer.html" %>

<script>
  window.onload = toggleActive("nav-history");
</script>
