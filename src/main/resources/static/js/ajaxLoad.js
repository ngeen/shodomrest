$(document).ready(function() {
	var win = $(window);
	var page = 0;
	var finish = true;

	var entryTemp = function(entry) {
		var text = $("#entryTemplate").html();
		text = text.format(entry.link, entry.title, entry.urlRoute);
		return text;
	}

	String.prototype.format = function() {
		var args = arguments;
		return this.replace(/\{\{|\}\}|\{(\d+)\}/g, function(m, n) {
			if (m == "{{") {
				return "{";
			}
			if (m == "}}") {
				return "}";
			}
			return args[n];
		});
	};

	var loadEntry = function() {
		if(finish){
			$.ajax({
				url : '/listEntry/' + page,
				dataType : 'json',
				success : function(data) {
					var entryList = "";
					$.each(data, function(index) {
						var rowIndex = (parseInt(index) + 1);
						
						entryList += entryTemp(data[index]);

						if (rowIndex % 4 == 0 || rowIndex >= data.length) {
							var row = $("#rowTemplate").html();
							$("section").append(row.format(entryList));
							$('#loading').hide();
							entryList = "";
						}
					});

					if (data.length > 0) {
						page++;
					} else {
						//$('#loading').hide();
						finish = false;
					}
				}
			});	
		}
	}

	// Each time the user scrolls
	win.scroll(function() {
		// End of the document reached?
		if ($(document).height() - win.height() == win.scrollTop()) {
			//$('#loading').show();
			loadEntry();
		}
	});

	loadEntry();
});