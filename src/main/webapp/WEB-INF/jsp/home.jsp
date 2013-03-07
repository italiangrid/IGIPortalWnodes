<%@ include file="/WEB-INF/jsp/init.jsp"%>

<script type="text/javascript">
<!--
	//-->
	
	(function ($) {

		/**********************************
		* CUSTOMIZE THE DEFAULT SETTINGS
		* Ex:
		* var _settings = {
		* 	id: 'modal',
		* 	src: function(sender){
		*		return jQuery(sender).attr('href');
		*	},
		* 	width: 800,
		* 	height: 600
		* }
		**********************************/
		var _settings2 = {
			width: 800, // Use this value if not set in CSS or HTML
			height: 600, // Use this value if not set in CSS or HTML
			overlayOpacity: .85, // Use this value if not set in CSS or HTML
			id: 'modal',
			fadeInSpeed: 0,
			fadeOutSpeed: 0
		};

		/**********************************
		* DO NOT CUSTOMIZE BELOW THIS LINE
		**********************************/
		$.modal2 = function (options) {
			return _modal2(this, options);
		};
		$.modal2.open = function () {
			_modal2.open();
		};
		$.modal2.close = function () {
			_modal2.close();
		};
		$.fn.modal2 = function (options) {
			return _modal2(this, options);
		};
		_modal2 = function (sender, params) {
			this.options = {
				parent: null,
				overlayOpacity: null,
				id: null,
				content: null,
				width: null,
				height: null,
				message: false,
				modalClassName: null,
				imageClassName: null,
				closeClassName: null,
				overlayClassName: null,
				src: null
			};
			this.options = $.extend({}, options, _defaults2);
			this.options = $.extend({}, options, _settings2);
			this.options = $.extend({}, options, params);
			this.close = function () {
				jQuery('.' + options.modalClassName + ', .' + options.overlayClassName).fadeOut(_settings2.fadeOutSpeed, function () { jQuery(this).unbind().remove(); });
			};
			this.open = function () {
				if (typeof options.src == 'function') {
					options.src = options.src(sender);
				} else {
					options.src = options.src || _defaults2.src(sender);
				}

				var fileExt = /^.+\.((jpg)|(gif)|(jpeg)|(png)|(jpg))$/i;
				var contentHTML = '';
				if (fileExt.test(options.src)) {
					contentHTML = '<div class="' + options.imageClassName + '"><img src="' + options.src + '"/></div>';

				} else {
					contentHTML = '<iframe width="' + options.width + '" height="' + options.height + '" frameborder="0" scrolling="no" allowtransparency="true" src="' + options.src + '"></iframe>';
				}
				options.content = options.content || contentHTML;

				if (jQuery('.' + options.modalClassName).length && jQuery('.' + options.overlayClassName).length) {
					jQuery('.' + options.modalClassName).html(options.content);
				} else {
					$overlay = jQuery((_isIE62()) ? '<iframe src="BLOCKED SCRIPT\'<html></html>\';" scrolling="no" frameborder="0" class="' + options.overlayClassName + '"></iframe><div class="' + options.overlayClassName + '"></div>' : '<div class="' + options.overlayClassName + '"></div>');
					$overlay.hide().appendTo(options.parent);

					$modal = jQuery('<div id="' + options.id + '" class="' + options.modalClassName + '" style="width:' + options.width + 'px; height:' + options.height + 'px; margin-top:-' + (options.height / 2) + 'px; margin-left:-' + (options.width / 2) + 'px;">' + options.content + '</div>');
					$modal.hide().appendTo(options.parent);

					$close = jQuery('<a class="' + options.closeClassName + '"></a>');
					$close.appendTo($modal);

					var overlayOpacity = _getOpacity($overlay.not('iframe')) || options.overlayOpacity;
					$overlay.fadeTo(0, 0).show().not('iframe').fadeTo(_settings2.fadeInSpeed, overlayOpacity);
					$modal.fadeIn(_settings2.fadeInSpeed);
					
					//alert(options.message)
					if(options.message==false){
					//$close.click(function () { jQuery.modal().close(); location.href='https://halfback.cnaf.infn.it/casshib/shib/app4/login?service=https%3A%2F%2Fgridlab04.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10671';});
					$close.click(function () { jQuery.modal().close(); location.href='https://halfback.cnaf.infn.it/casshib/shib/app1/login?service=https%3A%2F%2Fflyback.cnaf.infn.it%2Fc%2Fportal%2Flogin%3Fp_l_id%3D10669';});
					}else{
						$close.click(function () { window.location.href=window.location.href; });
						$overlay.click(function () { window.location.href=window.location.href; });
					}
					
				}
			};
			return this;
		};
		_isIE62 = function () {
			if (document.all && document.getElementById) {
				if (document.compatMode && !window.XMLHttpRequest) {
					return true;
				}
			}
			return false;
		};
		_getOpacity2 = function (sender) {
			$sender = jQuery(sender);
			opacity = $sender.css('opacity');
			filter = $sender.css('filter');

			if (filter.indexOf("opacity=") >= 0) {
				return parseFloat(filter.match(/opacity=([^)]*)/)[1]) / 100;
			}
			else if (opacity != '') {
				return opacity;
			}
			return '';
		};
		_defaults2 = {
			parent: 'body',
			overlayOpacity: 85,
			id: 'modal',
			content: null,
			width: 800,
			height: 600,
			modalClassName: 'modal-window',
			imageClassName: 'modal-image',
			closeClassName: 'close-window',
			overlayClassName: 'modal-overlay',
			src: function (sender) {
				return jQuery(sender).attr('href');
			}
		};
	})(jQuery);

	
</script>

<style type="text/css">

.modal-overlay {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
	background: url(/IGIPortalWnodes-0.0.1/images/overlay2.png) repeat;
	opacity: .85;
	filter: alpha(opacity=85);
	z-index: 101;
}
.modal-window {
	position: fixed;
	top: 50%;
	left: 50%;
	margin: 0;
	padding: 0;
	z-index: 102;
	background: #fff;
	border: solid 8px #000;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
}
.close-window {
	position: absolute;
	width: 47px;
	height: 47px;
	right: -23px;
	top: -23px;
	background: transparent url(/IGIPortalWnodes-0.0.1/images/close-button2.png) no-repeat scroll right top;
	text-indent: -99999px;
	overflow: hidden;
	cursor: pointer;
}

.rightAlign {
	float: right;
	margin-bottom: 15px;
}
</style>



<div id="containerWnodes">
	<div id="presentationWnodes">My Instances</div>
	<div id="contentWnodes">

		<liferay-ui:success key="added-ssh-key" message="added-ssh-key" />
		<liferay-ui:success key="vm-created" message="vm-created" />
		<liferay-ui:success key="vm-deleted" message="vm-deleted" />
		<liferay-ui:error key="vm-not-deleted" message="vm-not-deleted" />

		<aui:fieldset>
			<c:if test="${keyPairExist }">
			<aui:column columnWidth="50">
				<portlet:renderURL var="addUrl">
					<portlet:param name="myaction" value="showAddVirtualMachine" />
				</portlet:renderURL>
				
				<aui:form name="goToAddForm" action="${addUrl}">
				
					<aui:button type="submit" value="Create new Instance" />
				
				</aui:form>
				
			</aui:column>
			<aui:column columnWidth="50">
				<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="keyUrl">
					<portlet:param name="myaction" value="showKeyManagement" />
				</portlet:renderURL>
		 		<aui:button cssClass="rightAlign" type="button" value="Manage Key Pair" onclick="$(this).modal2({width:600, height:450, message:true, src: '${keyUrl }'}).open(); return false;"/>
	 		</aui:column>
	 		</c:if>
	 		<c:if test="${!keyPairExist }">
	 			<aui:column columnWidth="100">
				<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="keyUrl">
					<portlet:param name="myaction" value="showKeyManagement" />
				</portlet:renderURL>
		 		<aui:button cssClass="rightAlign" type="button" value="Manage Key Pair" onclick="$(this).modal2({width:600, height:450, message:true, src: '${keyUrl }'}).open(); return false;"/>
	 		</aui:column>
	 		</c:if>
 		</aui:fieldset>
 		
		<c:if test="${keyPairExist }">
			<jsp:useBean id="userInfo"
				type="it.italiangrid.portal.dbapi.domain.UserInfo" scope="request" />
			<jsp:useBean id="virtualMachines"
				type="java.util.List<it.italiangrid.wnodes.model.VirtualMachine>"
				scope="request" />
			<portlet:actionURL var="deleteUrl">
				<portlet:param name="myaction" value="deleteMultipleVirtualMachine" />
			</portlet:actionURL>
	
	
			<form name="goToAddForm" action="${deleteUrl}" method="POST">
	
				<liferay-ui:search-container
					emptyResultsMessage="No Instances" delta="10">
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
								<c:when test="${vms.hostname!='Unknown' }">
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
						<aui:button type="submit" value="Delete Selected Instance"
							onClick="return confirm('Are you sure you want to delete these?');" />
					</aui:button-row>
				</div>
			</form>
		</c:if>
		<c:if test="${!keyPairExist }">
			<div style="width:85%; float:left;">
			<div class="portlet-msg-error">To create new instances please set your key pair.</div>	
			</div>
			<div style="float:left; padding-left: 30px;">
				<img src="<%=request.getContextPath()%>/images/upload.png" width="64" />
			</div>
			
			<div style="clear:both"></div>
			
		</c:if>
	</div>
</div>