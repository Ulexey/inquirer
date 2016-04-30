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

	<table class="data">
		<tr>
			<td>${survey.id}</td>
			<td>${survey.fio}</td>
			<td>${survey.start}</td>
			<td>${survey.stop}</td>
			<td>${survey.status}</td>
		</tr>
	</table>

	<h1><spring:message code="label.survey.run" /></h1>

<table >

 <tbody >
 <c:forEach items="${questions}" var="question">
   <tr>
    <td style="background:#d3dce3"><c:out value="${question.body}" /></td>
   </tr>
   
  <c:if test="${!empty question.answers}">
   <c:forEach items="${question.answers}" var="answer">
   <tr>
    <td><input type="checkbox" name="answer${answer.id}" value="true"> <c:out value="${answer.body}" /></td>
   </tr>
   </c:forEach>
  </c:if>
   
   
 </c:forEach>
 </tbody>
</table>



</body>
</html>