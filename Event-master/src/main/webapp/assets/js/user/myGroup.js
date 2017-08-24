function getGroup() {
	$.ajax({
	    url : "/EventMedia/user/myGroup",
	    type : "POST",
	    data : false,
	    dataType : 'json',
	    async : true,
	    success : function(data, textStatus, jqXHR) {
	    	$(".btnLeaveGroup").removeClass('hidden_elem');
    		// display section group info
    		$(".infoGroup").show();

	    	// group info
    		$("#lblName").text(data.name);
    		$("#lblDescription").text(data.description);
    		$("#lblNote").text(data.note);
    		$("#lblDateStart").text(data.dateStart);
    		$("#lblDateEnd").text(data.dateEnd);
    		
    		$("input[name=status][value=" + data.status + "]").prop('checked', true);
    		$("input[name=type][value=" + data.type + "]").prop('checked', true);

    		var flgJoin = true;
            for (var i = 0; i < data.user.length; i++) {
                if ($(".lblusername").text() == data.user[i].username) {
                	flgJoin = false;
                	break;
                }
            }
            if (flgJoin) {
    	    	$(".btnRequestJoin").removeClass('hidden_elem');
    	    	$(".lblIdGroup").text(data.id);
            }
    		
    		// List member
    		if (data.user.length > 0) {
	    		if ($.fn.DataTable.isDataTable('#dataTables-result')) {
	                $('#dataTables-result').DataTable().destroy();
	            }

                $('#dataTables-result').DataTable({
                    "bProcessing" : true,
                    "aaData" : data.user,
                    "createdRow" : function(row, data, dataIndex) {
                        $(row).addClass('gradeX');
                    },
                    "aoColumns" : [
                    	{
                            "mDataProp" : "username",
                        }, { "mDataProp" : "name"
                        }, { "mDataProp" : "email"
                        }, { "mDataProp" : "phone"
                        }, { "mDataProp" : "statusJoin"
                        }],
                    responsive : true
                });
                
                $(".listMember").show();
			} else {
				 $(".listMember").hide();
				if (data.status == 0) {
					$(".listMember").show();
					$(".listMemberBody").html($("mgsNoMember").text());
    			}
    		}

    		// list image
    		if (data.image.length > 0) {
    			if ($.fn.DataTable.isDataTable('#dataTables-image')) {
                    $('#dataTables-image').DataTable()
                            .destroy();
                }

                $('#dataTables-image').DataTable({
                    "bProcessing" : true,
                    "aaData" : data.image,
                    "createdRow" : function(row, data, dataIndex) {
                        $(row).addClass('gradeX');
                    },
                    "aoColumns" : [
                    	{
                            "mDataProp" : "title"
                        }, { "mDataProp" : "userCreate"
                        }, { "mDataProp" : "votes.length"
                        }, { "mDataProp" : "url",
                        	"mRender": function(data, type, row) {
                           	return "<img src='"+data+"' style='height: 150px;'"
                                +" data-to='"+data+"'"
                                 + " data-caption='Like: <b>"+row.votes.length +"</b><br>" +
                                 		"<b>Title</b>: "+row.title +
                                 "</b></u><br>Description: "+row.description+"' class='modallery'>";
                        	}
                        }],
                    responsive : true
                });

            	$(this).modallery({
            	    title: "<b>"+data.name +"</b>",
            	    navigate: true,
            	    arrows: true,
            	    keypress: true
            	  });
            	
    			$(".listImage").show();
    		} else {
    			$(".listImageBody").html($("mgsNoImage").text());
    			$(".listImage").show();
    		}
	    	
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	$(".infoGroup").hide();
	    	$(".listImage").hide();
	    	$(".listMember").hide();
	    	$("#messageContainer").removeClass('hidden_elem');
	    }
	});
}

function clickLeaveGroup() {
	if (confirm($("#comfirmLeaveGroup").text()) == true) {
		$.ajax({
			url : "/EventMedia/user/leaveGroup",
			type : "GET",
			data : false,
			dataType : 'json',
			success : function(data) {
				if (data) {
					alert($("#mgsLeaveGroupSuccess").text());
					window.location.href = "/EventMedia/user/";
				}
				else {
					$('#mgsRequestJoin').html($("#requestJoinError").text());
				}
			},
			error : function(error) {
				$('#mgsRequestJoin').html($("#requestJoinError").text());
			}
		});
	}
}