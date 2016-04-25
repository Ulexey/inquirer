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
<a href="<c:url value="../../${question.id}" />"> 
	<spring:message code="label.back" />
</a><br/>
<h2><spring:message code="label.title" /></h2>

<h3><spring:message code="label.answer" /></h3>

<c:url var="saveUrl" value="${answer.id}/edit" />
<form:form modelAttribute="answer" method="POST" action="${saveUrl}">
 <table>
  <tr>
   <td><form:label path="id">111111111111</form:label></td>
   <td><form:input path="id" disabled="true"/></td>
  </tr>
  
 
  <tr>
    <td><form:label path="id">222222222</form:label></td>
   <td><form:textarea path="body" rows="5"/></td>
  </tr>
 
  <tr>
   <td><form:label path="valid">3333333</form:label></td>
   <td><form:input path="valid"/></td>
  </tr>
 </table>
  
 <input type="submit" value="Save" />
</form:form>
 
</body>



</html>