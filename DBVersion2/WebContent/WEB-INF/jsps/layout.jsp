<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="header" ignore="true" />
<body>
	<h3 align="center"><tiles:insertAttribute name="pageHeader" ignore="true"/></h3>
	<tiles:insertAttribute name="body" />
</body>	
<tiles:insertAttribute name="footer"/>