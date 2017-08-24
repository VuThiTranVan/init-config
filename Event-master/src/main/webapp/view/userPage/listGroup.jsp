<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 manageUser" id="messageContainer">
	</div>
</section>
<section class="bg_white clearfix noti-green">
	<div class="body clearfix mt20 manageUser hidden-elem notication-join-group">
		<spring:message code='mesage-notica-join-group' text='' />
	</div>
</section>
<label id="mgsNoResult" class="hidden_elem"><spring:message
		code='no_find_result_search' text='' /></label>
<label id="requestJoinError" class="hidden_elem">
	<spring:message code='message-request-join-group-error' text='' /></label>
<label id="requestJoinSuccess" class="hidden_elem">
	<spring:message code='message-request-join-group-success' text='' /></label>
	
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
		<a href="/EventMedia/user" class="btn btn-detail"><input
			type="button" value="BACK HOME" class="btn-forwardscreen"></a>
	</div>
</section>



