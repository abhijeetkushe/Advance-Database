<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
<div id="header">
	<h1>Mountain View Community Hospital</h1>
	
</div>

 <div id="page">
				<!-- start content -->
				<div id="content">
					<div class="post">
						<h1 class="title">Registration Successful</h1>
<label>Patient Id:</label>&nbsp;&nbsp;<input type="text" value="${patient.personId}"/>
<br/>
<label>First Name:</label><input type="text" value="${patient.fname}"/>
<label>Last Name:</label><input type="text" value="${patient.lname}"/>
<br/>
&nbsp;<label>User Id:</label>&nbsp;&nbsp; <input type="text" value="${patient.accountBean.userName}"/>
<label>Password:</label><input type="text" value="${patient.accountBean.password}"/>
<br/>
<p align="center"><a href="adminUser.html"><input type="button" value="    OK    "/></a></p>
</div>
</div>
</div>
</body>