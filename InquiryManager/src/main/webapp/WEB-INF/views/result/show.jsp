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
		<spring:message code="label.results" />
	</h2>

	<br />
	<c:out value="${results[0].survey.id }"></c:out>
	<c:out value="${results[0].survey.fio }"></c:out>
	<c:out value="${results[0].survey.start }"></c:out>
	<c:out value="${results[0].survey.stop }"></c:out>
	<br />
	<table border="true">
		<tr>
			<th><spring:message code="label.result.column.question"/></th>
			<th><spring:message code="label.result.column.answer.right"/></th>
			<th><spring:message code="label.result.column.answer.select"/></th>
			<th><spring:message code="label.result.column.answer"/></th>
		</tr>
		<c:forEach items="${results}" var="result">
			<c:if test="${prev != result.answer.question.id}">
				<tr>
					<td><c:out value="${result.answer.question.body}" /></td>

					<td><c:forEach items="${results}" var="result2">
							<c:if
								test="${result.answer.question.id == result2.answer.question.id}">
								<li type="none"><c:out value="${result2.answer.valid}" /></li>
							</c:if>
						</c:forEach></td>

					<td><c:forEach items="${results}" var="result2">
							<c:if
								test="${result.answer.question.id == result2.answer.question.id}">
								<li type="none"><c:out value="${result2.checked}" /></li>
							</c:if>
						</c:forEach></td>

					<td><c:forEach items="${results}" var="result2">
							<c:if
								test="${result.answer.question.id == result2.answer.question.id}">
								<li type="none"><c:out value="${result2.answer.body}" /></li>
							</c:if>
						</c:forEach></td>
					<c:set var="prev" value="${result.answer.question.id}" />
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>