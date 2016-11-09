var postComment = function() {
	var comment = $('#comment').val();
	var entryId = $('#entryId').val();

	var postComment = $.post("/postComment", {
		"comment" : comment,
		"entryId" : entryId
	}).done(function() {
		alert("Yorumunuz onaylandıktan sonra yayınlanacaktır.");
	}).fail(function() {
		alert("Yorumunuz eklenemedi. Lütfen daha sonra tekrar deneyiniz.");
	}).always(function() {
		$("#comment").val('');
	});
}