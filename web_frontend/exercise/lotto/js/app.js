// 랜덤한 숫자를 추출하는 함수
function getRandomNumber(count) {
    let selectedNumbers = [];
    // count개 수 뽑기
    while (selectedNumbers.length < count) {
        //1~45 수 추출
        let randomNum = Math.floor(Math.random() * 45) + 1;
        //이미 뽑은 수가 아니라면 추가
        if (!selectedNumbers.includes(randomNum)) {
            selectedNumbers.push(randomNum);
        }
    }
    return selectedNumbers;
}
// 숫자 비교, 몇 개 정답인지 return
function compareNumber(lotto, selectedNum) {
    //입력값을 정수 타입으로 변경
    selectedNum = selectedNum.map(Number);
    //console에 출력해서 확인
    console.log(lotto);
    console.log(selectedNum);
    //두 배열간 공통 요소 찾기(교집합)
    let intersection = lotto.filter((x) => selectedNum.includes(x));
    //일치하는 번호 개수 return
    return intersection.length;
}
// 버튼 클릭 이벤트에 연결
const btn = document.getElementById("buttonId");
const result1 = document.getElementById("result1");
const result2 = document.getElementById("result2");

btn.addEventListener("click", function () {
    // debugger;
    const formResult = document.getElementById("num1").value;
    const selectedNum = formResult.split(" ");
    let lotto = getRandomNumber(6);

    result1.innerText = lotto;
    result2.innerText = compareNumber(lotto, selectedNum) + "개 일치합니다.";
});
