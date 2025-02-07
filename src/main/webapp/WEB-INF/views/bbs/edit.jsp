<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<div class="container">
<script>
//CKEDITOR를 textarea에 적용하기
    window.onload = function(){
      CKEDITOR.ClassicEditor.create(document.getElementById("content"),option);
    }
    function bbsFormCheck(){
        if(document.getElementById("subject").value==""){
        alert("뉴스제목을 입력하세요.");
        return false;
    }
    return true;
    }
</script>
<style>
	.btn{
		padding:30px;
		text-align:center;
		width:100%;
	}
</style>
    <h1>뉴스 글수정폼</h1>
	<div class="container"/>
    <form method="post" action="<%=request.getContextPath()%>/bbs/editOk" onsubmit="return bbsFormCheck">
    	<input type="hidden" name="news_no" value="${vo.news_no}"/>
        <div>제목</div>
        <div><input type="text" name="subject" id="subject" value="${vo.subject }" style= width:100%;/></div>
        <div>글내용</div>
        <div><textarea name="content" id="content">${vo.content }</textarea></div>
        <div class="btn">
            <input type="submit" value="뉴스수정하기"/>
            <input type="reset" value="다시쓰기"/>
        </div>
    </form>
</div>