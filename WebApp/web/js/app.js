var ajaxFlag =false;

function sendRequest(url, method, data, callbackFunction) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if(this.readyState == 4 && this.status == 200) {
        callbackFunction;
      }
    };
    xhttp.open(method, url, true);
    xhttp.send(data);

}

function callBackReload() {
    location.reload();
}

function usernameAJAX() {
	var status = document.getElementById("username-check-status");
	status.innerHTML = "<div class='ajax-validator-loading'></div>";
	var username = document.getElementById('username-form').value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
	       if(validateUsername() && (xhttp.responseText)) {
	       	status.innerHTML = "<div class='ajax-validator-ok'></div>";
	       	ajaxFlag = true;
	       } else {
	       	status.innerHTML = "<div class='ajax-validator-not-ok'></div>";
	       	ajaxFlag = false;
		   }
		} else {
            status.innerHTML = "<div class='ajax-validator-not-ok'></div>";
            ajaxFlag = false;

        }
	};
	xhttp.open("GET", "app/username_check.php?username="+username, true);
	xhttp.send();
}


function emailAJAX() {
	var status = document.getElementById("email-check-status");
	status.innerHTML = "<div class='ajax-validator-loading'></div>"
	var email = document.getElementById('email-form').value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       if(validateEmail() && xhttp.responseText) {
	       	status.innerHTML = "<div class='ajax-validator-ok'></div>";
	       	ajaxFlag = true;
	       } else {
	       	status.innerHTML = "<div class='ajax-validator-not-ok'></div>";
	       	ajaxFlag =  false;
	       }
	    } else {
            status.innerHTML = "<div class='ajax-validator-not-ok'></div>";
            ajaxFlag =  false;
        }
	};
	xhttp.open("GET", "app/email_check.php?email="+email, true);
	xhttp.send();
    if (validateEmail() && (xhttp.responseText)) {
        return true;
    } else {
        return false;
    }
}

function addErrorText(element, errorText) {
	var childs= element.getElementsByClassName('error-text');
    if(!childs.length) {
    	element.innerHTML = element.innerHTML + "<span class=error-text>" + errorText + "</span>";
    } else {
		childs[0].innerHTML= errorText;
	}

}

function deleteErrorText(element) {
    var childs= element.getElementsByClassName('error-text');
    if(childs.length) {
        childs[0].innerHTML="";
    }

}

function validateName() {
	var input = document.getElementsByName('name')[0];
	var str = input.value;
    var parent = input.parentElement;
    input.style.border = "1px solid #CC0000";
    if(!str.length) {
        addErrorText(parent, "Nama harus diisi");
		return false;
	} else if(str.length >20) {
        addErrorText(parent, "Nama tidak boleh lebih dari 20 karakter");
		return false;
    }
    input.style.border = "1px solid #00782d";
    deleteErrorText(parent);

    return true;
}


function validateUsername() {
    var input = document.getElementsByName('username')[0];
    var str = input.value;
    var parent = input.parentElement;
    if(!str.length) {
        input.style.border = "1px solid #CC0000";
        addErrorText(parent, "Username harus diisi")
        return false;
    }
    input.style.border = "1px solid #00782d";
    deleteErrorText(parent);

    return true;
}

function validateEmail() {
    var input = document.getElementsByName('email')[0];
    var str = input.value;
    var parent = input.parentElement;
    input.style.border = "1px solid #CC0000";
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(!str.length ) {
        addErrorText(parent, "Email harus diisi")
        return false;
    } else if(!re.test(str)){
        addErrorText(parent, "Email tidak sesuai format");
		return false;
	}
    input.style.border = "1px solid #00782d";
    deleteErrorText(parent);

    return true;
}

function validatePassword() {
    var input = document.getElementsByName('password')[0];
    var confirm = document.getElementById('confirm-password');
    var str1 = input.value;
    var str2 = confirm.value;
    var parent = input.parentElement;
    var cparent = confirm.parentElement;
    input.style.border = "1px solid #CC0000";
    confirm.style.border = "1px solid #CC0000";
    if(!str1.length) {
    	addErrorText(parent, "Password harus diisi");
    	return false;
	} else if(!str2.length){
        addErrorText(cparent, "Confirm Password harus diisi");
        return false;
	} else if(str1 != str2) {
    	addErrorText(cparent, "Confirm Password harus sama dengan Password");
    	return false;
	}
    input.style.border = "1px solid #00782d";
    confirm.style.border = "1px solid #00782d";
    deleteErrorText(parent);

    return true;
}

function validatePhone() {
    var input = document.getElementsByName('phoneNumber')[0];
    var str = input.value;
    var parent = input.parentElement;
    input.style.border = "1px solid #CC0000";
    if(!str.length) {
        addErrorText(parent, "Phone Number harus diisi")
        return false;
    } else if(str.length < 9 && str.length >12 ) {
    	addErrorText(parent, "Phone Number harus antara 9-12 digit");
    	return false;
	}
    input.style.border = "1px solid #00782d";
    deleteErrorText(parent);
    return true;
}

function validateSignUpForm() {
    var f=true;
	f = !!validateEmail() && f;
    f = !!validateName() && f;
    f = !!validatePassword() && f;
    f = !!validatePhone() && f;
    f = !!validateUsername() && f;
    f = f && ajaxFlag;
	if(f)
		return true;
	else
		return false;
}

function toggleActive(tagId) {
    document.getElementById(tagId).classList.toggle("active");
}

function renderStar(rate) {
    document.getElementById('rating-value').value = rate;
    var parent = document.getElementById('rating-system');
    var childs = parent.getElementsByTagName('span');
    for(var i = 0;i<rate;i++) {
        childs[i].className = 'gold-star';
    }
    for(var j = rate; j<5; j++) {
        childs[j].className = 'grey-star';
    }
}

function toggleEdit(id) {
    button = document.getElementById("edit-button-"+id);
    input = document.getElementById("edit-input-"+id);
    newbutton = document.createElement("button");
    newbutton.setAttribute("type","submit");
    newbutton.setAttribute("form","form-update-"+id)
    newbutton.classList.add("save-button");
    //newbutton.setAttribute("onclick", "savePreferences("+id+")");
    input.removeAttribute("disabled");
    button.parentElement.appendChild(newbutton);
    button.parentElement.removeChild(button);
}

function savePreferences(id) {
  if(confirm("Are You Sure?")) {
    input = document.getElementById("edit-input-"+id);
    var data = {};
    data.position = id;
    data.location  = input.value;
    var payload = "position="+id+"&"+"location="+input.value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if(this.status == 200 && this.readyState == 4) {
        console.log(this.responseText)
        //location.reload();
      }
    }
    xhr.open("PUT","/preferred_location",true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(payload);
  } else {

  }
}

function deletePreferences(id) {
    if(confirm("Are You Sure?")) {
        input = document.getElementById("edit-input-"+id);
        var data = {};
        data.position = id;
        data.location  = input.value;
        var payload = "position="+id+"&"+"location="+input.value;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
          if(this.status == 200 && this.readyState == 4) {
            console.log(this.responseText)
            //location.reload();
          }
        }
        xhr.open("DELETE","http://httpbin.org/put",true);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhr.send(payload);
    } else {

    }
}

function userHide(id) {
    orderId  = id;
    var json = JSON.stringify(orderId);
    sendRequest("app/user_hide_history.php", "PATCH", json, callBackReload());
}

function driverHide(id) {
    orderId  = id;
    var json = JSON.stringify(orderId);
    sendRequest("app/driver_hide_history.php", "PATCH", json, callBackReload());
}

function validateForm(form) {
    inputs = form.querySelectorAll("input , textarea");
    f=true;
    for(i=0;i<inputs.length;i++) {
        if(inputs[i].getAttribute("optional") == null) {
            if(!inputs[i].value) {
                f= false;
                inputs[i].style.border = "1px solid #CC0000";
            } else if(inputs[i].name == "phoneNumber") {
                inputs[i].style.border = "1px solid #CC0000";
                f= !isNaN(inputs[i].value)
            }
            else {
                inputs[i].style.border = "1px solid #00782d";
            }
        } else {
            inputs[i].style.border = "1px solid #00782d";
        }
    }
    return f;
}
