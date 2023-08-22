function check (){
	var getID = RegExp(/^[a-zA-Z0-9]{4,12}$/);
    var getkorname = RegExp(/^[가-힣]+$/);
    var getengname = RegExp(/^[a-zA-Z]+$/);
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    var getCheck = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/);

    
    if(!getID.test($("#id").val())){
        alert("ID형식을 확인해주세요");
        $("#id").val("");
        $("#id").focus();
        return false;
    }

    if(getkorname.test($("#name").val()) || getengname.test($("#name").val())){
        
     }
 
     else{
         alert("성함을 정확히 입력해주세요");
         $("#name").val("");
         $("#name").focus();
         return false;
     }
    


    if(!getMail.test($("#email").val())){
        alert("이메일형식에 맞게 입력해주세요");
        $("#mail").val("");
        $("#mail").focus();
        return false;
    }
    

    if(!getCheck.test($("#pw").val())){
        alert("비밀번호 확인을 확인해주세요. 숫자,영문,특수문자 포함 8~15자로 입력해주세요");
        $("#pw").val("");
        $("#pw").focus();
        return false;
    }
    
    if(!getCheck.test($("#pw2").val())){
        alert("비밀번호 확인을 확인해주세요. 숫자,영문,특수문자 포함 8~15자로 입력해주세요");
        $("#pw2").val("");
        $("#pw2").focus();
        return false;
    }

    if ($("#pw").val() != $("#pw2").val()){
        alert("비밀번호가 상이합니다.");
        $("#pw").val("");
        $("#pw2").val("");
        $("#pw").focus();
    }
    
    alert("회원가입이 완료되었습니다.");
    
    


    
}