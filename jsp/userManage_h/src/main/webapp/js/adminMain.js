/**
 * 
 */

 const readUsers = document.querySelector("#readUsers");
 const changeStStatus = document.querySelector("#changeStStatus");
 const changeUserType = document.querySelector("#changeUserType");
 const logout = document.querySelector("#logout");
 
 readUsers.addEventListener("click",(ev)=>{
	window.location.href="./readUsers.jsp";
 });
 
 changeStStatus.addEventListener("click",(ev)=>{
	window.location.href="./changeStStatus.jsp";
 });
 
 changeUserType.addEventListener("click",(ev)=>{
	window.location.href="./changeType.jsp";
 });
 
 logout.addEventListener("click", (ev)=>{
	window.location.href = "./logout.jsp";
 });
 