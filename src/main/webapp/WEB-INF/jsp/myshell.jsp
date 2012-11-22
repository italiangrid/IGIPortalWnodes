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

<script type="text/javascript"
src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js">
</script>

<script type="text/javascript"
src="https://flyback.cnaf.infn.it:22443/static/gateone.js">
</script>

<script type="text/javascript">

	function closeSession(){
		
		
	}
	
	function create(){
		alert("${host}");
		GateOne.init({
			url: "https://flyback.cnaf.infn.it:22443/",
			fontSize: '120%',
			fillContainer: false, 
			style: {'width': '97%', 'height': '600px', 'margin':'50px 10px 0px 10px' },
			autoConnectURL: "ssh://dmichelotto@${host }/?identities=<%=((User) request.getAttribute(WebKeys.USER)).getUserId()%>/id_rsa",
			});
		
	}


	$(document).ready(function() {
		
	});


</script>

<div id="containerWnodes">
	<div id="presentationWnodes">Hi ${userInfo.firstName }
		${userInfo.lastName }</div>
	<div id="contentWnodes">
	
		<!-- <iframe
			src="https://flyback.cnaf.infn.it:8443/webtty/webtty.html?user=<%=((User) request.getAttribute(WebKeys.USER)).getUserId()%>&host=${host }"
			frameborder="no" width="100%" height="600"></iframe> -->
		<script type="text/javascript">
		
			create();
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