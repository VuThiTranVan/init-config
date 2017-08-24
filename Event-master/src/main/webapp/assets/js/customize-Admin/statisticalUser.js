function renderPlot(data) {
	$("#chart").empty();
	$("#chartError").hide();
	var nameTypeGroup = data.nameTypeGroup;
	var nameGroup = data.nameGroup;
	var numberUser = data.numberUser;
	if (numberUser.length == 0) {
		renderError();
	} else {
		$("#chart").show();
		$('#messageContainer').html('');
		$.jqplot("chart", [ numberUser ], {
			series : [ {
				renderer : $.jqplot.BarRenderer,
				rendererOptions : {
					barDirection : 'horizontal',
					varyBarColor : true
				}
			} ],
			axes : {
				yaxis : {
					label : 'Name Group',
					renderer : $.jqplot.CategoryAxisRenderer,

					rendererOptions : {
						tickRenderer : $.jqplot.CanvasAxisTickRenderer
					},
					ticks : nameGroup,
					tickOptions : {
						showGridline : false,
						markSize : 0
					},
					pad : 0
				},
				xaxis : {
					label : 'Number User'

				}
			}
		});
	}
}

function renderError() {
	$("#chart").hide();
	$('#messageContainer').html($("#mgsNoResult").text());
}

function reloadPlot(countryCode) {
	$.ajax({
		url : encodeURI("/EventMedia/statictical/" + countryCode),
		type : "GET",
		dataType : "json",
		success : renderPlot,
		error : renderError
	});
}

function initializeCountries() {
	$("#groupType").change(function() {
		var countryCode = $(this).val();
		reloadPlot(countryCode);
	});
	reloadPlot($("#groupType").val());
}

$(document).ready(initializeCountries);
