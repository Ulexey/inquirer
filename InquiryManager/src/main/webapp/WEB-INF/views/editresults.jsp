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

	<h1><spring:message code="label.survey.run" /></h1>


<h2>Spring MVC Multiple Row Form Submit example</h2>

<form:form method="post" action="savve" commandName="resultForm" >
    <table>
	<tbody>

    <c:forEach items="${resultForm.questions}" var="question" varStatus="q">
            <tr><td>${question.body}</td></tr>
    <c:forEach items="${question.results}" var="result" varStatus="r">
            <tr><td><form:hidden path="questions[${q.index}].results[${r.index}].id" /></td></tr>
            <tr><td><form:hidden path="questions[${q.index}].results[${r.index}].survey.id" /></td></tr>
            <tr><td><form:hidden path="questions[${q.index}].results[${r.index}].answer.id" /></td></tr>
            <tr><td><form:input path="questions[${q.index}].results[${r.index}].checked" /></td>
            <td>${result.answer.body}</td></tr>
    </c:forEach>
    </c:forEach>
    </tbody>
</table>  
<br/>
<input type="submit" value="Save" />
     
</form:form>


</body>
</html>