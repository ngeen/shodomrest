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
	
	$.post("/download", {
		"fileName" : fileName,
		"url" : url
	}).done(function(data) {
		alert("başarılı");
		console.log(data);
	}).fail(function(data) {
		alert("başarısız");
		console.log(data);
	}); 
}


var editLinkId = function(link){
	$('#link').val(link);
}