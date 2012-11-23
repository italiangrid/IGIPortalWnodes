var list = new Array();

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

function closeSession() {

	// GateOne.Terminal.closeTerminal('5');
	var i = 0;
	for (i = 0; i <= 100; i++) {
		GateOne.Net.killTerminal(i);
	}

}

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