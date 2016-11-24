var changeSource = function(source) {
	switch (parseInt(source)) {
	case 1:
		editLinkId('https://www.youtube.com/embed/');
		break;
	case 2:
		editLinkId('https://player.vimeo.com/video/');
		break;
	case 3:
		break;
	}
}

var postDownload = function() {
	var url = $('#link').val();
	var fileName = $('#urlRoute').val();
	
	var response = $.post("/download", {
		"url" : url,
		"filename" : filename
	}).done(function(data) {
		alert(data);
		grecaptcha.reset(widgetCaptcha);
	}).fail(function(data) {
		alert(data);
	}); 
}


var editLinkId = function(link){
	$('#link').val(link);
}