<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">

	function viewTags(name){
		$(".mp").hide();
		$("#mp_"+name).show();
	}
	
	function viewSize(size, divName){
		var infos = size.split("#");
		$("#"+divName).html("CORES: "+infos[1]+"<br/>MEMORY: "+infos[2]+"<br/>DISK: "+infos[3]);
		//$("#"+divName).fadeIn(30).css("background-color", 'blue').fadeOut(1000).css("background-color", 'blue');
		
		var $second_div = $("#"+divName); 
		var oldBGColour = $second_div.css('background-color'); 
		$second_div.animate({'background-color': 'yellow'}, 60, function(){ $(this).animate({'background-color': '#f4fdef'}, 1500); })
	}
	
	function openSettings(divName){
		$("#vm_settings_"+divName+"_on").show();
		$("#vm_settings_"+divName+"_off").hide();
	}
	
	function closeSettings(divName){
		$("#vm_settings_"+divName+"_on").hide();
		$("#vm_settings_"+divName+"_off").show();
	}
	
	$(document).ready(function(){$("#mp_WNoDeS").show();})

</script>

<style type="text/css">

.mp{
	display: none;
}
.tag{
	padding: 15px;
	border: solid 1px #ACDFA7;
	background-color: #f4fdef;
	border-radius: 5px;
	margin: 5px;
	float:left;
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
			
			
			<aui:select label="Select Marketplace" name="marketPlace" onClick="viewTags($(this).val())">
				
				<c:forEach var="mp" items="${marketPlace }">
					<aui:option value="${mp.name }">${mp.name }</aui:option>
				</c:forEach>
			</aui:select>
			
			<c:forEach var="mp" items="${marketPlace }">
				<div id="mp_${mp.name }" class="mp">
					<c:forEach var="t" items="${mp.tags }">
						<div class="tag">
						<aui:form name="t_${t.name }" commandName="vm" action="${addUrl}">
							<strong>${t.name } - ${t.architecture }</strong><br/><br/>
							
							<img src="<%=request.getContextPath()%>/images/vm.png"/>
							<div id="vm_info_${t.name }">
								CORES: ${mp.sizes[0].cores }<br/>MEMORY: ${mp.sizes[0].memory }<br/>DISK: ${mp.sizes[0].disk }
							</div>
							<div id= "vm_settings_${t.name }_off">
							<a href="#" onclick="openSettings('${t.name }');">Change setting</a>
							</div>
							<div id= "vm_settings_${t.name }_on" style="display:none;">
							
							<a href="#" onclick="closeSettings('${t.name }');">Close settings</a>
							<aui:input type="hidden" name="tag" value="${t.name }"/>
							
	
							<aui:select name="size" label="Size" onChange="viewSize($(this).val(), 'vm_info_${t.name }')">
								<c:forEach var="size" items="${mp.sizes }">
									<aui:option value="${size.name }#${size.cores }#${size.memory }#${size.disk }">${size.name }</aui:option>
								</c:forEach>
							</aui:select>
							
							<aui:select name="qta" label="Amount">
								
									<aui:option value="1">1</aui:option>
									<aui:option value="2">2</aui:option>
									<aui:option value="3">3</aui:option>
									<aui:option value="4">4</aui:option>
									
								
							</aui:select>
							
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
							</div>
							<c:if test="${fn:length(vos) > 0 }">
							<aui:button-row>
							<aui:button type="submit" value="Create Virtual Machine" />
							</aui:button-row>
							</c:if>
						
						</aui:form>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
			<aui:button-row>
			<aui:button type="cancel" value="View Virtual Machine List"
							onClick="location.href='${backUrl}';"></aui:button>
			</aui:button-row>
			
			<div style="display: none;">
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
						
						<aui:select name="qta" label="Amount">
							
								<aui:option value="1">1</aui:option>
								<aui:option value="2">2</aui:option>
								<aui:option value="3">3</aui:option>
								<aui:option value="4">4</aui:option>
								
							
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
						<aui:button type="cancel" value="View List"
							onClick="location.href='${backUrl}';"></aui:button>
					</aui:button-row>
				</c:if>

			</aui:form>
			</div>

		</aui:fieldset>
	</div>
</div>