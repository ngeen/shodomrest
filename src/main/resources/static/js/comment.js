var widgetCaptcha;

var postComment = function() {
	var gresponse = grecaptcha.getResponse(widgetCaptcha);
	var comment = $('#comment').val();
	var entryId = $('#entryId').val();
	
	var response = $.post("/postComment", {
		"comment" : comment,
		"entryId" : entryId,
		"response": gresponse
	}).done(function() {
		alert("Yorumunuz onaylandıktan sonra yayınlanacaktır.");
		$("#comment").val('');
		grecaptcha.reset(widgetCaptcha);
	}).fail(function() {
		alert("Yorumunuz eklenemedi. Lütfen 'Ben robot değilim' işaretlediğinize emin olun.");
		grecaptcha.reset(widgetCaptcha);
	}); 
}

var onloadCallback = function() {
	// Renders the HTML element with id 'example1' as a reCAPTCHA widget.
	// The id of the reCAPTCHA widget is assigned to 'widgetId1'.
	widgetCaptcha = grecaptcha.render('reCaptcha', {
		'sitekey' : '6LfdiwwUAAAAAHtmlzuLKyIwltl39O0zn06tTv-J',
		'theme' : 'light'
	});
};