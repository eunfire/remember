<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/css/style.css" rel=stylesheet>
<link href="/css/mypage/myinfo.css" rel=stylesheet>
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(
		function() {
			$("#editUser").on('click', function() {
				location.href = "/myinfoedit"
			});

			$("#deleteUser").on(
					'click',
					function() {
						if (confirm("정말로 탈퇴하시겠습니까?")) {
							$.ajax({
								url : '/myinfodelete',
								
								type : 'get',
								success : function(res) {
									alert("회원 탈퇴가 완료되었습니다.");
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText
											+ "\nerror=" + e);
								}
							});
						} else {
							alert("회원 탈퇴를 취소합니다.");
						}

					});

		});
</script>
<title>MyInfo</title>
</head>
<body>
	<div class="my_wrap">
		<div class="my_title">
			<p>회원 정보</p>
		</div>
		<div class="my_content">
			<div class="top">
				<div class="menu1">
					<a href="/myinfo">회원 정보</a>
				</div>
				<div class="menu2" style="color: var(--gray3)">
					<a href="/mylike">존경해요</a>
				</div>
				<div class="menu3" style="color: var(--gray3)">
					<a href="/mycomment">작성 댓글</a>
				</div>
			</div>
			<div class="myinfo">
				<div class='myedit'>
					<p>아이디</p>
					<input type="text" name="memId" id="memId" value="${user.id}" disabled>
				</div>
				<div class='myedit'>
					<p>비밀번호</p>
					<input type="password" name="memPw" id="memPw" value="${user.password }"
						disabled> <a class="help">(영문 대소문자/숫자/특수문자 중 2가지 이상
						조합, 8자~16자)</a>
				</div>
				<div class='myedit'>
					<p>이름</p>
					<input type="text" name="memName" id="memName" value="${user.name }"
						disabled>
				</div>
				<div class='myedit'>
					<p>E-mail</p>
					<input type="tel" name="memEmail" id="memEmail" value="${user.email }"
						disabled>
				</div>
			</div>
			<div class="bt_wrap">
				<button type="button" id="editUser">개인정보 수정</button>
				<button id="deleteUser">회원 탈퇴</button>
			</div>
		</div>
	</div>
</body>
</html>