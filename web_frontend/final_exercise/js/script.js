const menuList = document.querySelectorAll(".menu");
const navbar_togleBtn = document.querySelector(".navbar_togleBtn");
const navbar_menu = document.querySelector(".navbar_menu");
const dropMenuList = document.querySelectorAll(".drop_menu");
const drop_items = document.querySelectorAll(".drop_item");
const key = "json_list";
const randomKey = (length = 8) => {
    return Math.random().toString(16).substr(2, length);
};
const order_view = document.querySelector(".order_view");

menuList.forEach((menu) => {
    const dropMenu = menu.nextElementSibling;

    menu.addEventListener("click", (ev) => {
        ev.preventDefault(); // 링크 이벤트 방지

        if (dropMenu.style.display === "block") {
            dropMenu.style.display = "none";
        } else {
            // 다른 메뉴의 drop_menu가 열려 있을 경우 모두 닫기
            document.querySelectorAll(".drop_menu").forEach((menu) => {
                menu.style.display = "none";
            });
            dropMenu.style.display = "block";
        }
    });
});

navbar_togleBtn.addEventListener("click", (ev) => {
    navbar_menu.classList.toggle("active");
});

drop_items.forEach((drop_item) => {
    drop_item.addEventListener("click", (ev) => {
        const newItem = { [randomKey()]: ev.target.textContent };
        ev.stopPropagation(); // 부모 요소로의 이벤트 전파 방지
        let res = confirm(
            `${ev.target.textContent}를 주문 내역에 추가하시겠어요?`
        );
        if (res) {
            let jsonList = sessionStorage.getItem(key);

            // 기존 데이터가 없을 경우 빈 배열로 초기화
            if (jsonList === null) {
                jsonList = [];
            } else {
                // 기존 데이터가 있을 경우 JSON 형식의 문자열을 JavaScript 객체로 변환
                jsonList = JSON.parse(jsonList);
            }

            // 새로운 아이템을 JSON 배열에 추가
            jsonList.push(newItem);

            // sessionStorage에 JSON 형식으로 저장
            sessionStorage.setItem(key, JSON.stringify(jsonList));
        }
    });
});

order_view.addEventListener("click", (ev) => {
    document.querySelector(".order_list").classList.remove("hide");
    const jsonListString = sessionStorage.getItem("json_list");
    const jsonList = JSON.parse(jsonListString);
    if (sessionStorage.getItem(key) === null || jsonList.length < 1) {
        document.querySelector(".order_list").innerHTML =
            "<div>주문 내역이 없습니다.</div>";
    } else {
        document.querySelector(".reset").classList.remove("hide");
        document.querySelector(".order_list").innerHTML = "";
        jsonList.forEach(function (obj) {
            for (var key in obj) {
                document.querySelector(
                    ".order_list"
                ).innerHTML += `<div>${obj[key]}</div>`;
            }
        });
    }
});

document.querySelector(".reset").addEventListener("click", (ev) => {
    sessionStorage.clear();
    alert("주문 완료!");
    location.reload(true);
});
// document 클릭 이벤트 추가
// document.addEventListener("click", (ev) => {
//     // 클릭한 요소가 메뉴나 drop_menu가 아닌 경우 모든 drop_menu 닫기
//     console.log("창 닫기");
//     let isMenuOrDropItem = false;
//     let clickedElement = ev.target;

//     while (clickedElement) {
//         if (
//             clickedElement.classList.contains("menu") ||
//             clickedElement.classList.contains("drop_item")
//         ) {
//             isMenuOrDropItem = true;
//             break;
//         }
//         clickedElement = clickedElement.parentElement;
//     }

//     if (!isMenuOrDropItem) {
//         dropMenuList.forEach((drop) => {
//             drop.style.display = "none";
//         });
//     }
// });
