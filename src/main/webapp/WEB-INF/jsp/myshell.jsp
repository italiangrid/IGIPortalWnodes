<%@ include file="/WEB-INF/jsp/init.jsp"%>

<div id="containerWnodes">
	<div id="presentationWnodes">SSH ${host }</div>
	<div id="contentWnodes">
	
		<script type="text/javascript">
		
			create('${host }', '<%=((User) request.getAttribute(WebKeys.USER)).getUserId()%>');
		</script>
		<div style="width: 100%; height: 600px; margin-bottom: 15px;">
		    <div id="gateone"></div>
		</div>
		<portlet:renderURL var="backUrl">
			<portlet:param name="myaction" value="showList" />
		</portlet:renderURL>
		
		<aui:form>
			<aui:button type="cancel" value="Back" onClick="closeSession(); location.href='${backUrl}';"></aui:button>
		</aui:form>
	</div>
</div>