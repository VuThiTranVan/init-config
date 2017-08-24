<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test="${not empty user.username }">
			<section class="pb50">
				<div class="body clearfix mt20 manageUser">
					<div class="panel panel-default">
						<div class="panel-heading detail-user">
							<div class="detail-user-head-left">
								<h3 class="panel-title">Imfomation detail user</h3>
							</div>
						</div>
						<div class="panel-body">

							<table class="table-bordered profile_regist">
								<tr>
									<th>Id user</th>
									<td><label>${user.id}</label></td>
								</tr>
								<tr>
									<th>User name</th>
									<td><label>${user.username}</label></td>
								</tr>
								<tr>
									<th>Full name</th>

									<td><label>${user.name}</label></td>
								</tr>
								<tr>
									<th>Permissions</th>
									<td><label>${user.permission.name}</label></td>
								</tr>
								<tr>
									<th>Birthday</th>
									<td><label>${user.birthday}</label></td>
								</tr>

								<tr>
									<th>Phone number</th>
									<td><label>${user.phone}</label></span></td>
								</tr>
								<tr>
									<th>Gender</th>
									<td><label>${user.gender}</label></td>
								</tr>
								<tr>
									<th>Email</th>
									<td><label>${user.email}</label></td>
								</tr>
								<tr>
									<th>Status Join</th>
									<td><label>${user.statusJoin}</label></td>
								</tr>
								<tr>
									<th>Id Group</th>
									<td><label>${user.idGroup}</label></td>
								</tr>
								<tr>
									<th>Date create</th>
									<td><label>${user.dateCreate}</label></td>
								</tr>
								<tr>
									<th>User create</th>
									<td><label>${user.userCreate}</label></td>
								</tr>
								<tr>
									<th>Date update</th>
									<td><label>${user.dateUpdate}</label></td>
								</tr>
								<tr>
									<th>User update</th>
									<td><label>${user.userUpdate}</label></td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="sub_btn">
					<a href="/EventMedia/manageUser"><input type="button"
						value="Back home" class="btn-forwardscreen"></a>
				</div>
			</section>
	</c:when>
</c:choose>