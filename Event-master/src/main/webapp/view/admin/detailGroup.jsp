<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test="${not empty groupInfo.name }">
			<section class="pb50">
				<div class="body clearfix mt20 manageUser">
					<div class="panel panel-default">
						<div class="panel-heading detail-groupInfo">
							<div class="detail-groupInfo-head-left">
								<h3 class="panel-title">Imfomation detail groupInfo</h3>
							</div>
						</div>
						<div class="panel-body">

							<table class="table-bordered profile_regist">
								<tr>
									<th>Id Group</th>
									<td><label>${groupInfo.id}</label></td>
								</tr>
								<tr>
									<th>Name Group</th>
									<td><label>${groupInfo.name}</label></td>
								</tr>
								<tr>
									<th>Description</th>
									<td><label>${groupInfo.description}</label></td>
								</tr>
								<tr>
									<th>Note</th>
									<td><label>${groupInfo.note}</label></td>
								</tr>

								<tr>
									<th>Type</th>
									<td><label>${groupInfo.type}</label></span></td>
								</tr>
								<tr>
									<th>Status</th>
									<td><label>${groupInfo.status}</label></td>
								</tr>
								<tr>
									<th>Date Start</th>
									<td><label>${groupInfo.dateStart}</label></td>
								</tr>
								<tr>
									<th>Date End</th>
									<td><label>${groupInfo.dateEnd}</label></td>
								</tr>
								<tr>
									<th>Date create</th>
									<td><label>${groupInfo.dateCreate}</label></td>
								</tr>
								<tr>
									<th>User create</th>
									<td><label>${groupInfo.userCreate.username}</label></td>
								</tr>
								<tr>
									<th>Date update</th>
									<td><label>${groupInfo.dateUpdate}</label></td>
								</tr>
								<tr>
									<th>User update</th>
									<td><label>${groupInfo.userUpdate}</label></td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="sub_btn">
					<a href="/EventMedia/manageGroup"><input type="button"
						value="Back home" class="btn-forwardscreen"></a>
				</div>
			</section>
	</c:when>
</c:choose>