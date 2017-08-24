<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty pageContext.request.userPrincipal.name}">
	<div class="navbar-header collapse navbar-collapse menu-admin" >
		<a href="${pageContext.request.contextPath}/homePageUser"><button type="button" class="btn">Home</button></a>
		<a href="${pageContext.request.contextPath}/user/registerGroup">
			<button id="btnRegisterGroup" type="button" class="btn">Register Group</button></a> 
		<a href="${pageContext.request.contextPath}/user/group">
			<button id="btnRegisterGroup" type="button" class="btn">List Group</button></a>
		<a href="${pageContext.request.contextPath}/chat"><button type="button" class="btn">Chat</button></a>
		<button type="button" class="btn">Persional</button>
	</div>
</c:if>
