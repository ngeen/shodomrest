var entryId;
var userId;

var favoriteCheck = function() {
	var favorite = ($("#favorite").val() === 'true');
	if (favorite) {
		$("#unFavoriteBtn").show();
		$("#favoriteBtn").hide();
	} else {
		$("#unFavoriteBtn").hide();
		$("#favoriteBtn").show();
	}
}

var postFavorite = function() {

	var postFavorite = $.post("/favorite/add", {
		"userId" : userId,
		"entryId" : entryId
	}).done(function() {
		$("#favorite").val('true');
		favoriteCheck();
	}).fail(function() {
		alert("Favori eklenemedi. Lütfen daha sonra tekrar deneyiniz.");
	});
}

var postUnFavorite = function() {

	var postUnFavorite = $.post("/favorite/delete", {
		"userId" : userId,
		"entryId" : entryId
	}).done(function() {
		$("#favorite").val('false');
		favoriteCheck();
	}).fail(function() {
		alert("Favori silinemedi. Lütfen daha sonra tekrar deneyiniz.");
	});
}

$(function() {
	entryId = $('#entryId').val();
	userId = $('#userId').val();

	favoriteCheck();
});