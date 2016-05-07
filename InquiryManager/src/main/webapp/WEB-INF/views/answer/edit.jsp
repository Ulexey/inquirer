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
		<spring:message code="label.answer" />
	</h2>

	<c:url var="saveUrl" value="edit" />
	<form:form modelAttribute="answer" method="POST" action="${saveUrl}">
		<table>
					<tr>

				<td><input size="10" type="text" value="${inquiry.id}" readonly="readonly" style="background:#808080"/>
				<input size="50" readonly="readonly" type="text" value="${inquiry.body}" style="background:#808080" />
				</td>
			</tr>
			<tr>

				<td><input size="10" type="text" value="${question.id}" readonly="readonly" style="background:#808080"/>
				<input size="50" readonly="readonly" type="text" value="${question.body}" style="background:#808080" />
				</td>
			</tr>

			<tr>
				<td><form:label path="id">
						<spring:message code="label.body" />
					</form:label><br />
				<form:textarea path="body" rows="5" cols="30" /></td>
			</tr>

			<tr>
				<td><form:label path="valid">
						<spring:message code="label.valid" />
					</form:label><br />Правильный ответ?
				<form:checkbox path="valid" /></td>
			</tr>
		</table>

		<input type="submit" value="<spring:message code="label.saveAnswer"/>" />
	</form:form>

</body>



</html>