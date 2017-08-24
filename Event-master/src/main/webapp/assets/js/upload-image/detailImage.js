window.onload = function() {
	//$(".alert").hide();
	$("#formAlert").hide();
	$("#formAlertImage").hide();
	$("#formAlertError").hide();
	$("#formAlertErrorDes").hide();
	$("#formAlertEmpty").hide();
}
function clickBtnEdit() {
	$(".editForm").removeClass('hidden_elem');
	$(".lableForm").addClass('hidden_elem');
	$("#savebtn").prop('disabled', false);
	document.getElementById("fileName").readOnly = true; 
}

function clickBtnCancel() {
	$("#formAlert").hide();
	$("#formAlertImage").hide();
	$("#formAlertError").hide();
	$("#formAlertErrorDes").hide();
	$("#formAlertEmpty").hide();
	$(".lableForm").removeClass('hidden_elem');
	$(".editForm").addClass('hidden_elem');
	$("#updateForm")[0].reset();
}

function checkEmpty(str) {
	if (str == "") {
		return true;
	}
	return false;
}

// update user
$("#savebtn").click(function() {
	var fileImport = $("#fileImport").val();
	var fileName = $("#fileName").val();
	var url = $("#url").val();
	
	var title = $("#title").val();
	var description = $("#description").val();
	
	var check = true;
	
	if (checkEmpty(fileName)) {
		check = false;
		$("#formAlert").slideDown(400);
	} else {
		var suffixesFile = fileName.substring(fileName.lastIndexOf(".") + 1);
		$("#formAlert").slideUp(400);
		if (("png" != suffixesFile)&&("jpg" != suffixesFile)&&("jpeg" != suffixesFile)) {
			check = false;
			$("#formAlertImage").slideDown(400);
		}else{
			$("#formAlertImage").slideUp(400);
		}
	}
	if(title == "" || description == ""){
		check = false;
		$("#formAlertEmpty").slideDown(400);
	}else{
		$("#formAlertEmpty").slideUp(400);
	}
	if (title.length > 50) {
		check = false;
		$("#formAlertError").slideDown(400);
	} else {
		$("#formAlertError").slideUp(400);
	}
	
	if (description.length > 500) {
		check = false;
		$("#formAlertErrorDes").slideDown(400);
	} else {
		$("#formAlertErrorDes").slideUp(400);
	}
	
	if (check) {
		$("#updateForm").submit();
	}
	
});
	
$(document).on('click', '.browse', function() {
	var file = $(this).parent().parent().parent().find('.file');
	file.trigger('click');
});

$(document).on(
		'change',
		'.file',
		function() {
			$(this).parent().find('.form-control').val(
					$(this).val().replace(/C:\\fakepath\\/i, ''));
		});