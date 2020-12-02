<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/topmenu.jsp" %>
<%@include file="/resources/css/changePwdHead.jsp" %>
<script>

//로그인이 안된 상태면 로그인페이지로 넘어가게
let msg = '${msg}';
	if(msg != ""){
		alert(msg);
		location.href = '/login';
	}
	
//비밀번호 변경 성공, 실패여부 메세지
let success = '${success}';
	if(success != ""){
		alert(success);
}
</script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 비밀번호 변경</title>

</head>
<body>
    <div class="h-back">
        <div class="h-imgarea">
        <img id="h-backImg" src="/resources/img/zzan.JPG">

        </div><div class="h-pass-content">
            <h3 style="margin: 40px 0 40px 0;">비밀번호 변경</h3>
            <form action="/mypage/changepwd" method="post" onsubmit="return validate()">
                <p class="h-pwd-p"><input type="password" id ="pwd" name="pwd" placeholder="현재 비밀번호"></p>
                <p class="h-pwd-p" style="font-size:10px;">비밀번호는 대,소문자 특수문자 포함 5~16자리를 입력해주세요</p>
                <p class="h-pwd-p"><input type="password" id ="newpwd" name="newpwd" placeholder="새 비밀번호"></p>
                <p class="h-pwd-p"><input type="password" id="repwd" placeholder="새 비밀번호 확인" onblur="checkPwd()"></p>
                <p class="h-pwd-p" id="repwdmsg"></p>
                <div style="text-align: center;"><button type="submit" class="h-passbtn">변경</button> <button type="button" class="h-passbtn" onclick="location.href='/mypage'">취소</button></div>
            </form>
        </div>
        </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		const jPwd = /^(?=.*?[a-zA-Z])(?=.*?[#?!@$%^&*-]).{5,16}$/; // 대문자,소문자,특수문자 1개씩은 포함해서 5자리~16자리
		const blank = /\s/g;
    
		//1. 비밀번호와 비밀번호 확인이 일치하는지 확인
		function checkPwd(){
			if($("#repwd").val() == ""){
				 document.getElementById("repwdmsg").innerHTML = "비밀번호 확인을 입력해주세요";
			     document.getElementById("repwdmsg").style.color = 'red'; 
			     
			}else{
				
				if($("#newpwd").val() == $("#repwd").val()){
					document.getElementById("repwdmsg").innerHTML = "비밀번호가 일치합니다.";
		    		document.getElementById("repwdmsg").style.color = 'red'; 
				} 
			
				if($("#newpwd").val() != $("#repwd").val()){
					document.getElementById("repwdmsg").innerHTML = "비밀번호가 일치하지 않습니다 다시 입력해주세요";
		    		document.getElementById("repwdmsg").style.color = 'red'; 
				}
			}//else end
		}//fun end
		
		//2. 비밀번호 유효성검사
		function validate() {	
			
			if($("#pwd").val() == ""){
				alert('현재 비밀번호를 입력하여 주세요');
				$("#pwd").focus();
				return false;
			}
			
			 if(!jPwd.test($("#newpwd").val()) || blank.test($("#newpwd").val())){
				alert('비밀번호 형식에 맞지 않습니다');
				$("#newpwd").val("");
				$("#newpwd").focus();
				return false;
			}
			
			if($("#newpwd").val() != $("#repwd").val()){
				alert('비밀번호 확인과 일치하지 않습니다.');
				$("#repwd").val("");
				$("#repwd").focus();
				return false;
			} 
			return true;
		}
    </script>
</body>

</html>