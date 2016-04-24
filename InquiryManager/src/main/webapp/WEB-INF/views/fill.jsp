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

<a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>
  
<h2><spring:message code="label.title" /></h2>


<c:url var="saveUrl" value="addQuestion?id=${inquiry.id}" />
<form:form method="post" action="${saveUrl}" commandName="question">

	<table>
		<tr>
		   	<td><form:label path="id">
				<spring:message code="label.id" />
			</form:label></td>
  		<td><input type="number" value="${inquiry.id}" disabled="true"/>
		</tr>

		<tr>
			<td><form:label path="body">
				<spring:message code="label.body" />
			</form:label></td>
			<td><form:input path="body" /></td>
		</tr>

		<tr>
			<td><form:label path="multivalue">
				<spring:message code="label.multivalue" />
			</form:label></td>
			<td><form:radiobutton path="multivalue" value="true"/>TRUE <form:radiobutton path="multivalue" value="false"/>FALSE</td>
		</tr>

		<tr>
			<td><input type="submit"
				value="<spring:message code="label.addquestion"/>" /></td>
		</tr>

	</table>
</form:form>

<h3><spring:message code="label.questions" /></h3>
<c:if test="${!empty inquiry.getQuestions()}">
	<table class="data">
		<tr>
			<th><spring:message code="label.body" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${inquiry.getQuestions()}" var="question">
			<tr>
				<td>${question.body}</td>
				<td>${question.multivalue}</td>
				<td><a href="delete/question/${question.id}"><spring:message code="label.delete" /></a></td>
				<td><a href="fill/${question.id}"><spring:message code="label.fill" /></a></td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body></html>