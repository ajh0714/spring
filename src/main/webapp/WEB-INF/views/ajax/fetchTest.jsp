<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	function fetchGetTest(){
		//1. fetch()는 매핑주소와 서버로 보낼 데이터 및 옵션을 기술한다.
		fetch("/myapp/fetch/getTest?userid=ggggg&username=홍길동",{method:'GET'})
		//2. 서버에서 응답을 받으면 실행되는 부분		
		.then(response=>{
				return response.json();// 응답받은 결과를 json으로 피싱한다.	
			}
		
		)
		//3. 서버에서 응답받은 정보(데이터)를 처리하는곳
		.then(data=>{
			console.log(data);
			document.getElementById("fetchView").innerHTML = data.userid+", "+data.username+", "+data.email;
			
		}
		//4.요청과정에서 오류가 발생하면 실행되는 부분
		).catch(error=>{
			console.log('Error발생',error);
		});
	}
	// post방식전송을 이용한 비동기 처리
	// bbs목록 가져오기(페이지:nowPage)
	function fetchPostTest(){
		
		fetch("/myapp/fetch/fetchPostBbsList",{
			method:"POST",
			headers:{
				"Content-Type":"application/x-www-form-urlencoded"
			},
			body:"nowPage=5"
		}).then(response =>{
			return response.json();
		}).then(data=>{
			console.log(data);
			var tag = "<table class='table'><tr><td>번호</td><td>제목</td><td>등록일</td></tr>";
			for(var record of data){
				tag += "<tr><td>"+record.news_no+"</td><td>"+record.subject+"</td><td>"+record.writedate+"</td></tr>"
			}
			tag +="</table>";
			
			document.getElementById("fetchView").innerHTML = tag;
		}).catch(error => console.log('Error 발생함....',error));
	}
</script>
<div class="container">
	<h1>fetch를 이용한 비동기 처리</h1>
	<button onclick="fetchGetTest()">Get방식을 접속하기</button>
	<button onclick="fetchPostTest()">Post방식을 접속하기</button>
	<hr/>
	<div id="fetchView" style="background:pink; padding:10px;"></div>
</div>