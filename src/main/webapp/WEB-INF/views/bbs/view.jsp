<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function delCheck(){
		// alert, confirm(y,n), prompt(value)
		
		if(confirm("현재글을 삭제하시겠습니까?")){//확인: true, 삭제
			location.href="${pageContext.request.contextPath}/bbs/del?news_no=${vo.news_no}"
		}
	}
	
	//댓글 비동기 처리
	$(function(){
		//댓글목록 : 비동기로 댓글목록 가져오기---------------------------------------
		function replyList(){
			//기존 목록 지우기
			$("#replyList").html("");
			//DB에서 현재글과 관련있는 댓글목록을 가져오기
			
			var params = {
					news_no:${vo.news_no}
			};
			$.ajax({
				url : "/myapp/reply/bbsReplyList",
				type: "GET",
				data : params,
				success : function(results){
					console.log(results);
					
					$(results).each(function(idx, rVO){
					var tag =
					`<p>
				 		<b>`+rVO.userid+`</b>(`+ rVO.writedate +`)`
				 		// 본인이 쓴 댓글일 때 수정 삭제 버튼을 표시한다
				 		// 현재 생성되는 댓글쓴이와 로그인 한 아이디가 일치하면 
				 		if(rVO.userid == '${logid}'){
				 			tag += `
				 				<input type='button' value ='Edit'/>
				 				<input type='button' value ='Del' title="`+rVO.reply_no+`"/>
				 				
				 				<div style = 'display:none'>
				 					<form method='post'>
				 						<input type='hidden' name='reply_no' value='`+ rVO.reply_no +`'/>
				 						<textarea name='comment' style='width:500px;height:100px;'>`+ rVO.comment +`</textarea>
				 						<input type='submit' value='댓글수정하기'/>
				 					</form>
				 				</div>
				 				`;
				 		}
				 		tag += `<div style="font-weight:bold">`+ rVO.comment +`</div>
				 		<hr/>
					</p>`;
					$("#replyList").append(tag);
					});
					
				}, error : function(e){
					console.log(e.responseText);
				}
			});
		}
		//댓글등록 ---------------------------------------
		$("#bbsReplyWrite").click(function(){
			if($("#comment").val()==""){
				alert("댓글을 입력후 등록하세요..");
				return false;
			}
			//var params = "news_no=${vo.news_no}&comment="+$("comment").val(); // news_no=35&comment=입력한 댓글
			var params = {
					news_no:${vo.news_no},
					comment:$("#comment").val()
			}
			$.ajax({
				url : "/myapp/reply/bbsReplyWrite",
				type:"POST",
				data:params,
				success:function(results){
					console.log(results);
					if(results=="1"){
						$("#comment").val("");
					}else{
						alert("댓글등록이 등록되지 않았습니다.");
					}
					//댓글등록 출력
					replyList();
				},error:function(error){
					console.log(error.responseText);
				}
			});
			
		});
		//댓글수정폼 보여주기 : Edit버튼 클릭시
	/* 	$("input[value=Edit]").click(function(){
			
		}); */
		//				이벤트종류    대상     콜백함수
		$(document).on('click','input[value=Edit]',function(){
			//아이디,날짜들 숨기기
			$(this).parent().css('display','none');
			//수정을 보여주기
			$(this).parent().next().css("display","block")
			$(this).parent().next().next().css('display','none');
		});
		
		//댓글수정(DB update -> 리스트)
		$(document).on('submit','form',function(){
			event.preventDefault(); //기본이베튼 제거하기 a태그,form태그 페이지를 이동하는 기본이벤트가 있으며,
									//이를 해제하여야 페이지를 이동하기 않는다.
			var params = $(this).serialize(); //데이터준비 reply_no=2&comment=jfdkslfjd
			
			$.ajax({
				url : "/myapp/reply/bbsReplyEdit",
				data : params,
				type : "POST",
				success : function(results){
					if(results=="0"){
						alert("댓글이 수정되지 않았습니다.");
					}else{
						replyList();
					}
				},error : function(error){
					console.log(error.responseText);
				}
			});
		});
		//댓글삭제 ---------------------------------------
		$(document).on('click','input[value=Del]',function(){
			if(confirm("댓글을 삭제하시겠습니까?")){
				
				//삭제할 레코드 정보 구하기
			var params = {
					reply_no: $(this).attr("title")
			}
			$.ajax({
				url : '${pageContext.request.contextPath}/reply/bbsReplyDelete',
				data : params,
				type : "GET",
				success: function(results){
					console.log(results, typeof(results));
					replyList();
				},error : function(error){
					console.log(error.responseText);
				}
			});
			}
		});
		//댓글목록출력 : 처음 글내용보기로 오면 댓글목록이 나와야 함.
		replyList();
	});
	
</script>
<style>
	#comment{
		width:500px; height:100px;
	}
</style>
<div class="container">
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.news_no}</li>
	<li>작성자 : ${vo.userid}, 조회수 : ${vo.hit }, 등록일 : ${vo.writedate}</li>
	<li>제목</li>
	<li>${vo.subject}</li>
	<li>글내용</li>
	<li>${vo.content}</li>
</ul>
<div>
	<c:if test="${logid==vo.userid }">
		<a href="${pageContext.request.contextPath}/bbs/edit?news_no=${vo.news_no}">수정</a>
		<a href="javascript:delCheck()">삭제</a>
	</c:if>	
</div>
<!-- 댓글 ares-->
<div style="padding:10px 0">
	<c:if test="${logStatus=='Y'}">
		<!-- 댓글등록(로그인 상태인 경우 댓글폼이 보이도록 함.) -->
		<p>
		<textarea name="comment" id="comment"></textarea>
		<button id="bbsReplyWrite">댓글등록</button>
		</p>
	</c:if>
	<!-- 댓글목록 -->
	<div style="background:#ddd;">댓글목록</div>
	<div id="replyList">
	</div>
</div>
</div>