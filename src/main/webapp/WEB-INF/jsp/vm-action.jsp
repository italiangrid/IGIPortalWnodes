<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	VirtualMachine vm = (VirtualMachine) row.getObject();
	String primKey = String.valueOf(vm.getUuid());
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewURL">
		<portlet:param name="myaction" value="viewVirtualMachine" />
		<portlet:param name="uuid" value="<%=primKey%>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message="View Machine" url="${viewURL}" />

	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="deleteVirtualMachine" />
		<portlet:param name="uuid" value="<%=primKey%>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>