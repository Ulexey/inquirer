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
	<br />
	<h2>
		<spring:message code="label.surveys" />
	</h2>

	<c:url var="saveUrl" value="/inquiry/${inquiry.id}/survey/add" />
	<form:form method="post" action="${saveUrl}" commandName="survey">
		<table>
			<tr>
				<td><input size="10" type="text" value="${inquiry.id}"
					readonly="readonly" style="background: #808080" /> <input
					size="50" readonly="readonly" type="text" value="${inquiry.body}"
					style="background: #808080" /></td>
			</tr>

			<tr>
				<td><form:label path="fio">
						<spring:message code="label.survey.fio" />
					</form:label></td>
			</tr>
			<tr>
				<td><form:input path="fio" /></td>
			</tr>
			<tr>
				<td><input type="submit"
					value="<spring:message code="label.button.survey.add"/>" name="add" /></td>
				<td><input type="submit"
					value="<spring:message code="label.button.survey.show.by.fio"/>"
					name="show" /></td>
			</tr>
		</table>
	</form:form>

	<h3>
		<spring:message code="label.survey.list.by.fio" />
	</h3>
	<table border="true">
		<c:forEach items="${surveyList}" var="survey">
			<tr>
				<td><c:out value="${survey.id}" /></td>
				<td><c:out value="${survey.fio}" /></td>
				<td><c:out value="${survey.start}" /></td>
				<td><c:out value="${survey.stop}" /></td>

				<td><a
					href="<c:url value="/inquiry/${inquiry.id }/survey/${survey.id }/results/show" />"><spring:message
							code="label.show" /></a><br /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>