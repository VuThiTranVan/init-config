window.onload = function() {
	$('#messageContainer').html('');
    var postData = $(this).closest('form').serializeArray();

    $.ajax({
            url : "/EventMedia/user/searchgroup",
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
                	$(".notication-join-group").removeClass('hidden_elem');
                    $('#resultSearch').removeClass('hidden_elem');
                    $('#resultSearch').addClass('clearfix body manageUser');

                    $('#dataTables-result').DataTable({
                        "bProcessing" : true,
                        "aaData" : data,
                        "createdRow" : function(row, data, dataIndex) {
                            $(row).addClass('gradeX');
                        },
                        "aoColumns" : [
                            {
                                "mDataProp" : "name",
                                "mRender" : function(data, type, row) {
                                    return "<button class='requestJoin' onclick='clickJoinRequest("+row.id+")'>" +
                                    		"<img src='../assets/imgs/join-group.png' alt='Request join group' class='img-icon'/></button>"
                                    +"&nbsp;&nbsp;&nbsp;<a href='/EventMedia/groupInfo/" + row.id + "'>"
                                            + data+"</a>";
                                },
                            }, { "mDataProp" : "description"
                            }, { "mDataProp" : "dateStart"
                            }, { "mDataProp" : "dateEnd"
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

function clickJoinRequest(id) {
	$('#messageContainer').html("");
	var formURL = "/EventMedia/groupInfo/" + id+ "/request";
	$.ajax({
		url : formURL,
		type : "GET",
		data : false,
		dataType : 'json',
		success : function(data) {
			if (data) {
				$(".requestJoin").addClass('hidden_elem');
				// Message
				$('#messageContainer').html($("#requestJoinSuccess").text());
			}
			else {
				$('#messageContainer').html($("#requestJoinError").text());
			}
		},
		error : function(error) {
			$('#messageContainer').html($("#requestJoinError").text());
		}
	});
}