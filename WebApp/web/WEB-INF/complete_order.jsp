
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
                <li class="order-item">
                    <div class="order-number">2</div>
                    <div class="order-number-desc">Select a Driver</div>
                </li>
                <li class="order-item active">
                    <div class="order-number">3</div>
                    <div class="order-number-desc"><span>Complete your order</div>
                </li>
            </ul>
        </div>
        <div class="order-panel no-border">
            <div class="panel-heading">
                <h3>HOW WAS IT?</h3>
            </div>
            <div class="panel-body txt-center">
                <c:choose>
                    <c:when test="${driverProfile.getImage() != null}">
                        <img src='${driverProfile.getImage()}' class='profile-image' height='80' alt='profile-image'>
                    </c:when>
                    <c:otherwise>
                        <img src='img/default-profile.png' class='profile-image' height='80' alt='profile-image'>
                    </c:otherwise>
                </c:choose>
                <h3>@${driverProfile.getUsername()}</h3>
                <h3>${driverProfile.getName()}</h3>
                <form method="post" onsubmit="return validateForm(this)" action="complete_order">
                    <input type="hidden" value="${pickup}" name="pickUp">
                    <input type="hidden" value="${destination}" name="destination">
                    <input type="hidden" value="${driverProfile.getId()}" name="driverId">


                    <div id="rating-system">
                        <input type="number" id="rating-value" min="1" max="5" hidden name="rating">
                        <c:forEach var="iter" begin="1" end="5" >
                            <span class='grey-star' onclick='renderStar(${iter})'>&nbsp;</span>
                        </c:forEach>
                    </div>
                    <textarea name="comment" placeholder="Comment" class="form-input" id="comment-area" rows="5"></textarea>
                    <div class="txt-right">
                        <button type="submit" class="submit-button height-auto"> Complete Order</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="/component/footer.html"%>
        <script>
          window.onload = toggleActive("nav-order");
        </script>