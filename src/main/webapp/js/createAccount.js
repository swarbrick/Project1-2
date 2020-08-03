fetch('/Project1-2/logout')
var type = document.querySelector('#type');
var code;
document.querySelector('#loginPage').addEventListener('click', function() {
	window.location.href = "/Project1-2/login";
});
type.addEventListener('change', alterForm);
function alterForm() {
	if (type.value == 1) {
		document.querySelector('#managerSecret').innerHTML = 
			`<div class="form-group">
	    		<label for="code">Code</label>
	    		<input type="password" class="form-control" id="code" name="code" aria-describedby="codeHelp">
	    	</div>		
			`;
		code = document.querySelector("#code");
	} else if (type.value == 2) {
		document.querySelector('#managerSecret').innerHTML = "";
	}
}
var btn = document.querySelector("#btnCreate");
var un = document.querySelector("#username");
var pw = document.querySelector("#password");
var email = document.querySelector("#email");
var fn = document.querySelector("#firstName");
var ln = document.querySelector("#lastName");
btn.addEventListener('click', submitForm);
function submitForm() {
	if (un.value.trim()==""||pw.value.trim()==""||email.value.trim()==""||fn.value.trim()==""||ln.value.trim()==""||(type.value==1&&code.value!=="QqQq$")) {
		document.querySelector('#incomplete').innerText = "All fields are required";
	} else {
		fetch('/Project1-2/doCreateAccount',{
			method: "post",
			body: JSON.stringify({
			    'username': un.value,
			    'password': pw.value,
			    'firstName': fn.value,
			    'lastName': ln.value,
			    'email': email.value,
			    'acctType': type.value,
			  })
		}).then((response) => response.json())
			.then(function(data) {
				un.value = "";
				pw.value = "";	
				fn.value = "";
				ln.value = "";
				email.value = "";
				type.value = "";
				console.log(data);
			if(data) {
				window.location.replace("/Project1-2/login");
			} else {
				document.querySelector("#incomplete").innerText = "Account not created. This is most likely due to there already being an account associated with that username and/or email address. Please try again.";
			}
		})
	}
}