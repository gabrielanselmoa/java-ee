/**
 * Forms Validator
 * @author Gabriel Anselmo - FullStack Developer
 */

 const isValid = () => {
	let name = contactform.name.value;
	let phone = contactform.phone.value;
	
	if(name === ""){
		alert("Fill name field!")
		contactform.name.focus();
		return false;
	} else if(phone === ""){
		alert("Fill phone field!")
		contactform.phone.focus();
		onsole.log("Not Sent!!")
		return false;
	} else{
		console.log("Sent!!");
		return document.forms["contactform"].submit();
		
	}
 }