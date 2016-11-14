tinymce
		.init({
			selector : 'textarea',
			entity_encoding : "raw",
			menubar : false,
			forced_root_block : "",
			plugins : [
					'advlist autolink lists link image charmap print preview anchor',
					'searchreplace visualblocks code fullscreen',
					'insertdatetime media table contextmenu paste code' ],
			toolbar : 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image code'
		});