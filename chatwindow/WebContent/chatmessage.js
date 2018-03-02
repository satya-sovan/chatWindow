/**
 * 
 */
setInterval(oldChat,2000);
function oldChat(){
	var email=document.getElementById("email").value;
var aengg = new XMLHttpRequest();

	
	// sepcify callback function name
	aengg.onreadystatechange =function(){
		
		var val = aengg.responseText;
		
		if (aengg.readyState == 4 && aengg.status == 200){
			document.getElementById("chat-logs").innerHTML=val;
			
		}
			
		
	}
	// frame requst url and send request

	aengg.open("GET", "ChatRetrive?email="+email, true);
	
	aengg.send();
	document.getElementById('chat-logs').scrollTop = document.getElementById('chat-logs').scrollHeight;
	 
}
function insertMsgs(){
	var email=document.getElementById("email").value;
	var msgs=document.getElementById("msgs").value;
	var aengg = new XMLHttpRequest();

		
		// sepcify callback function name
		aengg.onreadystatechange =function(){
			
			var val = aengg.responseText;
			
			if (aengg.readyState == 4 && aengg.status == 200){
				document.getElementById("insmsg").innerHTML=val;
				document.getElementById("insmsg").scrollIntoView(true);
			}
				
			
		}
		// frame requst url and send request

		aengg.open("GET", "ChatInsert?email="+email+"&msgs="+msgs, true);
		
		aengg.send();
}