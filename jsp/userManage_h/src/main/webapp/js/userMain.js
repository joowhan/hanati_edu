/**
 * 
 */

const edit_info = document.querySelector("#edit_info");
const logout = document.querySelector("#logout"); 
const exit_user = document.querySelector("#exit_user");

edit_info.addEventListener("click", (ev)=>{
	window.location.href = "./modify.jsp";
 });
 
logout.addEventListener("click", (ev)=>{
	window.location.href = "./logout.jsp";
 });
 
 exit_user.addEventListener("click", (ev)=>{
	if(confirm("정말 탈퇴하시겠습니까?")){
			window.location.href = "./withdrawal.jsp";
	}
 });
 