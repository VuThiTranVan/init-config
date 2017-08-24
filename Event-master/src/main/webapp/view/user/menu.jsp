<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty pageContext.request.userPrincipal.name}">
	<div class="navbar-header collapse navbar-collapse menu-admin" >
		<a href="${pageContext.request.contextPath}/homePageUserGroup"><button type="button" class="btn">Home</button></a>
		<a href="${pageContext.request.contextPath}/upload"><button type="button" class="btn">Upload Image</button></a>
		<a href="${pageContext.request.contextPath}/manageImage"><button type="button" class="btn">Manage Image</button></a>
		<a href="${pageContext.request.contextPath}/user/myGroup">
			<button id="btnMyGroup" type="button" class="btn">My group</button></a>
		<a href="${pageContext.request.contextPath}/chat"><button type="button" class="btn">Chat</button></a>
		<button type="button" class="btn">Persional</button>
	</div>
</c:if>
