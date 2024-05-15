document.cookie = "name=jung; path=/; Max-Age=3600";

cookieStore.set("user", "John Doe", { expires: 3600, sameSite: "strict" });
const cookie = cookieStore.get("user").then((cookie) => console.log(cookie));

// Fetch API를 사용해 aaa.json 파일을 읽어옵니다.
fetch("https://jwonchi.github.io/kopoclass/JSON/quiz.json")
  .then((response) => {
    // JSON 형태로 응답을 변환합니다.
    return response.json();
  })
  .then((data) => {
    // data가 1 이상인 경우 퀴즈 만드는 함수를 호출한다.
    if (data.quizzes.length > 0) {
      //   makeQuiz(data.quizzes);
      console.debug(`data.quizzes.length : ${data.quizzes.length}`);
      console.log(data.quizzes);
      for (let i = 0; i < data.quizzes.length; i++) {
        const question = document.querySelector(".question");
        const options = document.querySelector(".question .options-list");
        question.innerHTML += `<p> ${data.quizzes[i].question} </p>`;

        question.innerHTML +=
          `<ol><li>${data.quizzes[i].options.a}</li>` +
          `<li>${data.quizzes[i].options.b}</li>` +
          `<li>${data.quizzes[i].options.c}</li>` +
          `<li>${data.quizzes[i].options.d}</li></ol>`;
      }
    }
  })
  .catch((error) => {
    console.error("파일을 읽는 중 오류 발생:", error);
  });
