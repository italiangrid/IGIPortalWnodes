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

<jsp:useBean id="userInfo"
	type="it.italiangrid.portal.dbapi.domain.UserInfo" scope="request" />
<jsp:useBean id="virtualMachines"
	type="java.util.List<it.italiangrid.wnodes.model.VirtualMachine>"
	scope="request" />

<div id="containerWnodes">
	<div id="presentationWnodes">Hi ${userInfo.firstName }
		${userInfo.lastName }</div>
	<div id="contentWnodes">

		<liferay-ui:success key="vm-created" message="vm-created" />
		<liferay-ui:success key="vm-deleted" message="vm-deleted" />
		<liferay-ui:error key="vm-not-deleted" message="vm-not-deleted" />

		<c:if test="${fn:length(virtualMachines)==0 }">
			<div class="portlet-msg-error">Empty List</div>
		</c:if>

		<br/>

		<liferay-ui:search-container emptyResultsMessage="No Virtual Machines"
			delta="20">
			<liferay-ui:search-container-results>
				
				
			<c:set var="results" value="${virtualMachines }"/>
			<c:set var="total" value="${fn:length(virtualMachines) }"/>
			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="it.italiangrid.wnodes.model.VirtualMachine"
				keyProperty="uuid" modelVar="vms">
				<liferay-ui:search-container-column-text name="Hostname"
					property="hostname" />
				<liferay-ui:search-container-column-text name="Archicteture"
					property="architecture" />
				<liferay-ui:search-container-column-text name="Cores"
					property="cores" />
				<liferay-ui:search-container-column-text name="Memory"
					property="memory" />
				<liferay-ui:search-container-column-text name="Status"
					property="status" />
				<c:if test="${vms.status=='ACTIVE' }">
					<c:if test="${vms.hostname!=null }">
						<liferay-ui:search-container-column-jsp
							path="/WEB-INF/jsp/vm-action.jsp" align="right" />
					</c:if>
					<c:if test="${vms.hostname==null }">
						<liferay-ui:search-container-column-jsp
							path="/WEB-INF/jsp/vm-del-action.jsp" align="right" />
					</c:if>
				</c:if>
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<br/>
		<portlet:renderURL var="addUrl">
			<portlet:param name="myaction" value="showAddVirtualMachine" />
		</portlet:renderURL>
		
		<aui:form name="goToAddForm" action="${addUrl}">
			<aui:button type="submit" value="Add Virtual Machine" />		
		</aui:form>

	</div>
</div>