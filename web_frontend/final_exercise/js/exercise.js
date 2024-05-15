const menuList = document.querySelectorAll(".menu");
const navbar_togleBtn = document.querySelector(".navbar_togleBtn");
const navbar_menu = document.querySelector(".navbar_menu");

menuList.forEach((menu) => {
    const dropMenu = menu.nextElementSibling;
    menu.addEventListener("click", (ev) => {
        ev.preventDefault();
        if (dropMenu.style.display == "block") {
            dropMenu.style.display = "none";
        } else {
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

const drop_items = document.querySelectorAll(".drop_item");

drop_items.forEach((drop_item) => {
    drop_item.addEventListener("click", (ev) => {
        ev.stopPropagation();
        const newItem = { [randomKey()]: ev.target.textContent };
        let res = confirm("???");
        if (res) {
            let jsonList = sessionStorage.getItem(key);
            if (jsonList === null) {
                jsonList = [];
            } else {
                jsonList = JSON.parse(jsonList);
            }
            jsonList.push(newItem);
            sessionStorage.setItem(key, JSON.stringify(jsonList));
        }
    });
});
jsonList.forEach(function (obj) {
    for (var key in obj) {
        obj[key];
    }
});

jsonList.foreach(function (obj) {
    for (var key in obj) {
        obj[key];
    }
});

menuList.forEach((menu) => {
    const dropMenu = document.nextElementSibling;
    menu.addEventListener("click", (ev) => {
        ev.preventDefault();
        if (dropMenu.style.display === "block") {
            dropMenu.style.display = "none";
        } else {
            document.querySelectorAll(".drop_menu").forEach((menu) => {
                menu.style.display = "none";
            });
            dropMenu.style.display = "block";
        }
    });
});

drop_items.forEach((drop_item)=>{
    drop_item.addEventListener("click", (ev)=>{
        
    });
});