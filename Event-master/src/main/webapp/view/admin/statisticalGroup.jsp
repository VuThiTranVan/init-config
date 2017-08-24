<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
	<form class="panel-body margin-type-group">
		<label for="groupType">Statistical:</label> <select id="groupType"
			name="goupType">
			<option value="0" selected="selected">Type</option>
			<option value="1">Status</option>
			<option value="2">Delete Flag</option>
		</select>
	</form>
	<label class="form-label"> <spring:message
			code='statistical_group_number' /></label>
	<section class="bg_white clearfix messageError center">
		<div class="body clearfix mt20 manageUser" id="messageContainer">
		</div>
	</section>
	<label id="mgsNoResult" class="hidden_elem"><spring:message
			code='no_find_result_search' text='' /></label>
	<div id="chart"></div>
	<div class="clearfix"></div>
	<div id="sub_btn">
		<a href="/EventMedia/manageGroup" class="btn btn-detail"><input
			type="button" value="BACK HOME" class="btn-forwardscreen"></a>
	</div>
</body>
</html>
