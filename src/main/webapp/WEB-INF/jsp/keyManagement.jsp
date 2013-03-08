<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style type="text/css">


#presentationWnodes2 {
	color: black;
	font-size: 120%;
	font-weight: bold;
	border-bottom: 1px solid #CCCCCC;
	width: 90%;
	margin-bottom: 15px;
	padding: 10px 0 0 10px;
}

#contentWnodes2 {
	margin-top: 15px;
}

.key{
	width: 140px;
	float: left;
	margin: 0 0 20px 80px;
	background-color: #f4fdef;
	border: 1px;
	border-style: solid;
	border-color: #ACDFA7;
	border-radius: 5px;
	padding: 8px;
	font-size: 14px;
}

.key:hover{
	cursor: pointer;
}

.keyImage{
	margin: 10px 38px 10px 38px;
}

.reset{
	clear: both;
}
#deleteButton{
	width: 100%; 
	padding: 0 34%;
}
.createOrUpload{
	width: 80%;
	
	margin: 20px 9% 0 9% ;
	background-color: #f4fdef;
	border: 1px;
	border-style: solid;
	border-color: #ACDFA7;
	border-radius: 5px;
	padding: 10px;
	font-size: 14px;
}
.label{
	width: 80px;
	float: left;
	margin-bottom: 10px;
}
.value{
	float: left;
	margin-bottom: 10px;
}
.icon{
	width: 20%;
	float: left;
	height: 100px;
	
}

.icon img{
	padding: 38px 25px 38px 5px;
}
.divContent{
	float: left;
	height: 140px;
}

#buttonGenerate{
	padding-top: 55px;
}
</style>
<div>
<div id="presentationWnodes2">My SSH Key Pair</div>
<div id="contentWnodes2">
	<liferay-ui:success key="keys-uploaded-successfully" message="keys-uploaded-successfully" />
	<liferay-ui:success key="added-ssh-key" message="added-ssh-key" />
	<liferay-ui:success key="keys-deleted-successfully" message="keys-deleted-successfully" />
	
	<liferay-ui:error key="keys-not-uploaded" message="keys-not-uploaded" />
	<liferay-ui:error key="system-exception" message="system-exception" />
	<liferay-ui:error key="keys-null" message="keys-null" />
	
	<c:if test="${keyPairExist }">
		
		<portlet:resourceURL var="keyDownloadURL" id="keyDownload">
		</portlet:resourceURL>
		<portlet:resourceURL var="keyPubDownloadURL" id="keyPubDownload">
		</portlet:resourceURL>
		
		<div class="key" onClick="window.location ='${keyDownloadURL}';">
		<img class="keyImage" src="<%=request.getContextPath()%>/images/cert-download.png" width="64" />
		<p><a href="#" > Download private key</a></p>
		</div>
		
		<div class="key" onClick="window.location ='${keyPubDownloadURL}';">
		<img class="keyImage" src="<%=request.getContextPath()%>/images/cert-download.png" width="64" />
		<p><a href="#" > Download public key</a></p>
		</div>
		<div class="reset"></div>
		<div id="deleteButton">
		<portlet:actionURL var="deleteUrl">
			<portlet:param name="myaction" value="destroyKeyPair"></portlet:param>
		</portlet:actionURL>
	
		<aui:form action="${deleteUrl }">
		<aui:button type="submit" value="Delete Uploaded Key Pair"  onClick="return confirm('Before deleting your key pair backup it on your PC. When you delete this key pair you are not able to use web terminal to access to the already created instances.');"></aui:button>
		</aui:form>
		</div>
	</c:if>
	<c:if test="${!keyPairExist }">
	
		<h4>Choose what you want to do:</h4>
		<div class="createOrUpload">
		<div class="icon">
			<img src="<%=request.getContextPath()%>/images/new.png" width="64" />
		</div>
		<div class="divContent">
		<portlet:actionURL var="createUrl">
			<portlet:param name="myaction" value="createKeyPair"></portlet:param>
		</portlet:actionURL>
		<div id="buttonGenerate">
		<aui:form action="${createUrl }">
		<aui:button type="submit" value="Generate New Key Pair"></aui:button>
		</aui:form>
		</div>
		</div>
		<div class="reset"></div>
		</div>
		
		<div class="createOrUpload">
		<div class="icon">
			<img src="<%=request.getContextPath()%>/images/upload.png" width="64" />
		</div>
		<div class="divContent">
		<portlet:actionURL var="uploadUrl">
			<portlet:param name="myaction" value="uploadKeyPair"></portlet:param>
		</portlet:actionURL>
		
		

		<form:form name="fileUploader" commandName="keyPair" method="post"
                action="${uploadUrl}"  enctype="multipart/form-data">

			<div class="label"> Private Key :</div>
			<div class="value"><form:input path="privateKey" type="file"/></div>
			<div class="reset"></div>
			<div class="label"> Public Key :</div>
			<div class="value"><form:input path="publicKey" type="file"/></div>
			<div class="reset"></div>
			<div class="label"> Password :</div>
			<div class="value"><form:input path="password" type="password"/></div>
			<div class="reset"></div>
			<button type="submit">Upload Key Pair</button>

      	</form:form>
      	</div>
      	<div class="reset"></div>
		</div>
		<div class="reset"></div>
	</c:if>
</div>
</div>