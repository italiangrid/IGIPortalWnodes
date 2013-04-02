<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>

<script type="text/javascript">

	function viewTags(name){
		$(".mp").hide();
		$("#mp_"+name).show();
	}
	
	function viewSize(size, divName){
		var infos = size.split("#");
		$("#"+divName).html("CORES: "+infos[1]+"<br/>MEMORY: "+infos[2]+"<br/>DISK: "+infos[3]);
		
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
			$("#icon_"+divName).html("<img src='<%=request.getContextPath()%>/images/minus.png' width='24px'/>");
		}else{
			list = newlist;
			$("#settings_"+divName).hide("slow");
			$("#icon_"+divName).html("<img src='<%=request.getContextPath()%>/images/Add.png' width='24px'/>");
			
		}
	}
	
	function buildHtmlDisplay( aData )
	{

	        var display = "<div class=\"tag\">" 
						+"<div id=\"tag_info_"+aData[9]+"\" class=\"description\" onclick=\"change('"+aData[9]+"');\">"
						+"<div class=\"vmName\"><img src=\"<%=request.getContextPath()%>/images/cd.png\" width=\"24px\" /> "+aData[0]+"</div> <div id=\"icon_"+aData[9]+"\" class=\"vmIcon\"><img src=\"<%=request.getContextPath()%>/images/Add.png\" width=\"24px\"/></div>"
						+"<div class=\"clear\"></div>"
						+"<p><strong>OS</strong>: "+aData[1]+" : "+aData[2]+" : "+aData[3]+"<br/>"
						+"<strong>Endorser</strong>: "+aData[4]+"<br/>"
						+"<strong>Description</strong>: "+aData[5]+"</p>"
						+"</div>"
						+aData[7]
						+"</div>";

	        return display;
	}
	
	$(document).ready(function(){
			$("#mp_WNoDeS").show();
			$('#search_table').dataTable({
				"aoColumnDefs": [
				                 { "bSortable": false, 
		                                   "fnRender": function ( o, val ) {
		                                                   return buildHtmlDisplay( o.aData );
		                                               },
		                                   "aTargets": [ 0 ] },
				                 { "bSearchable": true, "bVisible": false, "aTargets": [1] }, //os
				                 { "bSearchable": true, "bVisible": false, "aTargets": [2] }, //osversion
				                 { "bSearchable": true, "bVisible": false, "aTargets": [3] }, //arch
				                 { "bSearchable": true, "bVisible": false, "aTargets": [4] }, //endorser
				                 { "bSearchable": true, "bVisible": false, "aTargets": [5] }, //description
				                 { "bSearchable": false, "bVisible": false, "aTargets": [6] }, //identifier
				                 { "bSearchable": false, "bVisible": false, "aTargets": [7] }, //div
				                 { "bSearchable": false, "bVisible": false, "aTargets": [8] }, //div
				                 { "bSearchable": false, "bVisible": false, "aTargets": [9] } //divName
				                 ]
				                 
			});
			$("#search_table").css("width", "100%");
			$(".hide").show();
		})

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
	float: left;
	width: 100%;
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

.vmName img{
	margin-bottom: -6px;
}

.vmIcon{
	margin-bottom: 10px;
	padding: 5px;
	width: 30px;
	float:right; 
}

#search_table_wrapper{
	width: 100%;
}

.sorting_disabled{
	display: none;
}

#search_table_length{
	float: left;
	margin-left: 5px;
}

#search_table_filter{
	float: right;
	margin-right: 5px;
}

#search_table_info{
	float: left;
	margin-left: 5px;
}

#search_table_paginate{
	float: right;
	margin-right: 5px;
}

#search_table_paginate a{
	padding-left: 5px;
}

.hide{
	display: none;
}


</style>

<div id="containerWnodes">
	<div id="presentationWnodes">Choose an Image</div>
	<div id="contentWnodes">
		<liferay-ui:success key="vm-created" message="vm-created" />
		<liferay-ui:error key="portal-exception" message="exception" />
		<liferay-ui:error key="system-exception" message="exception" />
		<liferay-ui:error key="vm-not-created" message="vm-not-created" />
		<liferay-ui:error key="vo-not-supported" message="vo-not-supported" />
		<liferay-ui:error key="no-metadata-retrieved" message="no-metadata-retrieved" />


		<portlet:actionURL var="addUrl">
			<portlet:param name="myaction" value="addVirtualMachine" />
		</portlet:actionURL>
		<portlet:renderURL var="backUrl">
			<portlet:param name="myaction" value="showList" />
		</portlet:renderURL>
		<aui:fieldset>
		
			<c:if test="${marketPlace!=null }">			
			
			<table id="search_table" class="search hide">
			    <thead>
			        <tr>
			            <th>tag</th>
			            <th>os</th>
			            <th>os-version</th>
			            <th>arch</th>
			            <th>endorser</th>
			            <th>description</th>
			            <th>identifier</th>
			            <th>addDiv</th>
			            <th>addUrl</th>
			            <th>divName</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<c:forEach var="t" items="${marketPlace.tags }">
			    		<tr>
			    			<td>${t.name }</td>
			    			<td>${t.os }</td>
			    			<td>${t.osVersion }</td>
			    			<td>${t.architecture }</td>
			    			<td>${t.endorser }</td>
			    			<td>${t.description }</td>
			    			<td>${t.identifier }</td>
			    			<td>
								<div id="settings_${fn:replace(t.name,'.','_') }" class="settings">
											<div class="info">
											
											<aui:form id="t_${fn:replace(t.name,'.','_') }" name="t_${fn:replace(t.name,'.','_') }" commandName="vm" action="${addUrl}">	
												<aui:input type="hidden" name="tag" value="${t.name }"/>
												<aui:input type="hidden" name="identifier" value="${t.identifier }"/>
												<aui:input type="hidden" name="endorser" value="${t.endorser }"/>
												<div class="col">
													<aui:select name="size" label="Size" onChange="viewSize($(this).val(), 'vm_info_${fn:replace(t.name,'.','_') }')">
														<c:forEach var="resourceProvider" items="${marketPlace.resourceProviders }">
															<c:if test="${(resourceProvider.name==t.resourceProvider) && (resourceProvider.platform==t.platform) }">
																<c:forEach var="size" items="${resourceProvider.sizes }">
																	<aui:option value="${size.name }#${size.cores }#${size.memory }#${size.disk }">${size.name }</aui:option>
																</c:forEach>
															</c:if>
														</c:forEach>
													</aui:select>
													<div id="vm_info_${fn:replace(t.name,'.','_') }">
														<c:forEach var="resourceProvider" items="${marketPlace.resourceProviders }">
															<c:if test="${(resourceProvider.name==t.resourceProvider) && (resourceProvider.platform==t.platform)  }">
																CORES: ${resourceProvider.sizes[0].cores }<br/>MEMORY: ${resourceProvider.sizes[0].memory }<br/>DISK: ${resourceProvider.sizes[0].disk }
															</c:if>
														</c:forEach>
													</div>
												</div>
												<div class="col">
													<aui:select name="qta" label="How many Instances">
														
															<aui:option value="1">1</aui:option>
															<aui:option value="2">2</aui:option>
															<aui:option value="3">3</aui:option>
															<aui:option value="4">4</aui:option>
															
														
													</aui:select>
												</div>
												
												<c:set var="check" value="false"/>
												<c:forEach var="vo" items="${vos }">
													<c:if test="${vo==cloudVo }">
														<c:set var="check" value="true"/>
													</c:if>
												</c:forEach>
												
												<c:if test="${check=='true' }">
													<div class="col">
														<div style="padding-top: 10px;">
															<strong>Selected VO:</strong> <br/>${cloudVo }
															<aui:input type="hidden" name="vo" value="${cloudVo }"></aui:input>
														</div>
													</div>
												
													<div class="icon">
														<aui:button-row>
															<aui:button type="submit" value="Create Instance" />
														</aui:button-row>
													</div>
												</c:if>
												
												<c:if test="${check=='false' }">
													<div style="margin-top: 10px; float:left;" class="portlet-msg-error">Download your proxy before
															adding a new Virtual Machine.</div>
												</c:if>
												<div class="clear"></div>
												
												</aui:form>
											</div>
											
											<div class="clear"></div>
										</div>
							</td>
							<td>${addUrl }</td>
							<td>${fn:replace(t.name,'.','_') }</td>
			       		</tr>
			    	</c:forEach>
			     </tbody>
			</table>
			</c:if>
			<aui:button-row style="padding-top: 15px;">
			<aui:button type="cancel" value="View Instances List"
							onClick="location.href='${backUrl}';"></aui:button>
			</aui:button-row>

		</aui:fieldset>
	</div>
	
	
	
	
	
	
	
	
</div>