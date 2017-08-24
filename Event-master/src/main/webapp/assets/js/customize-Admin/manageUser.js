function submitClear() {
	    $("#userName").val("");
	    $('#permissioName').val("0");
}
window.onload = function() {
	$('#messageContainer').html('');
    var postData = $(this).closest('form').serializeArray();

    $.ajax({
            url : "/EventMedia/search",
            type : "GET",
            data : postData,
            async : false,
            success : function(data, textStatus, jqXHR) {
                if ($.fn.DataTable
                        .isDataTable('#dataTables-result')) {
                    $('#dataTables-result').DataTable()
                            .destroy();
                }

                if (data != "") {
                    $('#resultSearch').removeClass(
                            'hidden_elem');
                    $('#resultSearch').addClass(
                            'clearfix body manageUser');

                    $('#dataTables-result').DataTable({
                        "bProcessing" : true,
                        "aaData" : data,
                        "createdRow" : function(row, data, dataIndex) {
                            $(row).addClass('gradeX');
                        },
                        "aoColumns" : [
                            {
                                "mDataProp" : "username",
                                "mRender" : function(data, type, row) {
                                	if("ROLE_ADMIN" == row.permission.name){
                                		return "<a href='/EventMedia/detail/" + row.id + "'>"
                                                + data+"</a>";
                                	}
                                	
                                    return "<button onclick='clickBtnDel("+row.id+",this)'> Delete </button>"
                                    +"&nbsp;&nbsp;&nbsp;<a href='/EventMedia/detail/" + row.id + "'>"
                                            + data+"</a>";
                                },
                            }, { "mDataProp" : "name"
                            }, { "mDataProp" : "birthday"
                            }, { "mDataProp" : "email"
                            }, {"mDataProp" : "gender"
                            }, { "mDataProp" : "phone"
                            }, { "mDataProp" : "permission.name"
                            }],
                        responsive : true
                    });
                } else {
                    $('#resultSearch').addClass('hidden_elem');
                    $('#messageContainer').html($("#mgsNoResult").text());
                }
            },
            error : function(xhr, status, error) {
                $('#resultSearch').addClass(
                        'hidden_elem');
                $('#messageContainer').html($("#mgsNoResult").text());
            }
        });
	};
	
$("#btn_seach").click(function(e) {

    $('#messageContainer').html('');
    e.preventDefault();
    var postData = $(this).closest('form').serializeArray();

    $.ajax({
            url : "/EventMedia/search",
            type : "GET",
            data : postData,
            async : false,
            success : function(data, textStatus, jqXHR) {
                if ($.fn.DataTable
                        .isDataTable('#dataTables-result')) {
                    $('#dataTables-result').DataTable()
                            .destroy();
                }

                if (data != "") {
                    $('#resultSearch').removeClass(
                            'hidden_elem');
                    $('#resultSearch').addClass(
                            'clearfix body manageUser');

                    $('#dataTables-result').DataTable({
                        "bProcessing" : true,
                        "aaData" : data,
                        "createdRow" : function(row, data, dataIndex) {
                            $(row).addClass('gradeX');
                        },
                        "aoColumns" : [
                        	{
                                "mDataProp" : "username",
                                "mRender" : function(data, type, row) {
                                	var a = "aasa";
                                    return "<button onclick='clickBtnDel("+row.id+",this)'> Delete </button>"
                                    +"&nbsp;&nbsp;&nbsp;<a href='/EventMedia/detail/" + row.id + "'>"
                                            + data+"</a>";
                                },
                            }, { "mDataProp" : "name"
                            }, { "mDataProp" : "birthday"
                            }, { "mDataProp" : "email"
                            }, {"mDataProp" : "gender"
                            }, { "mDataProp" : "phone"
                            }, { "mDataProp" : "permission.name"
                            }],
                        responsive : true
                    });
                } else {
                    $('#resultSearch').addClass('hidden_elem');
                    $('#messageContainer').html($("#mgsNoResult").text());
                }
            },
            error : function(xhr, status, error) {
                $('#resultSearch').addClass(
                        'hidden_elem');
                $('#messageContainer').html($("#mgsNoResult").text());
            }
        });
    

});

// Del user on list
function clickBtnDel(idUser, el) {
	if (confirm("Are you sure delete record?") == true) {
		$('#messageContainer').html('');
		
		var formURL = "/EventMedia/delete/" + idUser;
		$.ajax({
			url : formURL,
			type : "GET",
			data : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					// remove datatable
					var a = $('#dataTables-result').DataTable();
					a.row($(el).parents('tr')).remove().draw();
					
					// Message
					$('#messageContainer').html($("#mgsSuccess").text());
				}
				else {
					$('#messageContainer').html($("#mgsError").text());
				}
			},
			error : function(error) {
				$('#messageContainer').html($("#mgsError").text());
			}
		});
	}
};

