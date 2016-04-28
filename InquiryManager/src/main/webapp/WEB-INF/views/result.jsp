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







<h1>Records</h1>
<c:url var="editImgUrl" value="/resources/img/edit.png" />
<c:url var="deleteImgUrl" value="/resources/img/delete.png" />
<c:url var="addUrl" value="/krams/main/record/add" />
<table style="border: 1px solid; width: 100%; text-align:center">
 <thead style="background:#d3dce3">
  <tr>
   <th>Id</th>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Money</th>
  </tr>
 </thead>
 <tbody style="background:#ccc">
 <c:forEach items="${questions}" var="question">
   
  <c:if test="${!empty question.answers}">
   <c:forEach items="${question.answers}" var="answer">
   <tr>
    <td><c:out value="${question.id}" /></td>
    <td><c:out value="${question.body}" /></td>
    <td><c:out value="${answer.id}" /></td>
    <td><c:out value="${answer.body}" /></td>
   </tr>
   </c:forEach>
  </c:if>
   
   
 </c:forEach>
 </tbody>
</table>
 
<c:if test="${empty questions}">
 No records found.
</c:if>
 











</body>
</html>