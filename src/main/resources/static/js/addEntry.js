$(document).ready(function() {
	$('#link').bind("focusout", function() {
		$('#youtubeVideo').attr("src", $('#link').val());
	});
});