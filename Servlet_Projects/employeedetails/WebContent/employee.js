function goback(){
	
	window.history.back();
	
}

function add(){
	
	var firstname = document.getElementById("firstname").value;
	if(firstname == ""){
		
		alert("Please enter your first name");
		return false;
		
	}
	
	var lastname = document.getElementById("lastname").value;
	if(lastname == ""){
		
		alert("Please enter your last name");
		return false;
		
	}
	
	var gender =document.getElementById("gender").value;
	if(gender == ''){
		
		alert("select gender");
		return false;

    }
	
	var phno = document.getElementById("phno").value;
	if(phno == ""){
		
		alert("Please enter your phone number");
		return false;
		
	}
	
	var email = document.getElementById("email").value;
	if(email == ""){
		
		alert("Please enter your email in the format of ---@--.com");
		return false;
		
	}
	
	var state = document.getElementById("state").value;
	if(state == 'select'){
		
		alert("please enter your state");
		return false;
		
	}
	
	var Address = document.getElementById("Address").value;
	if(Address == ''){
		
		alert("Please select your address");
		return false;
		
	}
	
	var empdes = document.getElementById("empdes").value;
	if(empdes == 'select'){
		
		alert("please select your designation");
		return false;
		
	}
	
	var info = "<table cellpadding='5' cellspacing='7' border='6'><tr><th>First Name</th><th>Last Name</th><th>Gender</th><th>Mobile_no</th><th>E-mail</th><th>State</th><th>Address</th><th>Designation</th></tr><tr><td>"+firstname+"</td><td>"+lastname+"</td><td>"+gender+"</td><td>"+phno+"</td><td>"+email+"</td><td>"+state+"</td><td>"+Address+"</td><td>"+empdes+"</td></tr></table>";
	
	document.getElementById("tableinfo").innerHTML=info;
	//
	
	//document.write("<br>")
	alert("Thank You!!! You're successfully registered..");
}



	