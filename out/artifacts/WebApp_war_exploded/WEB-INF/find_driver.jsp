<%@ include file="/component/header.html"%>
<%@ include file="/component/nav.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="wrapper row">
        <div>
            <div class="page-title row txt-center height-fit float-container">
                <div class="float-left">
                    <h1 class="inline-block">MAKE AN ORDER</h1>
                </div>
            </div>
        </div>
        <div class="order-menu">
            <ul class="order-ul">
            <li class="order-item">
                <div class="order-number">1</div>
                <div class="order-number-desc">Select Destination</div>
            </li>
            <li class="order-item active">
                <div class="order-number">2</div>
                <div class="order-number-desc">Select a Driver</div>
            </li>
            <li class="order-item">
                <div class="order-number">3</div>
                <div class="order-number-desc"><span>Complete your order</div>
            </li>
            </ul>
        </div>
        <form action="/complete_order" method="get">
            <input type="hidden" name="pickUp" value="${pickup}">
            <input type="hidden" name="destination" value="${destination}">
            <div class="order-panel">
                <div class="panel-heading">
                    <h3>PREFERRED DRIVERS:</h3>
                </div>
                <div class="panel-body txt-center">
                    <c:forEach items="${preferredDrivers}" var="item">
                    <div class="driver-list-item">
                        <div class="first">
                            <c:choose>
                                <c:when test="${item.getImage() != null}">
                                    <img src='${item.getImage()}' class='driver-profile' alt='profile-image'>
                                </c:when>
                                <c:otherwise>
                                    <img src='img/default-profile.png' class='driver-profile' alt='profile-image'>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="second">
                            <span class="name-text">${item.getName()}</span>
                        </div>
                        <div>
                                        <span class="rating-value">
                                            <img src="img/gold-star.svg" alt="">
                                            <fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${item.getRating()}" />
                                        </span>
                            <span>
                                            (${item.getVote()} votes)
                                        </span>
                        </div>
                        <div>
                            <button name="driverId" value="${item.getUsername()}" class="submit-button">
                                I Choose You
                            </button>
                        </div>
                    </div>
                    </c:forEach>
                    <c:if test="${preferredDriversLength == 0}">
                    <span class="color-grey">NOTHING TO DISPLAY :(</span>
                    </c:if>
                </div>
            </div>
            <div class="order-panel">
                <div class="panel-heading">
                    <h3>OTHER DRIVER:</h3>
                </div>
                <div class="panel-body txt-center">
                    <c:forEach items="${foundDrivers}" var="item">
                        <div class="driver-list-item">
                            <div class="first">
                                <c:choose>
                                    <c:when test="${item.getImage() != null}">
                                        <img src='${item.getImage()}' class='driver-profile' alt='profile-image'>
                                    </c:when>
                                    <c:otherwise>
                                        <img src='img/default-profile.png' class='driver-profile' alt='profile-image'>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="second">
                                <span class="name-text">${item.getName()}</span>
                            </div>
                            <div>
                                        <span class="rating-value">
                                            <img src="img/gold-star.svg" alt="">
                                            <fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${item.getRating()}" />
                                        </span>
                                <span>
                                            (${item.getVote()} votes)
                                        </span>
                            </div>
                            <div>
                                <button name="driverId" value="${item.getUsername()}" class="submit-button">
                                    I Choose You
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${foundDriversLength == 0}">
                        <span class="color-grey">NOTHING TO DISPLAY :(</span>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="/component/footer.html"%>
<script>
  window.onload = toggleActive("nav-order");
</script>