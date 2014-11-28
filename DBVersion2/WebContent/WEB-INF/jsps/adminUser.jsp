<%@ page language="java" isELIgnored="true"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--<fmt:setBundle basename="resources.messages_${lang}"   var="format" scope="page" />--%>
<body>
<div id="header">
	<h1>Mountain View Community Hospital</h1>
	
</div>
<form:form action="processAdmin.html" method="POST" commandName="userInfo">

 <div id="page">

 	<div id="sidebar">
 	<ul>
			<li>
<table class="tableboxborder" width="100%">

		<tbody>
			<!--<caption class="captionClass">Personal Info</caption>-->
				
				<h2>Search</h2>
				<ul>
				<li>
			<tr>
				<td width="100%" >
				
		
				
					<form:label path="searchName">Search by Last Name: <font color="orangered">*</font></form:label>&nbsp;
					<form:input path="searchName"  maxlength="50" id="searchName"></form:input>
					<input type="submit" name="search" value="Search"/>
                </td>
			</tr>
			</li>
			<li>
			<tr>
				<td width="100%" >
				<form:select path="personType" id="personType" >
					<form:option label="All" value="ALL" />
					<form:option label="Patient" value="PATIENT" />
					<form:option label="Physician" value="PHYSICIAN" />
				</form:select>&nbsp;</td>
				</tr>
				<tr>
				<td> <input type="submit" name="Go" value="Go"/></td>
									
			</tr>
			</li>
			</ul>
			
			
			
			
		</tbody>
	</table>
	</li>
			</ul>
</div>
				<!-- start content -->
				<div id="content">
					<div class="post">
						<h1 class="title">

	<b>Admin Work Area</b></h1>
	
		<div style="position: relative; float: left; margin-left: 2em;">
	<table class="tableboxborder" width="50%" style="margin-top:20px;" border = "1">
	<tbody>
	<tr class="captionRow">
		<th>Select</th>
		<th>Person Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Gender</th>
		<th>Phone</th>
		<th>Email</th>
		<th>Person Type</th>				
	</tr>
	<c:forEach var="apt" items="${userInfo.userList}" varStatus="rowcount">
		<c:choose>
			<c:when test="${rowcount.count mod 2 ==0}">
	<tr class="oddrow">
			</c:when>
			<c:otherwise>
	<tr class="evenrow">	
			</c:otherwise>
		</c:choose>		
		<td width="15%"><form:checkbox path="userList[${rowcount.count-1}].checked" /></td>
		<td width="15%"><c:out value="${apt.personId}"/></td>
		<td width="15%"><c:out value="${apt.fname}"/></td>
		<td width="35%"><c:out value="${apt.lname}"/></td>
		<td><c:out value="${apt.gender}"/></td>
		<td><c:out value="${apt.phone}"/></td>
		<td><c:out value="${apt.email}"/></td>		
		<td width="15%"><c:out value="${apt.personType}"/></td>					
	</tr>
	</c:forEach>
		</tbody>
</table>
<p align="center"></a><input type="submit" name="Delete" value="Delete"/></p>
</div>

	<div style="position: relative; float: left; margin-left: 2em;">
	<h4>Emergency Contact Detail:</h4>
	<table border="1">
	<tr>
	<th>Patient Id</th><th>First Name</th><th>Last Name</th><th>Relation</th><th>Address</th><th>City</th><th>Phone</th><th>Email</th>
	</tr>
	<c:forEach items="${userInfo.userList}" var="i">
		<tr>
		<c:choose>
			<c:when test="${i.personType=='Physician'}">
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
				<td><c:out value="-"/></td>
			</c:when>
			<c:when test="${i.personType=='Patient'}">
				<td><c:out value="${i.personId}"/></td>
				<td><c:out value="${i.emergency.fname}"/></td>
				<td><c:out value="${i.emergency.lname}"/></td>
				<td><c:out value="${i.emergency.relation}"/></td>
				<td><c:out value="${i.emergency.address}"/></td>
				<td><c:out value="${i.emergency.city}"/></td>
				<td><c:out value="${i.emergency.phone}"/></td>
				<td><c:out value="${i.emergency.email}"/></td>
			</c:when>
		</c:choose>
		
		</tr>
	</c:forEach>
	
	</table>
	</div>
	
	<p align="center"">
	<input type="submit" name="register" value="Register New Patient"/>
	<input type="submit" name="update" value="View/Update Patient"/>

</p>


	
</form:form>
</div></div>


</div>
</body>
