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
			code="label.back" />
	</a>
	<br />

	<h2>
		<spring:message code="label.questions" />
	</h2>

	<c:url var="saveUrl" value="../addQuestion?inquiryId=${inquiry.id}" />
	<form:form method="post" action="${saveUrl}" commandName="question">
		<table>
			<tr>

				<td><input size="10" type="text" value="${inquiry.id}" readonly="readonly" style="background:#808080"/>
				<input size="50" readonly="readonly" type="text" value="${inquiry.body}" style="background:#808080" />
				</td>
			</tr>

			<tr>
				<td><form:label path="body">
						<spring:message code="label.bodyQuestion" />
					</form:label></td>
			</tr>

			<tr>
				<td><form:textarea path="body" rows="5" cols="30" /></td>
			</tr>
			<tr>
				<td><form:label path="multivalue">
						<spring:message code="label.multivalue" />
						<form:radiobutton path="multivalue" value="true" />Множественный
							<form:radiobutton path="multivalue" value="false"
							checked="checked" />Альтернативный

					</form:label></td>
			</tr>
			<tr>
				<td><input type="submit"
					value="<spring:message code="label.addquestion"/>" /></td>
			</tr>
		</table>
	</form:form>

	<h3>
		<spring:message code="label.listQuestions" />
	</h3>
	<c:if test="${!empty inquiry.getQuestions()}">
		<table class="data">
			<c:forEach items="${inquiry.getQuestions()}" var="question">
				<tr>
					<td><textarea rows="5" cols="30" readonly="readonly" style="background:#808080" >${question.body}</textarea>
					</td>
					<td>Множественный? <br />${question.multivalue}</td>
					<td><a
						href="../question/delete/${question.id}?inquiryId=${inquiry.id}"><spring:message
								code="label.delete" /></a> <br /> <a
						href="../inquiry/${inquiry.id}/question/${question.id}"><spring:message
								code="label.edit" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>