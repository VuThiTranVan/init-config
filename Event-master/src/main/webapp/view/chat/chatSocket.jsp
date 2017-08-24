<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<body onload="connect()">
	<section class="bg_white clearfix">
		<div class="body clearfix mt20 manageUser">
			<div class="panel-heading ">
				<div class="panel panel-default">
					<div class="panel-heading">Send Messages</div>
					<div class="panel-body" id="conversationDiv">
						<div class="input-group">
							<input type="hidden" id="nameUser" name="nameUser"
								value="${userlogin}"> <input type="text"
								class="form-control" id="name" placeholder="Enter message">
							<span class="input-group-btn">
								<button id="sendName" class="btn btn-primary"
									onclick="sendName();">
									<span class="glyphicon glyphicon-share-alt"></span>&nbsp;Send
								</button>
							</span>
						</div>

						<!-- Error alert -->
						<div class="alert alert-danger alert-dismissable" id="formAlert">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><spring:message code='error' /></strong> <spring:message code='message_blank' />
						</div>
						
						<!-- Error alert -->
						<div class="alert alert-danger alert-dismissable" id="formAlertLength">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><spring:message code='error' /></strong> <spring:message code='message_150' />
						</div>
						
						<!-- /Error alert -->
						<!-- Info alert -->
						<div class="alert alert-info alert-dismissable" id="formInfoAlert">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<strong><spring:message code='sending' /></strong> <br /><spring:message code='message_send' />
						</div>
						<!-- /Info alert -->
						<!-- .input-group -->
					</div>
					<!-- .panel-body -->
					<div class="panel-body" id="response"></div>
				</div>
			</div>
		</div>
	</section>
</body>

</html>