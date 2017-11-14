<%@ include file="/component/header.html"%>

<div class="container">
    <div id="signup-box" class="form-box">
        <div id="signup-heading" class="form-box-heading">
            <div class="form-box-heading-wrapper">
                <hr>
                <h1 class="txt-center">SIGN UP</h1>
                <hr>
            </div>
        </div>
        <div class="form-box-body" id="signup-box-body">
            <span class="color-red">${errorMessage}</span>
            <% if (request.getParameter("status") != null) {
               out.println("<span class='color-red'>SignUp Gagal</span>");
            }
            %>
            <form action="/register" method="post" onsubmit="return validateForm(this)">
                <div class="form-box-body-wrapper">
                    <table border="0" class="form-box-table">
                        <tr>
                            <td class="table-label">

                                <label for="name" class="txt-right">Your Name</label>
                            <td>
                                <input type="text" name="name" id="name" placeholder="Name" class="form-input">                            </td>
                            </td>
                            <span class="error-text">

                            </span>

                        </tr>
                        <tr>
                            <td class="table-label">
                                <label for="username-form" class="txt-right">Username</label>

                            </td>
                            <td>
                                <input type="text" name="username" id="username-form"  class="form-input width-90" placeholder="Username" onblur="usernameAJAX()">
                                <div id="username-check-status" class="ajax-status">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="table-label">
                                <label for="email-form" class="txt-right">E-mail</label>
                            </td>
                            <td>
                                <input type="text" name="email" class="form-input width-90" placeholder="E-Mail" id="email-form" onblur="emailAJAX()">
                                <div id="email-check-status" class="ajax-status">
                                    <span></span>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="table-label">
                                <label for="password-register" class="txt-right">Password</label>
                            </td>
                            <td>
                                <input type="password" name="password" class="form-input" id="password-register" placeholder="Password">
                            </td>
                        </tr>
                        <tr>
                            <td class="table-label">
                                <label for="confirm-password" class="txt-right">Confirm Password</label>
                            </td>
                            <td>
                                <input type="password" name="confirm-password" id="confirm-password" placeholder="Re-type Password" class="form-input">
                            </td>
                        </tr>
                        <tr>
                            <td class="table-label">
                                <label for="phone-number" class="txt-right">Phone Number</label>
                            </td>
                            <td>
                                <input type="text" name="phoneNumber" id="phone-number" placeholder="Phone Number" class="form-input">
                            </td>
                        </tr>
                    </table>
                    <div class="form-box-body-footer">
                        <div class="row">
                            <input type="checkbox" name="isDriver" id="is-driver" value="1">
                            <label for="is-driver"><small>Also sign me up as a driver!</small></label>
                        </div>
                        <div>
                            <a href="login"><small>Already have an account?</small></a>
                        </div>
                        <div class="txt-right button-container">
                            <button type="submit" class="form-input submit-button" id="register-submit-button">Register</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
