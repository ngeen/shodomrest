var reCaptcha;

var verifyCallback = function(response) {
	$('#hiddenCaptcha').val(response);
};

var onloadCallback = function() {

	reCaptcha = grecaptcha.render('reCaptcha', {
		'sitekey' : '6LfdiwwUAAAAAHtmlzuLKyIwltl39O0zn06tTv-J',
		'callback' : verifyCallback
	});
};