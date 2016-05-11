<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="label.title" /></title>
</head>
<body>
	<a href="<c:url value="/logout" />"> <spring:message
			code="label.logout" />
	</a>
	<br />
	<a href="<c:url value="/index" />"> <spring:message
			code="label.index" />
	</a>
	<br />
	<h2>
		<spring:message code="label.head.survey.list.full" />
	</h2>
		<table border="true">
			<c:forEach items="${surveys}" var="survey">
				<tr>
					<td><c:out value="${survey.id}" /></td>
					<td><c:out value="${survey.fio}" /></td>
					<td><c:out value="${survey.start}" /></td>
					<td><c:out value="${survey.stop}" /></td>
					<td>
						<a href=<c:url value="/inquiry/${survey.inquiry.id}/survey/${survey.id}/show" />><spring:message
								code="label.show" /></a><br/>
					</td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>