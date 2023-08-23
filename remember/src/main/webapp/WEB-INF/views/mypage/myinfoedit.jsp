<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/css/style.css" rel=stylesheet>
<link href="/css/mypage/myinfo.css" rel=stylesheet>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(function() {
		const id = document.getElementById("memId");
		const pw = document.getElementById("password");
		
		function isPasswordValid(password) {
			// 조건 1: 영문대소문자 + 숫자 조합
			const regex1 = /^(?=.*[a-zA-Z])(?=.*\d).{8,16}$/;
			// 조건 2: 영문대소문자 + 특수문자 조합
			const regex2 = /^(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;
			// 조건 3: 숫자 + 특수문자 조합
			const regex3 = /^(?=.*\d)(?=.*[\W_]).{8,16}$/;
			
			return regex1.test(password) || regex2.test(password) || regex3.test(password);
		}

		// 다른 곳 클릭 시 비밀번호 유효성 검사
		$(pw).on('blur', function() {
		    var pwValue = $(this).val();
		    if (!isPasswordValid(pwValue)) {
		        alert("비밀번호 입력 조건을 만족해주세요.");
		    }
		});
		
		// 비밀번호 일치 여부 확인
	      $(document).on('click', 'input:not(#memPw_confirm)', function(e) {
	         var pwConfirmValue = $("#memPw_confirm").val();
	         var pwValue = $("#memPw").val();
	         if (pwValue !== '' && pwConfirmValue !== '' && pwConfirmValue !== pwValue) {
	             alert("비밀번호가 일치하지 않습니다.");
	             $("#memPw_confirm").focus();
	             
	         }
	      });
		
				$("#editBtn").on(
						'click',
						function(e) {
							e.preventDefault();
							
							var memPw =  $("#memPw").val();
							var memPw_confirm = $("#memPw_confirm").val();
				            
				            if (memPw === "") {
				                alert("비밀번호를 입력해 주세요.");
				                $("#memPw").focus();
				            } else if(memPw_confirm === ""){
				            	alert("비밀번호 확인란을 입력해 주세요.");
				            	$("#memPw_confirm").focus();
				            } else if(memPw !== memPw_confirm) {
				            	alert("비밀번호가 일치하지 않습니다.");
				            	 $("#memPw_confirm").focus();
			            	} else {
				            
							$.ajax({
								url : '/editinfo',
								data : {
									'password' : memPw
								},
								type : 'post',
								success : function(res) {
									alert("회원정보 수정이 완료되었습니다.");
									location.href = "/myinfo";
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText + "\nerror="
											+ e);
								}
							});
				            }
						});
			});
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="my_wrap">
		<div class="my_title">
			<p>회원 정보 수정</p>
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
				<form id="editForm" action="/myinfoedit" method="POST">
					<div class='myedit'>
						<p>아이디</p>
						<input type="text" name="memId" id="memId" value="${user.id }" disabled>
					</div>
					<div class='myedit'>
						<p>비밀번호*</p>
						<input type="password" name="memPw" id="memPw" value="${user.password }"
							maxlength="16"> <a class="help">(영문 대소문자/숫자/특수문자 중
							2가지 이상 조합, 8자~16자)</a>
					</div>
					<div class='myedit'>
						<p>비밀번호 확인*</p>
						<input type="password" name="memPw_confirm" id="memPw_confirm"
							value="" autocomplete="off" maxlength="16">
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
					<div class='myedit'>
						<button type="button" id="editBtn">수정 완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>