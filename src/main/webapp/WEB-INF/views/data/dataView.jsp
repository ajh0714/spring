<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function delCheck(){
		if(confirm("글을 삭제하시겠습니까?")){
			location.href="${pageContext.request.contextPath}/data/dataDel?no=${vo.no}";
		}
	}
</script>
<div class="container">
	<h1>자료실 글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }, 조회수 : ${vo.hit }, 등록일 : ${vo.writedate }</li>
		<li>제목 : ${vo.subject }</li>
		<li>글내용<br/>
			${vo.content }</li>
		<li>첨부파일<br/> 
			<a href="${pageContext.request.contextPath}/uploadfile/${vo.filename}" download>
				<img src="${pageContext.request.contextPath}/uploadfile/${vo.filename}"/>
			</a>
		</li>
	</ul>
	<c:if test="${logid==vo.userid }">
	<div>
		<a href="${pageContext.request.contextPath}/data/dataEdit?no=${vo.no}">수정</a>	
		<a href="javascript:delCheck()">삭제</a>
	</div>
	</c:if>
</div>
