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

<br/>
<a href="<c:url value="/index" />"> 
	<spring:message code="label.back" />
</a><br/>
	<h2>
		<spring:message code="label.inquiries" />
	</h2>
	<form:form method="post" action="addInquiry" commandName="inquiry">
		<table>
			<tr>
				<td><form:label path="body">
						<spring:message code="label.bodyinquiry" />
					</form:label></td>
			</tr>
			<tr>
				<td><form:textarea path="body" rows="5" cols="30"/></td>
			</tr>
			<tr>
				<td><input type="submit"
					value="<spring:message code="label.addinquiry"/>" /></td>
					
			</tr>
			
		</table>
	</form:form>

	<h3>
		<spring:message code="label.listInquiries" />
	</h3>
	<c:if test="${!empty inquiryList}">
		<table class="data">
			<c:forEach items="${inquiryList}" var="inquiry">
				<tr>
					<td>  <textarea rows="5" cols="30" readonly="readonly"  style="background:#808080">${inquiry.body}</textarea>      </td>
					<td><a href="inquiry/delete/${inquiry.id}"><spring:message
								code="label.delete" /></a><br/>
					<a href="inquiry/${inquiry.id}"><spring:message
								code="label.edit" /></a><br/>
					<a href="inquiry/${inquiry.id}/survey"><spring:message
								code="label.execute" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>