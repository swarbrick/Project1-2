fetch('/Project1-2/getSessionType')
    .then((response) => response.json())
        .then(function(data) {
            console.log(data);
            if (data && data == 1) {    
                window.location.href = "/Project1-2/managerViewAll";
            } else if (!data) {
                window.location.href = "/Project1-2/login";
            }
        }) 

window.onload = function() {
	var table = document.querySelector("#employeeReimbursements");
	fetch('/Project1-2/getEmployeeReimbursements')
	.then((response) => response.json())
		.then(function(data) {
			if(data) {
				for(r of data) {
					var status;
					switch(r["status_id"]) {
						case 1:
							status = "pending";
						break;
						case 2:
							status = "approved";
						break
						case 3:
							status = "denied";
						break;
					}
					var type;
					switch(r["type_id"]) {
						case 1:
							type = "lodging";
						break;
						case 2:
							type = "travel";
						break
						case 3:
							type = "food";
						break;
						case 4:
							type = "other";
						break;
					}
					var submitted = r["submitted"].substring(0,10);
					if (r["resolved"]){
						var resolved = r["resolved"].substring(0,10);
					} else {
						var resolved = "";
					}
					if (!r["description"]) {
						r["description"] = "";
					}
					table.innerHTML += `
					<tr>	
						<td>${r["id"]}</td>
						<td>${r["amt"]}</td>
						<td>${submitted}</td>
						<td>${resolved}</td>
						<td>${r["description"]}</td>
						<td>${status}</td>
						<td>${type}</td>
					</tr>	
					`;
				}
			} else {

			}
		}) 
}
document.querySelector('#logout').addEventListener('click', function() {
	fetch('/Project1-2/logout');
	window.location.href = "/Project1-2/login";
});
var button = document.querySelector('#btnCreateNewR');
button.addEventListener('click', attemptCreate);
function attemptCreate(e) {
	e.preventDefault();
	var amt = document.querySelector("#amount").value;
	var desc = document.querySelector("#description").value;
	var type = document.querySelector("#reimbursementType").value;
	
	fetch(`/Project1-2/doCreateReimbursement?amt=${amt}&desc=${desc}&type=${type}`)
		.then((response) => response.json())
			.then(function(data) {
				if(data) {
					window.location.href = "/Project1-2/employee";
				} else {
					document.querySelector("#unsuccessful").innerText = "Creation of reimbursement was not successful. Please try again.";
				}
		}) 
		
}
