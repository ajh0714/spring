<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <style>
        .container{
            width: 700px; margin: 0 auto; overflow: auto;background-color: beige;
        }
        .container>h1{
            text-align: center; margin: 100px 0 50px; 
        }
        #frm li{
            float: left;
            width: 80%;
            line-height: 40px;
            border-bottom: 1px solid #ddd;
        }
        #frm li:nth-child(2n){
            width: 80%;
        }
        #frm li:nth-child(2n+1){
            width: 20%;
        }
        #tel2, #tel3, #zipcode{
            width:50px;
        }
        #frm li:last-of-type{
            width: 100%;
            text-align: center;
        }
        input{
        	line-height:20px;
        }
    </style>
<script>

// 아이디 중복검사 (비동기식으로 구현)
	function idDuplicate(){
	var userid = document.getElementById("userid").value;
	//아이디가 입력된경우
	if(userid!=""){
			//비동기식으로 서버에 아이디를 보내고 DB조회하여 존재유무를 리턴받는다.
			var xHttp = new XMLHttpRequest();
			
			//응답받으면
			xHttp.onreadystatechange = function(){
				if(this.readyState==4 && this.status==200){
					if(this.responseText=="1"){
						document.getElementById("idCheck").innerHTML = "<span style='color:red'>사용불가능한 아이디입니다.</span>";
						document.getElementById("idCheckStatus").value='N';
					}else{
						document.getElementById("idCheck").innerHTML = "<span style='color:green'>사용가능한 아이디입니다.</span>";
						document.getElementById("idCheckStatus").value='Y';
					}
				}
				else{
				}
			}
			
			xHttp.open("GET","/myapp/member/idDuplicate?userid="+userid,true);
			
			xHttp.send();
		}else{
			alert("아이디를 입력후 중복검사하세요.");
		}
	}
	function idDuplicateStatus(){
		document.getElementById("idCheckStatus").value="N";
	}
	fucntion formCheck(){
		if(document.getElementById("idCheckStatus")=='N'){
		alert("아이디중복 검사를 하세요..");	
		return false;
		}
	}
</script>	
		
 <div class="container">
        <h1>회원가입 폼</h1>
        <form method="post" action="/myapp/member/formOk" id="frm">
            <ul>
                <li>아이디</li>
                <li><input type="text" name="userid" id="userid" onkeyup="idDuplicateStatus()"/>
                    <input type="button" value="아이디중복확인" onclick="idDuplicate()">
                    <span id="idCheck"></span>
                    <input type="hidden" id="idCheckStatus" value="N"/>
                </li>
                <li>비밀번호</li>
                <li><input type="password" name="userpwd" id="userpwd"></li>
                <li>비밀번호 확인</li>
                <li><input type="password" name="pwd2" id="pwd2"></li>
                <li>이름</li>
                <li><input type="text" name="username" id="username"></li>
                <li>연락처</li>
                <li><select name="tel1">
                        <option>010</option>
                        <option>02</option>
                        <option>031</option>
                        <option>032</option>
                        <option>041</option>
                </select>
                -
                <input type="text" name="tel2" id="tel2">
                -
                <input type="text" name="tel3" id="tel3"></li>
                <li>이메일</li>
                <li><input type="email" name="email" id="email"></li>
                <li>우편번호</li>
                <li><input type="text" name="zipcode" id="zipcode">
                    <input type="button" value="우편번호찾기"></li>
                <li>주소</li>
                <li><input type="text" name="addr" id="addr"></li>
                <li>상세주소</li>
                <li><input type="text" name="addDetail" id="addDetail"></li>
                <li>취미</li>
                <li><input type="checkbox" name="hobby" value="야구">야구
                    <input type="checkbox" name="hobby" value="축구">축구
                    <input type="checkbox" name="hobby" value="바이크">바이크
                    <input type="checkbox" name="hobby" value="쇼핑">쇼핑
                    <input type="checkbox" name="hobby" value="자전거">자전거
                    <input type="checkbox" name="hobby" value="걷기">걷기
                    <input type="checkbox" name="hobby" value="영화감상">영화감상  
                </li>
                <li><input type="submit" name="hwp" value="회원가입하기">
                	<input type="reset" name="type" value="다시쓰기">
                </li>

            </ul>
        </form>
    </div>