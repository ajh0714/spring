<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function delCheck(){
		// alert, confirm(y,n), prompt(value)
		
		if(confirm("현재글을 삭제하시겠습니까?")){//확인: true, 삭제
			location.href="${pageContext.request.contextPath}/bbs/del?news_no=${vo.news_no}"
		}
	}
</script>
<div class="container">
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.news_no }</li>
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
</div>