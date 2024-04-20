

function foo(people, count){
    //array 선언
    let selectedPeople = [];
    // count만큼 사람이 선택될 때까지 반복
    while(selectedPeople.length < count){
        // 소수점 버리기
        let randomIndex = Math.floor(Math.random()*people.length);
        // random index의 사람을 선택
        let person = people[randomIndex];
        //만약 그 사람을 포함하고 있지 않다면 selectedPeople에 추가(중복된 사람 X)
        if(!selectedPeople.includes(person)){
            //person을 추가
            selectedPeople.push(person);
        }
    }
    //완성된 array를 return 
    return selectedPeople;
}

// foo 함수에 people 배열과 뽑을 인원 count를 매개변수로 넣어 보내면 랜덤한 사람이 뽑혀서 return 된다. 



