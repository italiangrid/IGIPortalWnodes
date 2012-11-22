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
		<liferay-ui:success key="vm-created" message="vm-created" />
		<liferay-ui:error key="portal-exception" message="exception" />
		<liferay-ui:error key="system-exception" message="exception" />
		<liferay-ui:error key="vm-not-created" message="vm-not-created" />
		<liferay-ui:error key="vo-not-supported" message="vo-not-supported" />

		<portlet:actionURL var="addUrl">
			<portlet:param name="myaction" value="addVirtualMachine" />
		</portlet:actionURL>
		<portlet:renderURL var="backUrl">
			<portlet:param name="myaction" value="showList" />
		</portlet:renderURL>
		<aui:fieldset>
			<aui:form name="addUserInfoForm" commandName="vm" action="${addUrl}">
			
				<aui:column columnWidth="25">
				
					<aui:fieldset label="New Virtual Machine">

						<aui:select name="tag">
							<c:forEach var="tag" items="${tags }">
								<aui:option value="${tag }">${tag }</aui:option>
							</c:forEach>
						</aui:select>

						<aui:select name="size">
							<c:forEach var="size" items="${sizes }">
								<aui:option value="${size }">${size }</aui:option>
							</c:forEach>
						</aui:select>

					</aui:fieldset>
				</aui:column>
				<aui:column columnWidth="25">
				
					<aui:fieldset label="VO">

						<c:choose>
							<c:when test="${fn:length(vos) == 0 }">
								<div class="portlet-msg-error">Download your proxy before
									adding a new Virtual Machine.</div>
							</c:when>
							<c:when test="${fn:length(vos) == 1 }">
								Selected VO: ${vos[0] }
								<aui:input type="hidden" name="vo" value="${vos[0] }"></aui:input>
							</c:when>
							<c:otherwise>
								<aui:select name="vo">
									<c:forEach var="vo" items="${vos }">
										<aui:option value="${vo }">${vo }</aui:option>
									</c:forEach>
								</aui:select>
							</c:otherwise>

						</c:choose>

					</aui:fieldset>

				</aui:column>
				
				<c:if test="${fn:length(vos) > 0 }">
					<aui:button-row>
						<aui:button type="submit" value="Create Virtual Machine" />
						<aui:button type="cancel" value="View List" onClick="location.href='${backUrl}';"></aui:button>
					</aui:button-row>
				</c:if>
				
			</aui:form>

		</aui:fieldset>
	</div>
</div>