function validateMovieForm() {
	var title = document.forms["movieForm"]["title"].value;
	var boxOffice = document.forms["movieForm"]["boxOffice"].value;
	var dateOfLaunch = document.forms["movieForm"]["dateOfLaunch"].value;
	var genre = document.forms["movieForm"]["genre"].value;
	if (title == "") {
		alert("Title is required.");
		return false;
	}
	else if (title.length < 2 || title.length > 100) {
		alert("Title should have 2 to 100 characters.");
		return false;
	}			
	else if (boxOffice == "") {
		alert("Box Office is required.");
		return false;
	}
	else if (isNaN(boxOffice)) {
		alert("Box Office has to be a number.");
		return false;
	}
	else if (dateOfLaunch == "") {
		alert("Date of Launch is required.");
		return false;
	}			
	else if (genre == "") {
		alert("Select one genre.");
		return false;
	}			
	else {
		return true;
	}
}

function validateAddMovieForm() {
	var id = document.forms["addMovieForm"]["id"].value;
	var title = document.forms["addMovieForm"]["title"].value;
	var boxOffice = document.forms["addMovieForm"]["boxOffice"].value;
	var active = document.forms["addMovieForm"]["active"];
	var dateOfLaunch = document.forms["addMovieForm"]["dateOfLaunch"].value;
	var genre = document.forms["addMovieForm"]["genre"].value;
	if (id == "") {
		alert("Id is required.");
		return false;
	}
	else if (isNaN(id)) {
		alert("Id has to be a number.");
		return false;
	}
	else if (title == "") {
		alert("Title is required.");
		return false;
	}
	else if (title.length < 2 || title.length > 100) {
		alert("Title should have 2 to 100 characters.");
		return false;
	}			
	else if (boxOffice == "") {
		alert("Box Office is required.");
		return false;
	}
	else if (isNaN(boxOffice)) {
		alert("Box Office has to be a number.");
		return false;
	}
	else if ((!active[0].checked) && (!active[1].checked)) {
		alert("Active status is required.");
		return false;
	}
	else if (dateOfLaunch == "") {
		alert("Date of Launch is required.");
		return false;
	}			
	else if (genre == "") {
		alert("Select one genre.");
		return false;
	}			
	else {
		return true;
	}
}

function validateLoginAdminForm() {
	var username = document.forms["loginAdminForm"]["username"].value;
	var password = document.forms["loginAdminForm"]["password"].value;
	if (username == "") {
		alert("Username is required.");
		return false;
	}
	else if (password == "") {
		alert("Password is required.");
		return false;
	}
	else if (username != "aditya") {
		alert("Invalid Username.");
		return false;
	}
	else if (password != "password") {
		alert("Invalid Password.");
		return false;
	}
	else {
		return true;
	}
}

function validateLoginCustomerForm() {
	var username = document.forms["loginCustomerForm"]["username"].value;
	var password = document.forms["loginCustomerForm"]["password"].value;
	if (username == "") {
		alert("Username is required.");
		return false;
	}
	else if (password == "") {
		alert("Password is required.");
		return false;
	}
	else if (username != "aditya") {
		alert("Invalid Username.");
		return false;
	}
	else if (password != "password") {
		alert("Invalid Password.");
		return false;
	}
	else {
		return true;
	}
}