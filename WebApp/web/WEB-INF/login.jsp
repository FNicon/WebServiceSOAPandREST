<%@ include file="/component/header.html"%>
<div class="container">
    <div id="login-box" class="form-box">
        <div id="login-heading" class="form-box-heading">
            <div class="form-box-heading-wrapper">
                <hr>
                <h1 class="txt-center">LOGIN</h1>
                <hr>
            </div>
        </div>
        <div class="form-box-body" id="login-box-body">
            <span class="color-red">${errorMessage}</span>
            <form action="login" method="post" onsubmit="return validateForm(this)">
                <div class="form-box-body-wrapper">
                    <table border="0" class="form-box-table">
                        <tr>
                            <td class="table-label">
                                <label for="username" class="txt-right color-darkgreen">Username</label>
                            </td>
                            <td>
                                <input type="text" name="username" id="username" placeholder="Username" class="form-input">
                            </td>
                        </tr>
                        <tr>
                            <td class="table-label">
                                <label for="password" class="txt-right color-darkgreen">Password</label>

                            </td>
                            <td>
                                <input type="password" name="password" id="password" placeholder="Password" class="form-input">

                            </td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                    <div class="form-box-body-footer">
                        <div>
                            <a href="register"><small>Don't have an account?</small></a>
                        </div>
                        <div class="txt-right button-container">
                            <button type="submit" class="submit-button">Go!</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/component/footer.html" %>
