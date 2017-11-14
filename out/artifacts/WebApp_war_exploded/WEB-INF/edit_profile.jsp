<%--
  Created by IntelliJ IDEA.
  User: kennethhalim
  Date: 11/4/17
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/component/header.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="wrapper">
        <div class="page-title">
            <h1>EDIT PROFILE INFORMATION</h1>
        </div>
        <div id="editprofile-body">
            <form action="edit_profile" id="edit-form" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this)">
            <table class="edit-table">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${userProfile.getImage() != null}">
                                <img src='${userProfile.getImage()}' class='square-image' height='80' alt='profile-image'>
                            </c:when>
                            <c:otherwise>
                                <img src='img/default-profile.png' class='square-image' height='80' alt='profile-image'>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <label for="upload-profpic" class="txt-right">Update profile picture</label>
                        <br>
                        <div class="uploader-container">

                        </div>
                        <input type="file" name="image" id="upload-profpic" accept="image/jpg, image/png" placeholder="Image Path" optional>

                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="update-name" class="txt-right">Your Name</label>
                    </td>
                    <td>
                        <input type="text" name="name" id="update-name" placeholder="Edit Name" class="form-input"
                               value="${userProfile.getName()}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="update-phone-number" class="txt-right">Phone</label>
                    </td>
                    <td>
                        <input type="text" name="phoneNumber" id="update-phone-number" placeholder="Edit Phone Number" class="form-input"
                               value="${userProfile.getPhoneNumber()}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="update-driver-status" class="txt-right nowrap">Status Driver</label>
                    </td>
                    <td class="txt-right" id="slider-toggle">
                        <label class="switch">
                            <input type="checkbox" name="isDriver" value="1" id="update-driver-status"
                            <c:if test="${userProfile.getIsDriver()}"> checked </c:if>
                            >
                            <span class="slider round"></span>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/profile">
                        <button id="back-button" type="button" class="submit-button">Back</button>
                        </a>
                    </td>
                    <td class="txt-right">
                        <button type="submit" class="form-input submit-button" id="save-changes-button">Save</button>
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
</div>
</div>
<%@ include file="/component/footer.html"%>
