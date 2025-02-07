<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	$(function(){
		//비동기식으로 서버에 접속하여 문자열 전송받기
		$("#ajaxString").click(function(){
			
			var params = "no=3344&name=이순신";
			var params2 = {
					no:5566,
					name:'이순신'
			}
			$.ajax({
				url:'/myapp/ajax/ajaxString',//매핑주소
				data : params, //서버로 보낼데이터
				type : 'get',//전송방식 get or post
				success:function(results){//정상응답받았을때
					$("#view").html(results);
				},
				error:function(error){//응답받을때 에러가 발생하면
					console.log(error.responseText);	
				}
			});
		});
	//form의 데이터를 비동기식으로 서버로 보내 회원가입하기
	$("#memberForm").on('submit',function(){
		var url ="/myapp/ajax/ajaxObject";
		//serialize(): form의 데이터를 쿼리데이터로 생성하는 함수
		//form의 데이터를 쿼리데이터로 생성하는 함수
		var params = $("#memberForm").serialize();
		console.log(params);
		$.ajax({
			url:url,
			data:params,
			type:"POST",
			success:function(results){
				console.log(results);
				var tag = "<table class='table'>";
				tag +="<tr><td>아이디</td><td>"+ results.userid+"</td></tr>";
				tag +="<tr><td>이름</td><td>"+ results.username+"</td></tr>";
				tag +="<tr><td>연락처</td><td>"+ results.tel+"</td></tr>";
				tag +="<tr><td>등록일</td><td>"+ results.writedate+"</td></tr></table>";
				
				$("#view").append(tag);
			},error:function(e){
				console.log(e);
				console.log(e.responseText);
			}
			});
		
			//홈태그에는 
			//페이지 이동을 해제해야 한다.
			event.preventDefault();
		});
	
		//페이지, 검색키, 검색어를 이용하여 목록 선택하기를 비동기식으로 처리
		$("#bbsList").submit(function(){
			event.preventDefault();
			var params = $("#bbsList").serialize();
			console.log(params);
			$.ajax({
				url : "/myapp/ajax/ajaxList",
				data : params,
				type : "GET",
				success:function(results){
					console.log(results);
					
					var tag = "<table class='table table-dark'>";
					//each() : 배열을 반복처리하는 함수
					//					index, 값(vo)
					$(results).each(function(i, obj){
						tag += "<tr><td>"+obj.news_no +"</td>";
						tag += "<td>"+obj.subject +"</td>";
						tag += "<td>"+obj.userid +"</td>";
						tag += "<td>"+obj.hit +"</td>";
						tag += "<td>"+obj.writedate +"</td></tr>";
					});
					tag += "</table>";
					$("#view").append(tag);
				},error:function(e){
					console.log(e.responseText);
				}
			});
			
		});
		
		//비동기식으로 Map리턴받기
		$("#ajaxMap").click(function(){
			var param = $("#bbsList").serialize();
			$.ajax({
				url : "/myapp/ajax/ajaxMap",
				data : param,
				type : "GET",
				success : function(results){
					
				}, error:function(e){
					console.log(e.responseText);
				}
			});
		});
	});
</script>
<div class="container">
	<h1>ajax를 이용한 비동기처리</h1>
	<button id="ajaxString">AJAX-String</button>
	<hr/>
	<form method="post" id="memberForm">
		아이디 : <input type="text" name="userid" id="userid"/><br/>
		비밀번호 : <input type="password" name="userpwd" id="userpwd"/><br/>
		이름 : <input type="text" name="username" id="username"/><br/>
		연락처 : <input type="text" name="tel1" id="tel1"/>-
		<input type="text" name="tel2" id="tel2"/>-
		<input type="text" name="tel3" id="tel3"/><br/>
		<input type="submit" value="전송하기"/>
	</form>
	<div>
		<form method="post" id="bbsList">
			<select name="nowPage">
				<script>
					for(var i=1; i<=10; i++){
						document.write("<option>"+i+"</option>");
					}
				</script>
			</select>
			<input type="text" name="searchKey" value="subject"/>
			<input type="text" name="searchWord"/>
			<input type="submit" value="검색"/>
			<input type="button" value="AJAX-Map" id="ajaxMap" />
		</form>
	</div>
	<hr/>
	<div id ="view" style="background:#ddd; padding:10px;"></div>
</div>