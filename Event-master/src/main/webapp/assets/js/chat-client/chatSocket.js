var stompClient = null;

function setConnected(connected) {
	document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
			: 'hidden';
	document.getElementById('response').innerHTML = '';
}

function connect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
	$("#formAlert").hide();
	$("#formInfoAlert").hide();
	$("#formAlertLength").hide();
	var socket = new SockJS('/EventMedia/request');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
			console.log(greeting);
			showGreeting(JSON.parse(greeting.body).content);
			$("#formInfoAlert").slideUp(400);
			$("#name").val("");
			$("#name").focus();
			$("#name").select();
		});
	});
}

function sendName() {
	var name = document.getElementById('name').value;
	if (name === "") {

		// If message is empty prevent submission and show the alert
		$("#formAlert").slideDown(400);

	} else {
		if (name.length > 300) {
			$("#formAlert").slideUp(400);
			$("#formAlertLength").slideDown(400);
		} else {
			$("#formInfoAlert").slideDown(400);
			$("#formAlertLength").slideUp(400);
			stompClient.send("/app/request", {}, JSON.stringify({
				'name' : name
			}));
		}

	}

}

function showGreeting(message) {
	var decoded = $("<div/>").html(message).text();

	var tmp = "";
	var serverResponse = document.getElementById("response");
	var nameUser = document.getElementById('nameUser').value;
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';

	var suffixesFile = message.substr(0, message.indexOf(' :'));
	if (nameUser == suffixesFile) {
		p.style.color = '#8A0808';
	}

	tmp = "<time>" + getCurrentDateTime() + "</time>" + "|"
			+ "<span class='glyphicon glyphicon-arrow-right'></span> "
			+ decoded;
	// Assigning the decoded HTML to the <p> element
	p.innerHTML = tmp;
	serverResponse.appendChild(p);
}

/**
 * Utility function to return the date time in simple format like Tue Jan 07
 * 2014 @ 11:47:24 AM
 */
function getCurrentDateTime() {
	var date = new Date();
	var time = date.toLocaleTimeString();
	return time;
}