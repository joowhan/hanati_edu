const liItem = document.createElement("li");
liItem.id = "li-id";
liItem.innerHTML = "<strong>삽입되는 요소</strong>";

//insertBefore
const ol = document.querySelector("#ol-id");
const second = document.getElementById("second");
ol.insertBefore(liItem, second);

//prepend
const liItem1 = document.createElement("li");
liItem1.id = "li-id1";
liItem1.innerHTML = "<strong>prepend 요소</strong>";
ol.prepend(liItem1);

//append()
const liItem2 = document.createElement("li");
liItem2.id = "li-id2";
liItem2.innerHTML = "<strong>append 요소</strong>";
ol.append(liItem2);

//after()
const liItem3 = document.createElement("li");
liItem3.id = "li-id3";
liItem3.innerHTML = "<strong>after 요소</strong>";
ol.after(liItem3);

//insertAdjacentHTML()
function addDiv() {
    const divElem = document.getElementById("ol-id");
    divElem.insertAdjacentHTML("beforebegin", "<span> 시작 전에</span>");
}
function addDiv2() {
    const divElem = document.getElementById("ol-id");
    divElem.insertAdjacentHTML("afterbegin", "<span> 시작 후</span>");
}
function addDiv3() {
    const divElem = document.getElementById("ol-id");
    divElem.insertAdjacentHTML("beforeend", "<span> 마지막 전에</span>");
}
function addDiv4() {
    const divElem = document.getElementById("ol-id");
    divElem.insertAdjacentHTML("afterend", "<span> 마지막 후에</span>");
}

const list = document.getElementById("list");
list.addEventListener("click", function (event) {
    console.log(event.target.id);
    console.log(event.currentTarget.id);
});

//mouse event
const yellowBox = document.getElementById("yellow-box");
const result = document.querySelector(".result");

// click
yellowBox.addEventListener("click", (ev) => {
    result.innerHTML += "<p>click</p>";
});

//dbclick
yellowBox.addEventListener("dblclick", (ev) => {
    result.innerHTML += "<p>dbclick</p>";
    console.log("dbclick");
});

//mousedown
yellowBox.addEventListener("mousedown", (ev) => {
    result.innerHTML += "<p>mousedown</p>";
    console.log("mousedown");
});

yellowBox.addEventListener("mousedown", (ev) => {
    result.innerHTML += "<p>mousedown</p>";
    console.log("mousedown");
});

yellowBox.addEventListener("mouseup", (ev) => {
    result.innerHTML += "<p>mouseup</p>";
    console.log("mouseup");
});

yellowBox.addEventListener("mousemove", (ev) => {
    // result.innerHTML += "<p>mousemove</p>";
    console.log("mousemove");
});

yellowBox.addEventListener("mouseenter", (ev) => {
    result.innerHTML += "<p>mouseenter</p>";
    console.log("mouseenter");
});
yellowBox.addEventListener("mouseout", (ev) => {
    result.innerHTML += "<p>mouseout</p>";
    console.log("mouseout");
});
yellowBox.addEventListener("mouseleave", (ev) => {
    result.innerHTML += "<p>mouseleave</p>";
    console.log("mouseleave");
});

yellowBox.addEventListener("contextmenu", (ev) => {
    result.innerHTML += "<p>contextmenu</p>";
    console.log("contextmenu");
});

const clear = document.querySelector("#clear-button");
clear.addEventListener("click", (ev) => {
    result.innerHTML = "";
});

const input = document.getElementById("keyboardex");
const result2 = document.getElementById("keyboardex1");

input.addEventListener("keydown", (ev) => {
    result2.innerHTML += "<p>keydown</p>";
    console.log("keydown");
});

input.addEventListener("keyup", (ev) => {
    result2.innerHTML += "<p>keyup</p>";
    console.log("keyup");
});

checkId = document.getElementById("focusex");
result3 = document.querySelector("result3");

checkId.addEventListener("blur", (ev) => {
    console.log("focusing");
    const regex = /[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*/;
    if (!regex.test(ev.value)) {
        result3.innerHTML("아이디 형식 확인");
    }
});
