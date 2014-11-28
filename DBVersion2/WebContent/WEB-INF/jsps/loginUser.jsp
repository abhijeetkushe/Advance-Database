<%@ page language="java" isELIgnored="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
<div id="header">
	<h1>Mountain View Community Hospital</h1>
	
</div>

 <div id="page">
				<!-- start content -->
				<div id="content">
					<div class="post">
						<h1 class="title">
	<form:form action="login.html" method="POST" commandName="userAccount">
		
               
                <b>Login Information</b></h1>
                <table border="0" width="474" style="margin-top: 10px; margin-bottom: 10px;margin-left: 10px">
                    <tbody><tr>
                        <td nowrap="nowrap">
                            <form:label id="userName" path="userName">User Name: <font color="orangered">*</font></form:label>
                        </td>
                        <td nowrap="nowrap">
                            <form:input path="userName"  maxlength="50" id="userName" cssClass="text"></form:input>
                         </td>
                    </tr>
                    <tr>
                        <td nowrap="nowrap">
                            <form:label path="password">
                                Password: <font color="orangered">*</font></form:label></td>
                        <td nowrap="nowrap">
                            <form:password path="password" id="password" maxlength="50" cssClass="text" value=""/></td>
                    </tr>
                    <tr>
						<td nowrap="nowrap" style="padding:0px !important;">
							<form:label path="personType">
                                User Type: <font color="orangered">*</font></form:label></td>
						</td>
						<td>
						
						<form:select path="personType" id="personType" cssClass="selectionAlt" cssStyle="width: 100px;" >
							<form:option label="Patient" value="PATIENT"/>
							<form:option label="Physician" value="PHYSICIAN"/>
							<form:option label="Admin" value="ADMIN"/>
						</form:select>
						</td>
					</tr>
                    <tr>
						<td nowrap="nowrap" style="padding:0px !important;">
							<input type="submit" value="Submit" value="Submit"/>
						</td>
						<td nowrap="nowrap" style="padding:0px !important;">
							<input type="reset" value="Reset"  value="Cancel"/>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" style="height: 18px"><a href="registerNewPatient.html" id="linkSignIn" class="altlink">New User? Click Here to Register</a></td>
					</tr>
					<c:if test="${requestScope.invalidLogin}">
						<font color="orangered"><c:out value="Invalid User Name/ Password"/></font>
					</c:if>         	          
                </tbody></table>
        
	</form:form>
	</div></div></div>
</body>