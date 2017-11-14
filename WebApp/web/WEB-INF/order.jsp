<%@ include file="/component/header.html"%>
<%@ include file="/component/nav.html"%>
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
                <li class="order-item active">
                    <div class="order-number">1</div>
                    <div class="order-number-desc">Select Destination</div>
                </li>
                <li class="order-item">
                    <div class="order-number">2</div>
                    <div class="order-number-desc">Select a Driver</div>
                </li>
                <li class="order-item">
                    <div class="order-number">3</div>
                    <div class="order-number-desc"><span>Complete your order</div>
                </li>
            </ul>
        </div>
        <div class="order-form">
            <form action="/find_driver" onsubmit="return validateForm(this)" method="get">
                <table>
                    <tr>
                        <td>Picking Point</td>
                        <td><input type="text" name="pickUp" class="form-input" placeholder="Required"></td>
                    </tr>
                    <tr>
                        <td>Destination</td>
                        <td><input type="text" name="destination" class="form-input" placeholder="Required"></td>
                    </tr>
                    <tr>
                        <td>Preferred Driver</td>
                        <td><input type="text" name="preferredDriver" class="form-input" placeholder="Optional" optional></td>
                    </tr>
                </table>
                <div class="txt-center">
                    <button type="submit" class="form-input submit-button">OK</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/component/footer.html"%>
<script>
  window.onload = toggleActive("nav-order");
</script>
