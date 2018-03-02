/**
 * 
 */

function registerFunction() {
	
	var fname = document.getElementById("first-name").value;
	var lname = document.getElementById("last-name").value;
	var email = document.getElementById("email").value;
	var pwd = document.getElementById("pass1").value;
	
	var aengg = new XMLHttpRequest();

	
	// sepcify callback function name
	aengg.onreadystatechange =function(){
		
		var val = aengg.responseText;
		
		if (aengg.readyState == 4 && aengg.status == 200){
			if(val=="true"){
				alert("Registration Successful");
				
			}
			else
				alert("Registration failed");
		}
			
		
	}
	// frame requst url and send request

	aengg.open("GET", "chat-register?first-name="+fname+"&last-name="+lname+"&email="+email+"&password="+pwd, true);
	
	aengg.send();
	 

}

