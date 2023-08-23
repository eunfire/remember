let param = document.location.href.split("mng_no=");

let mng_no = document.getElementById("mng_no");
let name_ko = document.getElementById("name_ko");
let name_ch = document.getElementById("name_ch");
let sex = document.getElementById("sex");
let birthday = document.getElementById("birthday");
let lastday = document.getElementById("lastday");
let register = document.getElementById("register");
let workout_affil = document.getElementById("workout_affil");
let judge_year = document.getElementById("judge_year");
let hunkuk = document.getElementById("hunkuk");
let img = document.getElementById("imgWrap");

$(document).ready(function () {
  var request1 = new XMLHttpRequest();
  request1.open(
    "GET",
    `https://e-gonghun.mpva.go.kr/opnAPI/publicReportList.do?nPageIndex=1&nCountPerPage=1&type=JSON&mngNo=${param[1]}`
  );
  request1.send();
  request1.onload = function () {
    let items = JSON.parse(this.response).ITEMS[0];
    
	if (items == undefined) {
		return window.history.back();
	}
	
    mng_no.innerText = items.MNG_NO;
    name_ko.innerText = items.NAME_KO;
    name_ch.innerText = `(${items.NAME_CH})`;
    sex.innerText = items.SEX == "여" ? "여성" : "남성";
    birthday.innerText = items.BIRTHDAY;
    lastday.innerText = items.LASTDAY;
    register.innerHTML = `${items.REGISTER_MID_DIV} <span class="hanja">${items.REGISTER_SMALL_DIV}</span>`;
    workout_affil.innerText = items.WORKOUT_AFFIL;
    judge_year.innerText = items.JUDGE_YEAR;
    hunkuk.innerText = items.HUNKUK;

    let param = document.location.href.split("/detail");

    img.innerHTML += `<img id="profileImg" src="https://e-gonghun.mpva.go.kr/hise/ua/getImage.do?mngNo=${
      items.MNG_NO
    }&type=CH" onerror="this.onerror=null; this.src='${param[0]}/image/${
      items.SEX == "여" ? "female" : "male"
    }.svg';" />`;

    let article = document.querySelectorAll("article")[0];
    article.innerHTML = `${items.ACHIVEMENT_KO.replaceAll(
      "(",
      "<span class='hanja'>("
    ).replaceAll(")", ")</span>")}`;
    
    
    let errorLink = document.getElementsByClassName("errorWrap")[0];
    let aLink = 'https://e-gonghun.mpva.go.kr/user/ContribuReportDetail.do?goTocode=20001&pageTitle=Report&mngNo='
    
    errorLink.innerHTML = `<a href="${aLink}${items.MNG_NO}">${aLink}${items.MNG_NO}</a>`
    
    let profileImg = document.getElementById("profileImg").src;
    
    $('head').append(`<meta property="og:title" content="${items.NAME_KO}(${items.NAME_CH})">`)
    $('head').append(`<meta property="og:description" content="${items.ACHIVEMENT_KO}">`)
    $('head').append(`<meta property="og:image" content="${profileImg}">`)
    $('head').append(`<meta property="og:url" content="http://49.50.175.53/detail?mng_no=${items.MNG_NO}">`)

  };

  var request2 = new XMLHttpRequest();
  request2.open(
    "GET",
    `https://e-gonghun.mpva.go.kr/opnAPI/indepCrusaderList.do?nPageIndex=1&nCountPerPage=1&type=JSON&mngNo=${param[1]}`
  );
  request2.send();
  request2.onload = function () {
    let items = JSON.parse(this.response).ITEMS[0];
    let name = document.getElementsByClassName("name")[0];

    if (items != undefined) {
      name.innerHTML += `
			<div class="medal">
				<object data="/image/medal.svg" alt="메달"></object>
				<p>이달의 독립운동가</p>
			</div>
			`;

      let article = document.querySelectorAll("article")[1];
      article.innerHTML = `${items.ACHIVEMENT.replace(" 1. ", "<br /><br />1. ")
        .replace(" 2. ", "<br /><br />2. ")
        .replace(" 3. ", "<br /><br />3. ")
        .replace(" 4. ", "<br /><br />4. ")
        .replace(". 3. ", "<br /><br />3. ")
        .replace(". 4. ", "<br /><br />4. ")
        .replace(". 5. ", "<br /><br />5. ")
        .replace(". 6. ", "<br /><br />6. ")
        .replace(" 목차 0. 1.", "<br /><br /><div class='mokcha'>목차</div>1.")
        .replace(" 목차 1.1.", "<br /><br /><div class='mokcha'>목차</div>1.")
        .replace(" 2.2.", "<br />2.")
        .replace(" 3.3.", "<br />3.")
        .replace(" 4.4.", "<br />4.")}`;
    } else {
      let li = document.getElementById("hide");
      li.style.display = "none";
    }
  };
});

function copyURL() {
  var url = "";
  var textarea = document.createElement("textarea");
  document.body.appendChild(textarea);
  url = window.document.location.href;
  textarea.value = url;
  textarea.select();
  document.execCommand("copy");
  document.body.removeChild(textarea);
  alert("URL이 복사되었습니다.");
}
