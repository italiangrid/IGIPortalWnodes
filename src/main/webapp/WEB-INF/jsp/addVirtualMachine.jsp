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
		$second_div.animate({'background-color': '#ACDFA7'}, 60, function(){ $(this).animate({'background-color': '#f4fdef'}, 1500); })
	}
	
	function openSettings(divName){
		$("#vm_settings_"+divName+"_on").show();
		$("#vm_settings_"+divName+"_off").hide();
	}
	
	function closeSettings(divName){
		$("#vm_settings_"+divName+"_on").hide();
		$("#vm_settings_"+divName+"_off").show();
	}
	
	function submit(formId){
		$("#<portlet:namespace/>t_"+formId).submit();
	}
	var list = new Array();
	function change(divName){
		var i = 0;
		var newlist = new Array();
		var isPresent = false;
		for (i = 0; i < list.length; i++) {
			if (list[i] != divName) {
				newlist.push(list[i]);
			} else {
				isPresent = true;
			}
		}

		if (isPresent == false){
			list.push(divName);
			$("#settings_"+divName).show("slow");
			$("#icon_"+divName).html("<img src='<%=request.getContextPath()%>/images/Delete.png' width='24px'/>");
		}else{
			list = newlist;
			$("#settings_"+divName).hide("slow");
			$("#icon_"+divName).html("<img src='<%=request.getContextPath()%>/images/Add.png' width='24px'/>");
			
		}
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
}

.description:HOVER{
	cursor: pointer;
}

.info{
	width:100%;
	float: left;
}

.icon{
	width: 20%;
	float: right;
	text-align: right;
	padding: 23px 10px 0 0;
}

.clear{
	clear: both;
}

.settings{
	display: none;
	border-color: #ACDFA7;
	border-style: solid;
	border-width: 1px 0 0 0;
}

.col{
	width: 20%;
	padding-left: 20px;
	float: left;
}

.vmName{
	margin-bottom: 10px;
	padding: 5px;
	color: white;
	font-size: 14px;
	font-weight: bold;
	border: solid 1px green;
	border-radius: 5px;
	/*background-color: #ACDFA7;*/
	width: 40%; 
	background-color: green;
	float: left;
}

.vmIcon{
	margin-bottom: 10px;
	padding: 5px;
	width: 30px;
	float:right; 
}

</style>

<div id="containerWnodes">
	<div id="presentationWnodes">Choose a Virtual Machine image</div>
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
			
			<c:forEach var="mp" items="${marketPlace }">
				<div id="mp_${mp.name }" class="mp">
					<c:forEach var="t" items="${mp.tags }">
						<div class="tag">
						<aui:form id="t_${t.name }" name="t_${t.name }" commandName="vm" action="${addUrl}">
							<div id="tag_info_${t.name }" class="description" onclick="change('${t.name }');">
								<div class="vmName">${t.name }</div> <div id="icon_${t.name }"class="vmIcon"><img src="<%=request.getContextPath()%>/images/Add.png" width="24px"/></div>
								<div class="clear"></div>
								<p><strong>OS</strong>: ${t.os } : ${t.osVersion } : ${t.architecture }<br/>
								<strong>Endorser</strong>: ${t.endorser }<br/>
								<strong>Description</strong>: ${t.description }</p>
							</div>
							<div id="settings_${t.name }" class="settings">
								<div class="info">
								
									
									<aui:input type="hidden" name="tag" value="${t.name }"/>
									<div class="col">
										<aui:select name="size" label="Size" onChange="viewSize($(this).val(), 'vm_info_${t.name }')">
											<c:forEach var="size" items="${mp.sizes }">
												<aui:option value="${size.name }#${size.cores }#${size.memory }#${size.disk }">${size.name }</aui:option>
											</c:forEach>
										</aui:select>
										<div id="vm_info_${t.name }">
											CORES: ${mp.sizes[0].cores }<br/>MEMORY: ${mp.sizes[0].memory }<br/>DISK: ${mp.sizes[0].disk }
									</div>
									</div>
									<div class="col">
										<aui:select name="qta" label="How many VM">
											
												<aui:option value="1">1</aui:option>
												<aui:option value="2">2</aui:option>
												<aui:option value="3">3</aui:option>
												<aui:option value="4">4</aui:option>
												
											
										</aui:select>
									</div>
									
									<c:set var="check" value="false"/>
									<c:forEach var="vo" items="${vos }">
										<c:if test="${vo=='gridit' }">
											<c:set var="check" value="true"/>
										</c:if>
									</c:forEach>
									
									<c:if test="${check=='true' }">
										<div class="col">
											<div style="padding-top: 10px;">
												<strong>Selected VO: gridit</strong>
												<aui:input type="hidden" name="vo" value="gridit"></aui:input>
											</div>
										</div>
									
										<div class="icon">
											<aui:button-row>
												<aui:button type="submit" value="Create Virtual Machine" />
											</aui:button-row>
										</div>
									</c:if>
									
									<c:if test="${check=='false' }">
										<div style="margin-top: 10px; float:left;" class="portlet-msg-error">Download your proxy before
												adding a new Virtual Machine.</div>
									</c:if>
									<div class="clear"></div>
									
									
								</div>
								
								<div class="clear"></div>
							</div>
							
						
						</aui:form>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
			<aui:button-row>
			<aui:button type="cancel" value="View Virtual Machine List"
							onClick="location.href='${backUrl}';"></aui:button>
			</aui:button-row>

		</aui:fieldset>
	</div>
</div>