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

<form:form method="post" action="addInquiry" commandName="inquiry">

	<table>
		<tr>
			<td><form:label path="body">
				<spring:message code="label.body" />
			</form:label></td>
			<td><form:input path="body" /></td>
		</tr>
		
		<tr>
			<td><input type="submit"
				value="<spring:message code="label.addinquiry"/>" /></td>
		</tr>
	</table>
</form:form>

<h3><spring:message code="label.inquiries" /></h3>
<c:if test="${!empty inquiryList}">
	<table class="data">
		<tr>
			<th><spring:message code="label.body" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${inquiryList}" var="inquiry">
			<tr>
				<td>${inquiry.body}</td>
				<td><a href="delete/inquiry/${inquiry.id}"><spring:message code="label.delete" /></a></td>
				<td><a href="fill/${inquiry.id}"><spring:message code="label.fill" /></a></td>
				<td><a href="execute/${inquiry.id}"><spring:message code="label.execute" /></a></td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body>
</html>