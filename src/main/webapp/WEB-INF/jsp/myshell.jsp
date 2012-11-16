<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style>
<!--
CSS temporaneo da spostare poi in main css
-->#containerWnodes {
	box-shadow: 10px 10px 5px #888;
	background-color: #e4e4e4;
	border: 1px;
	border-style: solid;
	border-color: #c1c1c1;
	border-radius: 5px;
	padding: 5px;
	margin-right: 9px;
}

#presentationWnodes {
	color: black;
	font-size: 120%;
	font-weight: bold;
	border-bottom: 1px solid #CCCCCC;
	display: block;
	position: absolute;
	width: 90%;
	margin-bottom: 15px;
	padding: 10px 0 0 10px;
}

#contentWnodes {
	margin-top: 45px;
}
</style>

<div id="containerWnodes">
	<div id="presentationWnodes">Hi ${userInfo.firstName }
		${userInfo.lastName }</div>
	<div id="contentWnodes">
		<iframe
			src="https://flyback.cnaf.infn.it:8443/webtty/webtty.html?user=<%=((User) request.getAttribute(WebKeys.USER)).getUserId()%>&host=${host }"
			frameborder="no" width="800" height="600"></iframe>
			
		<portlet:renderURL var="backUrl">
			<portlet:param name="myaction" value="showList" />
		</portlet:renderURL>
		
		<aui:form>
			<aui:button type="cancel" value="Back" onClick="location.href='${backUrl}';"></aui:button>
		</aui:form>
	</div>
</div>