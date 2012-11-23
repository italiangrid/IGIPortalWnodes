/**
 * List of the virtual machine that the user want to delete.
 */
var list = new Array();

/**
 * Function that show the delete button if some virtual machine are selected, or hide the button if none.
 * @param uuid - The virtual machine identifier selected.
 */
function viewOrHideDeleteButton(uuid) {
	var i = 0;
	var newlist = new Array();
	var isPresent = false;
	for (i = 0; i < list.length; i++) {
		if (list[i] != uuid) {
			newlist.push(list[i]);
		} else {
			isPresent = true;
		}
	}

	if (isPresent == false)
		list.push(uuid);
	else
		list = newlist;

	if (list.length == 0) {
		$("#deleteButton").hide("slow");
	} else {
		$("#deleteButton").show("slow");
	}
}

/**
 * Function that close all the opened shell.
 */
function closeSession() {

	var i = 0;
	for (i = 0; i <= 100; i++) {
		GateOne.Net.killTerminal(i);
	}

}

/**
 * Function that create a new shell.
 * @param host - The hostname of the ssh connection.
 * @param user - The user identifier.
 */
function create(host, user) {

	GateOne.init({
		url : "https://flyback.cnaf.infn.it:22443/",
		fontSize : '120%',
		fillContainer : false,
		style : {
			'width' : '97%',
			'height' : '600px',
			'margin' : '50px 10px 0px 10px'
		},
		autoConnectURL : "ssh://dmichelotto@" + host + "/?identities=" + user
				+ "/id_rsa",
	});

}