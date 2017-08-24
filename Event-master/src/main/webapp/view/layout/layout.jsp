<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- Default CSS --%>
<tiles:importAttribute name="cssDefault" />
<c:forEach var="item" items="${cssDefault}">
	<link rel="stylesheet" href="<c:url value='${item}'/>" type="text/css" />
</c:forEach>

<%-- Default JS --%>
<tiles:importAttribute name="jsDefault" />
<c:forEach var="item" items="${jsDefault}">
	<script src="<c:url value="${item}"/>"></script>
</c:forEach>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
</head>
<body>

	<tiles:insertAttribute name="header" />

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#site-menu"></button>
				<p class="navbar-brand">
					<tiles:insertAttribute name="screenname" />
				</p>

			</div>

			<div class="collapse navbar-collapse navbar-custom" id="site-menu">
				<tiles:insertAttribute name="menu" />
				<c:if test="${not empty pageContext.request.userPrincipal.name}">
					<form:form action="${pageContext.request.contextPath}/logout"
						method="POST">
						<p class="pull-right">
							${pageContext.request.userPrincipal.name} | <input
								class="btn-forwardscreen" name="submit" type="submit"
								value="Logout">
						</p>
					</form:form>
				</c:if>
				<c:if test="${empty pageContext.request.userPrincipal.name}">
					<p class="pull-right" onclick="location.href = '/EventMedia/login';"><input
								class="btn-forwardscreen"  type="button"
								value="Login">
						</p>
				</c:if>
			</div>
		</div>
	</nav>

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />

</body>

<%-- Default CSS --%>
<tiles:importAttribute name="cssAdd" />
<c:forEach var="item" items="${cssAdd}">
	<link rel="stylesheet" href="<c:url value='${item}'/>" type="text/css" />
</c:forEach>

<%-- Addition JS --%>
<tiles:importAttribute name="jsAdd" />
<c:forEach var="item" items="${jsAdd}">
	<script src="<c:url value="${item}"/>"></script>
</c:forEach>
</html>
