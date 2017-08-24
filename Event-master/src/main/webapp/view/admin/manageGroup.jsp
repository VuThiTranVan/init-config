<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 manageUser" id="messageContainer">
	</div>
</section>
<label id="mgsNoResult" class="hidden_elem"><spring:message
		code='no_find_result_search' text='' /></label>
<label id="mgsSuccess" class="hidden_elem"><spring:message
		code='delete_success' text='' /></label>
<label id="mgsError" class="hidden_elem"><spring:message
		code='delete_error' text='' /></label>
<section class="bg_white clearfix">
	<div class="body clearfix mt20 manageUser">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Search area</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/searchgroup" var="searchActionUrl" />
				<form role="form" id="searchForm" action="${searchActionUrl}"
					method="post" modelAttribute="conditionSearchGroup">
					<div class="form-group form-group-lg">
						<div class="col-sm-6">
							<label>Name</label> <input class="form-control" id="nameGroup"
								name="nameGroup" type="text" placeholder="please input text">
						</div>
						<div class="col-sm-6">
							<label>Group Type:</label> 
							<select id="typeGroup" name="typeGroup" class="form-control">
								<option value="-1" selected="selected"></option>
								<option value="0">Private</option>
								<option value="1">Public</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="sub_btn">
						<input type="button" onclick="submitClear()" value="Clear"
							class="btn btn-defaul"> <input type="button"
							value="Search" id="btn_seach" class="btn">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</section>
<section class="pb50">
	<div id="resultSearch" class="hidden_elem">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">List result</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-result" width="100%">
								<thead>
									<tr>
										<th>Name</th>
										<th>Decription</th>
										<th>Type</th>
										<th>Status</th>
										<th>Date Start</th>
										<th>Date End</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</div>

	<div class="clearfix"></div>
	<div id="sub_btn">
		<a href="/EventMedia/admin" class="btn btn-detail"><input
			type="button" value="BACK HOME" class="btn-forwardscreen"></a>
			<a href="/EventMedia/staticticalgroup" class="btn btn-detail"><input
			type="button" value="STATICTICAL" class="btn-forwardscreen"></a>
	</div>
</section>



