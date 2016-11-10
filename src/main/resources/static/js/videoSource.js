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


var editLinkId = function(link){
	$('#link').val(link);
}