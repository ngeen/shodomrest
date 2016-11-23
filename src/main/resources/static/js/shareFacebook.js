window.fbAsyncInit = function() {
	FB.init({
		appId : '1819267634986583',
		xfbml : true,
		version : 'v2.8'
	});
	FB.AppEvents.logPageView();
};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function gonder() {
	var link = "http://shodom.com/detail/" + $("#urlRoute").val();
	FB
			.api(
					'/321037334945326/feed',
					'POST',
					{
						"link" : link,
						"access_token" : "EAAZA2nUEiflcBAC430vRDJvUYiXwglr40ZCXINqMetBYJxrkGoya9wHcQ9PpDxZAVC8NFjFfAGsZAwQlvOiPNyRZCEkFXe30t82od69a7fd6fCoiK1sv5dvQ1EiVO3eliD4absyxZBFr4qlQ9TxRdPATnKf35wlTegeOsun2qSeQZDZD"
					}, function(response) {
						if (!response || response.error) {
					        alert('Error occured');
					        console.log(response);
					    } else {
					        alert('Post ID: ' + response.id);
					    }
					});
}