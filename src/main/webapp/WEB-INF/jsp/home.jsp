<%@ include file="/WEB-INF/jsp/init.jsp"%>

<jsp:useBean id="userInfo"
	type="it.italiangrid.portal.dbapi.domain.UserInfo" scope="request" />
<jsp:useBean id="virtualMachines"
	type="java.util.List<it.italiangrid.wnodes.model.VirtualMachine>"
	scope="request" />

<div id="containerWnodes">
	<div id="presentationWnodes">Hi ${userInfo.firstName }
		${userInfo.lastName }</div>
	<div id="contentWnodes">

		<liferay-ui:success key="added-ssh-key" message="added-ssh-key" />
		<liferay-ui:success key="vm-created" message="vm-created" />
		<liferay-ui:success key="vm-deleted" message="vm-deleted" />
		<liferay-ui:error key="vm-not-deleted" message="vm-not-deleted" />

		<c:if test="${fn:length(virtualMachines)==0 }">
			<div class="portlet-msg-error">Empty List</div>
		</c:if>

		<br />
		<portlet:renderURL var="addUrl">
			<portlet:param name="myaction" value="showAddVirtualMachine" />
		</portlet:renderURL>

		<aui:form name="goToAddForm" action="${addUrl}">
			<aui:button type="submit" value="Add Virtual Machine" />
		</aui:form>
		<br />
		<portlet:actionURL var="deleteUrl">
			<portlet:param name="myaction" value="deleteMultipleVirtualMachine" />
		</portlet:actionURL>


		<form name="goToAddForm" action="${deleteUrl}" method="POST">

			<liferay-ui:search-container
				emptyResultsMessage="No Virtual Machines" delta="10">
				<liferay-ui:search-container-results>

					<%
						results = ListUtil.subList(virtualMachines,
										searchContainer.getStart(),
										searchContainer.getEnd());
						total = virtualMachines.size();

						pageContext.setAttribute("results", results);
						pageContext.setAttribute("total", total);
					%>

				</liferay-ui:search-container-results>
				<liferay-ui:search-container-row
					className="it.italiangrid.wnodes.model.VirtualMachine"
					keyProperty="uuid" modelVar="vms">
					<liferay-ui:search-container-column-text name="Del">
						<c:if test="${(vms.status=='ACTIVE') }">
							<input name="vmToDel" type="checkbox"
								value="${vms.uuid }"
								onchange="viewOrHideDeleteButton('${vms.uuid }');"></input>
						</c:if>
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="Hostname">
						<c:choose>
							<c:when test="${vms.hostname!='null' }">
								<portlet:renderURL var="viewURL">
									<portlet:param name="myaction" value="viewVirtualMachine" />
									<portlet:param name="uuid" value="${vms.uuid }" />
								</portlet:renderURL>
								<a href="${viewURL }">${vms.hostname }</a>
							</c:when>
							<c:otherwise>
								${vms.hostname} 
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="Archicteture"
						property="architecture" />
					<liferay-ui:search-container-column-text name="Memory"
						property="memory" />
					<liferay-ui:search-container-column-text name="Cores"
						property="cores" />
					<liferay-ui:search-container-column-text name="Status"
						property="status" />
					
				</liferay-ui:search-container-row>
				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
			<div id="deleteButton" style="display: none;">
				<aui:button-row>
					<aui:button type="submit" value="Delete Selected VM"
						onClick="return confirm('Are you sure you want to delete these?');" />
				</aui:button-row>
			</div>
		</form>

	</div>
</div>