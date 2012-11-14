<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style>
<!--
 CSS temporaneo da spostare poi in main css
-->

#containerWnodes{
	box-shadow: 10px 10px 5px #888;
	background-color: #e4e4e4;
	border: 1px;
	border-style: solid;
	border-color: #c1c1c1;
	border-radius: 5px;
	padding: 5px;
	margin-right: 9px;
}

#presentationWnodes{
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
#contentWnodes{
	margin-top: 45px;
}
</style>

<jsp:useBean id="userInfo"
	type="it.italiangrid.portal.dbapi.domain.UserInfo"
	scope="request" />

<div id="containerWnodes">
	<div id="presentationWnodes">
		Hi ${userInfo.firstName } ${userInfo.lastName } 
	</div>
	<div id="contentWnodes">
	Tabella con lista macchine virtuali dell'utente in WNODeS.
	</div>
</div>