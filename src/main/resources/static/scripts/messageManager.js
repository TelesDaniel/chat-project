
let stompClient = null
let socketServerUrl = "/chat"
let socketMessageEndpoint = "/app/send"
let socketMessageTopicPattern = "/topic"

function showMessageOutput(messageOutput) {
	let {from, text, date} = messageOutput
    let messagesTable = document.getElementById('messages-table');
    let tr = document.createElement('tr');
    let p = document.createElement('p');
    
    date = new Date(date);
    let now = new Date()
    let dateString = `${date.getHours()}:${date.getMinutes()}`
    if(date.getDate() != now.getDate() ||
    		date.getMonth() != now.getMonth() ||
    		date.getFullYear() != now.getFullYear()) {
    	dateString = 
    		date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear() + " " + dateString;  
    	//`${date.getDate()}/${date.getMonth()/${date.getYear()} ${dateString}` - IDE NOT SUPPORT
    }
    
    p.innerHTML = `${from}: ${text} - ${dateString}`
    tr.append(p)
    messagesTable.append(tr)
}

function sendMessage() {
    stompClient.send(socketMessageEndpoint, {}, 
      JSON.stringify({
    	  'text': document.getElementById("text").value ,
    	  'roomId': "1016",
    	  "from": "Daniel",
    	  "date": new Date().toISOString()
       }));
}

function connect(roomId) {
    var socket = new SockJS(socketServerUrl);
    console.log(StompJs)
    stompClient = StompJs.Stomp.over(socket);  
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(socketMessageTopicPattern + `/${roomId}`, function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}