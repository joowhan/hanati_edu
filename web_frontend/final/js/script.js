// index.html 요소 불러오기
const drop_menuList = document.querySelectorAll(".drop_menu");
const menuList = document.querySelectorAll(".menu");
const drop_items = document.querySelectorAll(".drop_item");
const menu_img = document.querySelector(".menu_img");
const nav_menu = document.querySelector(".nav_menu");
const order_read = document.querySelector(".order_read");
const order_list = document.querySelector(".order_list");
const order_btn = document.querySelector(".order");

//random 문자열 만들기, json list 내부의 key -> 주문한 아이템에 고유 ID가 된다.
const randomKey = (length = 8) => {
    return Math.random().toString(16).substr(2, length);
};
//session storage의 key
const key = "json_list";
// json 메뉴 불러오기
//json fetch
fetch("https://prof-jwchi.github.io/koporepo/JSON/menu.json")
    .then((response) => {
        // JSON 형태로 응답을 변환
        return response.json();
    })
    .then((data) => {
        // 추천메뉴 읽기
        let cnt = 0;
        drop_menuList.forEach((drop_menu) => {
            console.log(drop_menu, cnt);
            // 추천메뉴
            if (cnt === 0) {
                //데이터가 존재한다면
                if (data.Favorites.length > 0) {
                    //drop_menu에 추가하기
                    for (let i = 0; i < data.Favorites.length; i++) {
                        console.log(data.Favorites[i]);
                        drop_menu.innerHTML += `<li><a href="#" class="drop_item">${data.Favorites[i]}</a></li>`;
                    }
                }
            }
            //마실 것
            else if (cnt === 1) {
                //데이터가 존재한다면
                if (data.drinks.length > 0) {
                    //drop_menu에 추가하기
                    for (let i = 0; i < data.drinks.length; i++) {
                        console.log(data.drinks[i]);
                        drop_menu.innerHTML += `<li><a href="#" class="drop_item">${data.drinks[i]}</a></li>`;
                    }
                }
            }
            //먹을 것
            else {
                //데이터가 존재한다면
                if (data.foods.length > 0) {
                    //drop_menu에 추가하기
                    for (let i = 0; i < data.foods.length; i++) {
                        console.log(data.foods[i]);
                        drop_menu.innerHTML += `<li><a href="#" class="drop_item">${data.foods[i]}</a></li>`;
                    }
                }
            }
            cnt++;
        });
    })
    .catch((error) => {
        console.error("파일을 읽는 중 오류 발생:", error);
    });

// 메뉴를 눌렀을 때  메뉴 보이고 나머지 메뉴들은 숨기기
menuList.forEach((menu) => {
    const dropMenu = menu.nextElementSibling;
    //메뉴를 클릭한다면
    menu.addEventListener("click", (ev) => {
        // 주문 내역 및 주문 버튼 숨기기
        order_list.classList.add("hide");
        order_btn.classList.add("hide");
        // 브라우저 이벤트 방지
        ev.preventDefault();
        console.log(ev.target.textContent);
        //block 이라면 none으로 안보이게 변경
        if (dropMenu.style.display === "block") {
            dropMenu.style.display = "none";
        } else {
            //다른 drop menu들 모두 none으로 변경
            document.querySelectorAll(".drop_menu").forEach((menu) => {
                menu.style.display = "none";
            });
            //dropMenu 보이기
            dropMenu.style.display = "block";
        }
    });
});

//dropdown 내부 아이템을 클릭했을 때 처리
drop_menuList.forEach((drop_item) => {
    drop_item.addEventListener("click", (ev) => {
        // 상위 요소로 이벤트 전달 방지
        ev.stopPropagation();
        console.log(ev.target.textContent);
        // sessionStorage에 추가할 데이터 생성
        const new_item = { [randomKey()]: ev.target.textContent };
        // sessionStorage 읽기
        let json_list = sessionStorage.getItem(key);
        // confirm 창으로 주문 확인
        let res = confirm(`${ev.target.textContent}을 주문하시겠습니까?`);
        // 저장을 한다면
        if (res) {
            // 처음 저장한다면
            if (json_list === null) {
                json_list = [];
            }
            //이미 데이터가 존재한다면 더해서 추가
            else {
                //json parsing
                json_list = JSON.parse(json_list);
            }
            // 데이터 추가
            json_list.push(new_item);
            // json으로 변환 후 sessionStorage에 추가
            sessionStorage.setItem(key, JSON.stringify(json_list));
            alert("추가 완료!");
        }
    });
});

//760px 이하에서 햄버거 버튼을 클릭한다면 숨겨진 메뉴 보이기
menu_img.addEventListener("click", (ev) => {
    console.log("1");
    nav_menu.classList.toggle("active");
});

// 주문 내역을 클릭했을 때
order_read.addEventListener("click", (ev) => {
    // 주문 내역 리스트, 주문하기 버튼 보이기
    order_list.classList.remove("hide");
    order_btn.classList.remove("hide");
    //sessionStorage에서 데이터 읽기
    let json_list_string = sessionStorage.getItem(key);
    // 만약 저장된 데이터가 없다면 내역 없음을 출력하기
    if (json_list_string === null) {
        order_list.innerHTML = "<div>주문 내역이 없습니다.</div>";
    }
    // 데이터가 존재한다면 주문 내역 초기화 후 데이터 저장
    else {
        order_list.innerHTML = "";
        //데이터 parsing
        let json_list = JSON.parse(json_list_string);
        //for 문을 돌면서 데이터 읽기
        json_list.forEach(function (obj) {
            for (let key in obj) {
                order_list.innerHTML += `<div> ${obj[key]} </div>`;
            }
        });
    }
});

// 주문하기 버튼을 눌렀을 때
order_btn.addEventListener("click", (ev) => {
    // 세션 clear
    sessionStorage.clear();
    // 페이지 새로고침
    location.reload(true);
    alert("주문 완료!");
});
