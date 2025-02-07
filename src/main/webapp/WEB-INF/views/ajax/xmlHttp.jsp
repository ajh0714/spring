<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	// 비동기식으로 서버에 있는 data,txt의 내용을 받아와 view에 표시하기
	function loadDocument(){
		//서버에 있는 data.txt파일의 내용가져오기
		
		//1. 비동기처리를 하는 클래스를 객체 생성
	 	var xHttp = new XMLHttpRequest();
		
		//2. 응답받았을 때 실행할 함수
		xHttp.onreadystatechange = function(){
			//this.readyState : 0->초기화실패, 1:서버와 연결, 2:요청이 접수, 3:처리요청, 4:요청완료
			console.log('readyState', this.readyState);
			//this.status : 200->정상처리, 403:금지, 404:찾을수없음
			console.log('status', this.status);
			//this.responseText
			console.log('responseText', this.responseText);
			if(this.readyState==4 && this.status==200){
				document.getElementById("view").innerHTML = this.responseText;
			}else{
				document.getElementById("view").innerHTML = "요청한 자료가 없습니다.";
			}
			
		}
		
		//3. 요청방식
		//		   전송방식,  가져올 파일명           , 비동기식
		xHttp.open("GET","/myapp/resources/data.txt", true);
		
		//4.서버에 요청서 보낸다.
		xHttp.send();
	}
	function loadDocument2(){
		var xHttp = new XMLHttpRequest();
		
		xHttp.onreadystatechange = function(){
			if(this.readyState==4 && this.status==200){
				console.log(this.responseText);
				//Json형식으로 된 문자열을 jso
				var jsonData = JSON.parse(this.responseText);
				console.log(jsonData);
				
				var tag = "<table class='table table-dark'>";
				tag += "<tr><td>이름</td><td>"+ jsonData.username+"</td></tr>";
				tag += "<tr><td>연락처</td><td>"+ jsonData.tel+"</td></tr>";
				tag += "<tr><td>이메일</td><td>"+ jsonData.email+"</td></tr>";
				tag += "<tr><td>주소</td><td>"+ jsonData.addr+" "+jsonData.addDetail+"</td></tr></table>";
				
				document.getElementById("view").innerHTML = tag;
			}	
		}
		xHttp.open("GET", "/myapp/ajax/xmlHttpTest?userid=goguma", true);
		xHttp.send();
	}
	function loadDocument3(){
		var xHttp = new XMLHttpRequest();
		xHttp.onreadystatechange = function(){
			if(this.readyState==4 && this.status==200){
				console.log(this.responseText);
				var jsonData = JSON.parse(this.responseText); //JSON.stringfy()
				
				var tag = "<table class='table table-dark'>"
				for(var i=0; i<jsonData.length; i++){
					tag += "<tr><td>"+ jsonData[i].news_no +"</td>";
					tag += "<td>"+ jsonData[i].subject +"</td>";
					tag += "<td>"+ jsonData[i].userid +"</td>";
					tag += "<td>"+ jsonData[i].hit +"</td>";
					tag += "<td>"+ jsonData[i].writedate +"</td></tr>";
				};
				tag += "</table>";
				document.getElementById("view").innerHTML = tag;
			}
		}
		xHttp.open("POST","/myapp/ajax/xmlHttpPost", true);
		xHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); //post방식의 전송은 header를 설정하여야 한다.
		xHttp.send("subject=아작스로 보내는 글1&content=AJAX로 쓴 글내용");
	}
	//window.onload = loadDocument;
</script>
<div class="container">
	<h1>XMLHttpRequest를 이용한 비동기 처리</h1>
	<button onclick="loadDocument()">서버에서 txt문서의 내용을 가져온다.</button>
	<button onclick="loadDocument2()">서버에 GET 데이터보내고 받기</button>
	<button onclick="loadDocument3()">서버에 POST방식으로 데이터보내고 받기</button>
	
	<!--  서버에서 비동기식으로 가져온 정보를 표시할 곳 -->
	<div id="view" style="background-color:beige; border:1px solid #ddd; padding:10px"></div>
</div>