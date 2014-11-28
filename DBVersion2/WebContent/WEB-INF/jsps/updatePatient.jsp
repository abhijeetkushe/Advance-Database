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

<form:form method="post" action="update.html" commandName="patient">
<br/>
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Personal Information</h1>
			
<label>Patient Id: </label><form:input path="personId" name="pId" readonly="true"/><br/>
<label>First Name:</label><form:input path="fname" name="fname"/>
<label>Last Name:</label><form:input path="lname" name="lname"/><br/>
<label>Birth Date:</label>
<form:input path="birthdate" name="bd"/><br/>

&nbsp;&nbsp;<label>Gender:</label>&nbsp;&nbsp;<form:radiobutton path="gender" value="M" name="gender"/>Male<form:radiobutton path ="gender" value="F" name="gender"/>Female <br/>
&nbsp;<label>Address:</label>&nbsp;<form:input path="address" name="address"/><br/>
&nbsp;&nbsp;&nbsp;<label>City:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="city" name="city"/>
&nbsp;&nbsp;<label>State:</label>&nbsp;&nbsp;<form:input path="state" name="state"/><br/>
<label>Zip Code:</label>&nbsp;<form:input path="zip" name="zip"/>
<label>Country:</label>&nbsp;<form:input path="country"/><br/>
&nbsp;&nbsp;&nbsp;<label>Phone:</label>&nbsp;&nbsp;<form:input path="phone" name="phone"/>
<label>Email:</label>&nbsp;&nbsp;&nbsp;<form:input path="email" name="email"/><br/>
<br/>
</div>
<div class="post">
			<h1 class="title">
Emergency Contact Information</h1>

<label>Relationship:</label>
<form:select path="Emergency.relation">
<form:option value='Spouse'>Spouse</form:option>
<form:option value='Child'>Child</form:option>
<form:option value="Brother">Brother</form:option>
<form:option value="Sister">Sister</form:option>
<form:option value="Father">Father</form:option>
<form:option value="Mother">Mother</form:option>
<form:option value="Friend">Friend</form:option>
<form:option value='Other Adult'>Other Adult</form:option>
</form:select>
<br/>
<label>First Name:</label>&nbsp;&nbsp;<form:input path="Emergency.fname" />
<label>Last Name:</label><form:input path="Emergency.lname" />
<br/>
&nbsp;&nbsp;&nbsp;<label>Address:&nbsp;&nbsp;&nbsp;</label><form:input path="Emergency.address" />
&nbsp;&nbsp;&nbsp;&nbsp;<label>City:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Emergency.city"/><br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>State:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Emergency.state" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Zip:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Emergency.zip" />
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<label>Phone:</label>&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Emergency.phone" />
&nbsp;&nbsp;&nbsp;&nbsp;<label>Email:</label>&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Emergency.email" />
</div>

<div class="post">
			<h1 class="title">
Benefits Information</h1>


Doctors and pharmacists strongly recommend that you complete the following benefits information.<br/><br/>

<label>Do you have health insurance? </label>
<form:radiobutton path="Insurance.status"  name="insurance" value="Y"/>Yes
<form:radiobutton path="Insurance.status" name="insurance" value="N"/>No
<br/>
<label>Policy Number:</label><form:input path="Insurance.policyNbr" name="policyNbr"/>
<label>Company Name:</label><form:input path="Insurance.company" name="compName"/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<label>Group Id:</label>&nbsp;&nbsp;&nbsp;&nbsp;<form:input path="Insurance.groupId" name="groupId"/>
<br/>
<label>Relationship to Policy Holder:</label><form:select path="Insurance.relation">
<form:option value='Self'>Self</form:option><form:option value='Spouse'>Spouse</form:option><form:option value='Child'>Child</form:option><form:option value='Other Adult'>Other Adult</form:option>
</form:select>
<br/>
</div>
<p align="center">
<a href="adminUser.html"><input type="button" value="  Back  "/></a>
<input type="submit" value="  Update  "/></p>
</div></div>
</form:form>
</body>