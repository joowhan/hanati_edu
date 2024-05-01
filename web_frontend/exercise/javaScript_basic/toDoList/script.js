const input = document.querySelector("#inputText");
const submit = document.querySelector("#addItem");
const resultList = document.querySelector(".list");
let listIndex = 0;

// function allStorage() {
//     var values = [],
//         keys = Object.keys(localStorage),
//         i = keys.length;

//     while (i--) {
//         values.push(localStorage.getItem(keys[i]));
//     }

//     return values;
// }

//초기 로딩되었을 때 로직
/* *localStorage에서 데이터 불러오기
1. lastIndex

*/
// localStorage.setItem([1, 2, 3], ["a", "b", "c"]);

if (localStorage.getItem !== null) {
    console.log("hee");
    for (let i = 0; i < localStorage.length; i++) {
        console.log(
            localStorage.key(i),
            localStorage.getItem(localStorage.key(i))
        );
        if (localStorage.key(i) === "NEXTIDX") {
            console.log(localStorage.getItem(localStorage.key(i)));
            listIndex = localStorage.getItem(localStorage.key(i));
            continue;
        }
        resultList.innerHTML +=
            "<div>" +
            "<li" +
            localStorage.key(i) + // 저장된 value를
            ">" +
            localStorage.getItem(localStorage.key(i)) +
            "<button id=delete" +
            listIndex +
            ">삭제</button><br/>" +
            "" +
            "</li>" +
            "<div>";
    }
}

submit.addEventListener("click", (ev) => {
    resultList.innerHTML +=
        "<div>" +
        "<li" +
        listIndex +
        ">" +
        input.value +
        "<button id=delete" +
        listIndex +
        ">삭제</button><br/>" +
        "" +
        "</li>" +
        "<div>";
    localStorage.setItem(listIndex, input.value);

    listIndex++;
    localStorage.setItem("NEXTIDX", listIndex);

    console.log("add");
});

const toDoList = document.querySelector(".list");

//개별 삭제
toDoList.addEventListener("click", (ev) => {
    let s = ev.target.id;
    const deleteItem = document.getElementById(ev.target.id);
    console.log(Number(s.substr(6)));
    localStorage.removeItem(Number(s.substr(6)));

    deleteItem.parentNode.remove(deleteItem.parentNode);
});

const deleteAll = document.querySelector("#deleteAll");
// 전체 삭제
deleteAll.addEventListener("click", (ev) => {
    toDoList.innerHTML = "";
    localStorage.clear();
    listIndex = 0;
});
