<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Directory</title>
<script type="text/javascript">
function setPId(){
    var radio = document.getElementsByName("patientId");
    var value;
    for(var i=0;i<radio.length;i++){
   if(radio[i].checked == true)
       value = radio[i].value;
        }
    document.getElementById("sPId").value=value;
    document.getElementById("ssPId").value=value;
}
</script>
</head>
<body>
<h2>Patient Directory</h2>-->
<form action="searchPatient.html">
<label>Search Patient By Last Name: </label>
<input type="text" id="value" name="value"/>
<input type="submit" value="Search"/><a href="personDirectory.html"><input type="button" value="Refresh Table"/></a>
</form>
<br/>
<div style="position: relative; float: left;">
<h4>Personal Detail:</h4>
<table border="1">
<tr>
<th>Select</th><th>Patient Id</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Phone</th><th>Email</th>
</tr>
<c:forEach items="${patientList}" var="i" varStatus="count">
<tr>
<td><form:checkbox path="userList[${rowcount.count-1}].checked" /></td>
<td>${i.patientId}</td>
<td>${i.fname}</td>
<td>${i.lname}</td>
<td>${i.gender}</td>
<td>${i.phone}</td>
<td>${i.email}</td>
</tr>
</c:forEach>
</table>
</div>

<div style="position: relative; float: left; margin-left: 2em;">
<h4>Emergency Contact Detail:</h4>
<table border="1">
<tr>
<th>Patient Id</th><th>First Name</th><th>Last Name</th><th>Relation</th><th>Address</th><th>City</th><th>Phone</th><th>Email</th>
</tr>
<c:forEach items="${patientList}" var="i">
<tr>
<td>${i.emergency.patientId}</td>
<td>${i.emergency.fname}</td>
<td>${i.emergency.lname}</td>
<td>${i.emergency.relation}</td>
<td>${i.emergency.address}</td>
<td>${i.emergency.city}</td>
<td>${i.emergency.phone}</td>
<td>${i.emergency.email}</td>
</tr>
</c:forEach>
</table>
</div>

<div style="position: relative; float: left;">
<form action="deletePatient.html">
<a href="registerNewPatient.html"><input type="button" value="Register New Patient"/></a>
<input type="hidden" name="patientId" id="sPId"/>
<input type="submit" value="Delete Patient Record"/>
</form>
</div>

<div style="position: relative; float: left;">
<form action="updatePatient.html">
<input type="hidden" name="patientId" id="ssPId"/>
<input type="submit" value="View/Update Patient"/>
</form>
</div>
</body>
</html>