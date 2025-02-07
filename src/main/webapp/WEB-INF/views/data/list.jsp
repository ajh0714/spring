<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <style>
 	.row{
 		border-bottom:1px solid #ddd;
 	}
 </style>
 <script>
 	function sendEditForm(no){
 		location.href="${pageContext.request.contextPath}/data/dataEdit?no="+no;
 	}
 	function sendDel(no){
 		if(confirm("자료실글을 삭제하시겠습니까?")){
 		location.href="${pageContext.request.contextPath}/data/dataDel?no="+no;
 		}
 	}
 </script>
<div class="container">
	<h1>자료실 목록</h1>
	<c:if test="${logStatus=='Y' }">
		<a href="${pageContext.request.contextPath }/data/dataWrite">자료올리기</a>
	</c:if>
	  <div class="row">
    <div class="col-sm-1 p-3">번호</div>
    <div class="col-sm-7 p-3">제목</div>
    <div class="col-sm-1 p-3">작성자</div>
    <div class="col-sm-3 p-3">첨부파일</div>
  </div>
  <c:forEach var="vo" items="${list}">
  	  <div class="row">
    <div class="col-sm-1 p-3">${vo.no }</div>
    <div class="col-sm-7 p-3"><a href="${pageContext.request.contextPath }/data/dataView?no=${vo.no}">${vo.subject }</a></div>
    <div class="col-sm-1 p-3">${vo.userid }</div>
    <div class="col-sm-3 p-3">
    	<a href="${pageContext.request.contextPath }/uploadfile/${vo.filename}" download>
    	<img src="${pageContext.request.contextPath }/uploadfile/${vo.filename }" width="120" height="100"/>
    	</a>
    	<button type="button" class="btn btn-info" onclick="sendEditForm(${vo.no })">수정</button>
		<button type="button" class="btn btn-warning" onclick="sendDel(${vo.no})">삭제</button>
    </div>
  </div>
  </c:forEach>
</div>