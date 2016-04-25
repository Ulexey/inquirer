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
	<a href="<c:url value=".." />"> <spring:message code="label.back" />
	</a>
	<br />

	<h2>
		<spring:message code="label.answers" />
	</h2>


	<c:url var="saveUrl"
		value="../../../addAnswer?questionId=${question.id}&inquiryId=${inquiry.id}" />
	<form:form method="post" action="${saveUrl}" commandName="answer">

		<table>

			<td><input type="number" value="${question.id}" disabled="true" />
			</tr>

			<tr>
				<td><form:label path="body">
						<spring:message code="label.bodyAnswer" />
					</form:label><br />
				<form:textarea path="body" rows="5" cols="30" /></td>
			</tr>

			<tr>
				<td><form:label path="valid">
						<spring:message code="label.valid" />
					</form:label><br />
				<form:radiobutton path="valid" value="true" />Правильный <form:radiobutton
						path="valid" value="false" checked="checked" />Не правильный</td>
			</tr>

			<tr>
				<td><input type="submit"
					value="<spring:message code="label.addanswer"/>" /></td>
			</tr>

		</table>
	</form:form>

	<h3>
		<spring:message code="label.listAnswers" />
	</h3>
	<c:if test="${!empty question.getAnswers()}">
		<table class="data">

			<c:forEach items="${question.getAnswers()}" var="answer">
				<tr>
					<td><textarea rows="5" cols="30" readonly="readonly">${answer.body}</textarea></td>
					<td>Правильный? <br />${answer.valid}</td>
					<td><a href="${question.id}/answer/${answer.id}/delete"><spring:message
								code="label.delete" /></a><br />
					<a href="${question.id}/answer/${answer.id}"><spring:message
								code="label.edit" /></a></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

</body>
</html>