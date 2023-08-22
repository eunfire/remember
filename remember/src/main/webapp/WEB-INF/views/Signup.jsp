<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기억하길</title>
<script src="/js/jquery-3.6.4.min.js"></script>
</head>
<body>
<form method="post" class="member-join" action="/signup">
 <h1 class="h1">회원가입</h1>
        <input
            type="text"
            id="id"
            name="user_id"
            alt="ID입력"
            placeholder="ID(영문,숫자 4~12자)"
            oninput="checkeID()"
            required
          />
          <div id="result-2" class="duID"></div>
          <input
            type="text"
            id="name"
            name="name"
            alt="이름입력"
            placeholder="성함(ex:홍길동 혹은 josh)"
            required
          />
        <input
            type="text"
            id="email"
            name="email"
            alt="이메일 입력"
            placeholder="이메일"
            oninput="return checkEmail()"
            required
          />
          <div id="result-2" class="duEmail"></div>
          <section>
        <input
          type="text"
          id="num"
          alt="인증번호 입력"
          placeholder="인증번호 입력"
          required
        />
        <input
          type="button"
          id="num-st"
          value="이메일 인증번호 발송"
          onclick="return numCheck1()" 
        />
        <input
          type="button"
          id="num-st1"
          value="인증번호 확인"
          onclick="return emailnum()" 
        />
        </section>
        <div><span id="result-1"></span><span class="time2"></span></div>

          <input
            type="password"
            class="secret-number"
            id="pw"
            name="password"
            alt="비밀번호 입력"
            placeholder="비밀번호(영문,숫자,특수문자 포함 8~15자)"
            required
          />
          <label class="secret-mark">
            <input
              type="checkbox"
              id="mark"
              name="secret-mark"
              value="mark"
            />
            <label class="pw-check">비밀번호표시</label>
            </label          >
          <input
            type="password"
            class="secret-number"
            id="pw2"
            alt="비밀번호 확인"
            placeholder="비밀번호 확인(영문,숫자,특수문자 포함 8~15자)"
            required
          />
          <label class="secret-mark">
            <input
              type="checkbox"
              id="mark1"
              name="secret-mark1"
              value="mark1"
            /><label class="pw-check">비밀번호확인표시</label></label
          >
          <input
            type="submit"
            class="join"
            id="join"
            value="회원 가입"
            onclick="return check()"
            disabled
          />
 </form>
</body>
<script src="/js/sendmail.js"></script>
<script src="/js/duplicatecheck.js"></script>
<script src="/js/signupcheck.js"></script>
<script src="/js/siguploginevent.js"></script>
<script src="/js/checkbox.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/address.js"></script>
</html>