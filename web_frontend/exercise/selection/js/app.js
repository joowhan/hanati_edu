// 발표자를 선정하는 함수
function getRandomPresenter(people, count) {
    let selectedPeople = [];
    while (selectedPeople.length < count) {
        let randomIndex = Math.floor(Math.random() * people.length);
        let person = people[randomIndex];
        if (!selectedPeople.includes(person)) {
            selectedPeople.push(person);
        }
    }
    return selectedPeople;
}

// 버튼 클릭 이벤트에 연결
const btn = document.getElementById("buttonId");
const result1 = document.getElementById("result1");
const result2 = document.getElementById("result2");
btn.addEventListener("click", function () {
    debugger;
    result1.innerText = getRandomPresenter(people, 5);
    result2.innerText = getRandomPresenter(people, 5);
});
