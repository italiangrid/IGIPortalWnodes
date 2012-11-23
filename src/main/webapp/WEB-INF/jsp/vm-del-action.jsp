<%@ include file="/WEB-INF/jsp/init.jsp"%>


<%
	ResultRow row = (ResultRow) request
			.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	VirtualMachine vm = (VirtualMachine) row.getObject();
	String primKey = String.valueOf(vm.getUuid());
%>

<liferay-ui:icon-menu>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="myaction" value="deleteVirtualMachine" />
		<portlet:param name="uuid" value="<%=primKey%>" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteURL}" />

</liferay-ui:icon-menu>