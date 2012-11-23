<%@ include file="/WEB-INF/jsp/init.jsp"%>

<div id="containerWnodes">
	<div id="presentationWnodes">Hi ${userInfo.firstName }
		${userInfo.lastName }</div>
	<div id="contentWnodes">
	
		<!-- <iframe
			src="https://flyback.cnaf.infn.it:8443/webtty/webtty.html?user=<%=((User) request.getAttribute(WebKeys.USER)).getUserId()%>&host=${host }"
			frameborder="no" width="100%" height="600"></iframe> -->
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