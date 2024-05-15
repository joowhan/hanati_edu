/**
 * 
 */

const login = document.querySelector("#login");
const signUp = document.querySelector("#signUp");

 
 login.addEventListener("click", (ev)=>{
	window.location.href = "./login.jsp";
 });
 
signUp.addEventListener("click", (ev)=>{
	window.location.href = "./join.jsp";
 });