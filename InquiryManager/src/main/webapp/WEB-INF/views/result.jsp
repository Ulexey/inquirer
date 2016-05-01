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


<h2>Spring MVC Multiple Row Form Submit example</h2>

<form:form method="post" action="saveSurvey/${survey.id}" commandName="survey" >
    <table>
	<tbody>
    <tr>
        <td><form:input path="fio"></form:input></td>
        <td><form:input path="status"></form:input></td>
        <td><form:input path="start"></form:input></td>
        <td><form:input path="stop"></form:input></td>
    </tr>


    <c:forEach items="${survey.results}" var="result" varStatus="status">
        <tr>
            <td align="center">${status.count}</td>
            <td><form:input path="results[${status.index}].checked" /></td>
            <td>${result.id}</td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>  
<br/>
<input type="submit" value="Save" />
     
</form:form>


</body>
</html>