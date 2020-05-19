function refreshCaptcha() {
	document.location.reload();
	document.forms[0].captcha.value="";
}

function clearFields() {	
	document.forms[0].reset();
}