<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/image/icon.png" rel="icon">
<link href="/css/style.css" rel=stylesheet>
<link href="/css/mypage/myinfo.css" rel=stylesheet>
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
		$(document).ready(function() {
			
			// 독립운동가 리스트 생성자
			function mainList(mngNo, name, sex, work) {
				this.mngNo = mngNo;
				this.sex = sex;
				this.work = work;
				if (name.length > 6) {
					this.name = name.substring(0, 6) + "...";
				}
				else {
					this.name = name;
				}
				this.printLi = function() {
					return `
						<li class="mainLi" data-target="\${ this.mngNo }">
							<div class="mainLiCaption">
								<p>\${ this.mngNo }</p>
								<p>\${ this.work }</p>
							</div>
			    			<div class="profileWrapper">
			    				<img src="https://e-gonghun.mpva.go.kr/hise/ua/getImage.do?mngNo=\${ this.mngNo }&type=CH" data-target="\${ this.sex }">
			    			</div>
			    			<h2>\${ this.name }</h2>
		    			</li>
					`;	// return end
				};	// printMonthLi end
			};	// 독립운동가 리스트 생성자 end
			
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
					$(this).attr("class", "noProfile")
				});
			}	// 프로필 사진 없는 경우 함수 end
			
			// 독립운동가 리스트 click event
			$(document).on("click", ".mainLi", function() {
				window.location.href = "/detail?mng_no=" + $(this).data("target");
			});
			
			// 이달의 독립운동가 api
			let ncpp = 5;	// nCountPerPage			
			// 모든 독립유공자 존경해요순 조회
			async function getLikeList(list) {
				for (let i = 0; i < list.length; i++) {
					let mngNo = list[i];
					
					await $.ajax({
						url: "https://e-gonghun.mpva.go.kr/opnAPI/publicReportList.do"
							+ "?nPageIndex=1&nCountPerPage=1&type=JSON"
							+ "&mngNo=" + mngNo,
						type: "get",
						dataType: "json",
						success: function(data) {
							// ul에 추가
							let item = data.ITEMS[0];
							let ml = new mainList(item.MNG_NO, item.NAME_KO, item.SEX, item.WORKOUT_AFFIL);
							$("#mainAllUl").append(ml.printLi());
							
							// 사진 없으면
							noProfile();
						}	// success end
					});	// ajax end
				}	// for end
			}	// getLikeList end
		
			// 함수 실행
			getLikeList(${ mngNoList });
		});	// document end
	</script>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div class="my_wrap">
		<div class="my_title">
			<p>존경해요</p>
		</div>
		<div class="my_content">
			<div class="top">
				<div class="menu1" style="color: var(--gray3)">
					<a href="/myinfo">회원 정보</a>
				</div>
				<div class="menu2">
					<a href="/mylike">존경해요</a>
				</div>
				<div class="menu3" style="color: var(--gray3)">
					<a href="/mycomment">작성 댓글</a>
				</div>
			</div>
			<div class="mylist">
				<ul id="mainAllUl"></ul>
				
				<div class="pageNums"></div><h1 id="activePage" style="display: none;"></h1>
			</div>
		</div>
	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>