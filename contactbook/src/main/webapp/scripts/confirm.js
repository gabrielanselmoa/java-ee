/**
 * Confirmation of Contact Delete
 * 
 * @author Gabriel Anselmo - FullStack Developer
 * @param id
 */

const confirmer = (id) => {
	let answer = confirm("Are you sure about delete this contact?");
	
	if (answer){
		window.location.href = "delete?id=" + id;
		alert("Contact Deleted sucessfully!");
	}
}