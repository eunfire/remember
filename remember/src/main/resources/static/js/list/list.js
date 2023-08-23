// type active function
function typeActive(type) {
	$(`.listTypeLi[data-target='${ type }']`).addClass("activeLi");
}

// 독립유공자 리스트 생성자
function indeList(mngNo, name, sex, work) {
	this.mngNo = mngNo;
	this.sex = sex;
	this.work = work;
	if (name.length > 3) {
		this.name = name.substring(0, 3) + "...";
	}	// if end
	else {
		this.name = name;
	}	// else end
	this.printLi = function() {
		return `
			<li class="listLi" data-target="${ this.mngNo }">
				<div class="liCaption">
					<p class="liMngNo">${ this.mngNo }</p>
					<p class="liWork">${ this.work }</p>
				</div>
				<div class="liContents">
					<div class="liProfile">
						<img
							class="yesProfile"
							src="https://e-gonghun.mpva.go.kr/hise/ua/getImage.do?mngNo=${ this.mngNo }&type=CH"
							data-target="${ this.sex }"
						>
					</div>
					<div class="liInfo">
						<div class="liName">
							<p>${ this.name }</p>
						</div>
						<div class="liBottom">
							<div class="liSex">
								<p>성별</p>
								<p>${ this.sex }</p>
							</div>
							<div class="liLike" data-target="${ this.mngNo }">
								<p>존경</p>
								<p>0</p>
							</div>
							<div class="liComm" data-target="${ this.mngNo }">
								<p>댓글</p>
								<p>0</p>
							</div>
						</div>
					</div>
				</div>
			</li>
		`;
	}	// printLi end
};	// 독립유공자 리스트 생성자 end

// 독립유공자 DB 출력 함수
function likeComment(mngNo, like, comment) {
	$(`.liLike[data-target=${ mngNo }] p:last-child`).text(like);
	$(`.liComm[data-target=${ mngNo }] p:last-child`).text(comment);
};	// 독립유공자 DB 출력 함수 end

// 프로필 사진 없는 경우 함수
function noProfile() {
	$(".yesProfile").on("error", function() {
		let sex = $(this).data("target");
		if (sex == "여") {
			$(this).attr("src", "/image/female.svg");
		}
		else {
			$(this).attr("src", "/image/male.svg");
		}
		$(this).attr("class", "noProfile");
	});
};	// 프로필 사진 없는 경우 함수 end

// page 생성 함수
function makePage(totalCnt, divNum, page) {
	// totalPage 계산
	let totalPage = Math.floor(totalCnt / divNum);
	let pageCnt = 10;
	if (totalCnt % divNum != 0) {
		totalPage++;
	}
	
	// 시작과 끝 페이지 계산
	let calc = Math.floor(page / pageCnt);
	if (page % pageCnt == 0) {
		calc--;
	}
	let startPage = calc * pageCnt + 1;
	let lastPage = startPage + pageCnt - 1;
	
	// 이전 페이지 이동 버튼 출력
	let result = "";
	result += `<button
					class="pageChange strongPrev"
					data-total="${ totalCnt }"
					data-div="${ divNum }"
					onclick="pageChange(this)"
				>《</button>`;
	result += `<button
					class="pageChange prev"
					data-total="${ totalCnt }"
					data-div="${ divNum }"
					onclick="pageChange(this)"
				>〈</button>`;
	
	// 페이지 이동 버튼 출력
	result += `<div id="pageDiv">`;
	let temp = "";
	for(let p = startPage; p <= lastPage; p++) {
		if (p > totalPage) {
			break;
		}
		else {
			temp = `<button
						class="pageBtn"
						data-target="${ p }"
					>${ p }</button>`;
			result += temp;			
		}
	}
	result += `</div>`;
	
	// 다음 페이지 이동 버튼 출력
	result += `<button
					class="pageChange next"
					data-total="${ totalCnt }"
					data-div="${ divNum }"
					onclick="pageChange(this)"
				>〉</button>`;
	result += `<button
					class="pageChange strongNext"
					data-total="${ totalCnt }"
					data-div="${ divNum }"
					onclick="pageChange(this)"
				>》</button>`;	
	return result;
}

// page active 함수
function pageActive(sort, page) {
	// 기존 active class 제거
	$(".sortA, .pageBtn").removeClass("pageActive");
	
	// active class 생성
	$(`.sortA[data-target='${ sort }']`).addClass("pageActive");
	$(`.pageBtn[data-target='${ page }']`).addClass("pageActive");
	$("#activePage").text(page);
}

// page change 함수
function pageChange(button) {
	// 현재 페이지
	let activePage = $("#activePage").text();
	let nowPage = parseInt($("#pageDiv button:first-child").text());
	
	// totalPage 계산
	let totalCnt = $(button).data("total");
	let divNum = $(button).data("div");
	let totalPage = Math.floor(totalCnt / divNum);
	let pageCnt = 10;
	if (totalCnt % divNum != 0) {
		totalPage++;
	}
	
	// 첫번째로 가기
	if ($(button).hasClass("strongPrev")) {
		if (nowPage > 1) {
			$(".pageNums").html(makePage(totalCnt, divNum, 1));
		}
	}
	// 마지막으로 가기
	else if ($(button).hasClass("strongNext")) {
		if (nowPage < totalPage) {
			$(".pageNums").html(makePage(totalCnt, divNum, totalPage));
		}
	}
	// pageCnt만큼 이전으로 가기
	else if ($(button).hasClass("prev")) {
		let go = nowPage - pageCnt;
		if (go > pageCnt) {
			$(".pageNums").html(makePage(totalCnt, divNum, go));
		}
		else {
			$(".pageNums").html(makePage(totalCnt, divNum, 1));
		}
	}
	// pageCnt만큼 다음으로 가기
	else if ($(button).hasClass("next")) {
		let go = nowPage + pageCnt;
		if (go < totalPage) {
			$(".pageNums").html(makePage(totalCnt, divNum, go));
		}
		else if (totalPage > 0) {
			$(".pageNums").html(makePage(totalCnt, divNum, totalPage));
		}
	}
	
	// 현재 페이지 active
	$(`.pageBtn[data-target='${ activePage }']`).addClass("pageActive");
}

// 독립유공자 리스트 조회 함수
function getList(whatAPI, sort, page, ncpp) {
	let mngNoList = [];
	$("#listUl").html("");
	
	// 기본순
	if (sort == "default") {
		$.ajax({
			url: "https://e-gonghun.mpva.go.kr/opnAPI/" + whatAPI + "List.do"
				+ "?nPageIndex=" + page + "&nCountPerPage=" + ncpp
				+ "&type=JSON",
			type: "get",
			dataType: "json",
			success: function(data) {
				// 총 개수
				let totalCnt = data.TOTAL_COUNT;
				$("#liCnt span").text(totalCnt);
				
				// 페이지
				$(".pageNums").html(makePage(totalCnt, ncpp, page));
				pageActive("default", page);
				
				// API 리스트 조회
				for (let i = 0; i < data.ITEM_COUNT; i++) {
					// ul에 추가
					let item = data.ITEMS[i];
					let il = new indeList(item.MNG_NO, item.NAME_KO, item.SEX, item.WORKOUT_AFFIL);
					$("#listUl").append(il.printLi());
					
					// list에 추가
					mngNoList.push(item.MNG_NO);
					
					// 사진 없으면
					noProfile();
				}	// for end
				
				// DB 조회
				$.ajax({
					url: "/list/DB",
					data: {mngNoList: mngNoList},
					type: "post",
					dataType: "json",
					success: function(data) {
						for (let i = 0; i < data.length; i++) {
							// 존경, 댓글 출력
							likeComment(data[i].mng_no, data[i].like, data[i].comment);
						}	// for end
					}	// success end
				});	// DB 조회 ajax end
			}	// success end
		});	// ajax end
	}	// if end
	
	// 정렬
	else {
		$.ajax({
			url: "/list/DB/sort",
			data: {sort: sort, page: page, divNum: ncpp},
			type: "post",
			dataType: "json",
			success: function(data) {
				// 총 개수
				let totalCnt = data.cnt;
				$("#liCnt span").text(totalCnt);
				
				// 페이지
				$(".pageNums").html(makePage(totalCnt, ncpp, page));
				pageActive(sort, page);
				
				// 리스트 출력
				getListFor(data.list);
			}	// success end
		});	// ajax end
	}	// else end
}	// getList end

// 비동기식 ajax 조회 함수
async function getListFor(list) {
	for (let i = 0; i < list.length; i++) {
		await $.ajax({
			url: "https://e-gonghun.mpva.go.kr/opnAPI/publicReportList.do"
				+ "?nPageIndex=1&nCountPerPage=1&type=JSON"
				+ "&mngNo=" + list[i].mng_no,
			type: "get",
			dataType: "json",
			success: function(data) {
				// ul에 추가
				let item = data.ITEMS[0];
				let il = new indeList(item.MNG_NO, item.NAME_KO, item.SEX, item.WORKOUT_AFFIL);
				$("#listUl").append(il.printLi());
				
				// 사진 없으면
				noProfile();
				
				// 존경, 댓글 출력
				likeComment(list[i].mng_no, list[i].like, list[i].comment);
			}	// success end
		});	// ajax end
	}	// for end
}	// getListFor end