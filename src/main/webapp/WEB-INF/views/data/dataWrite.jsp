<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script>
//CKEDITOR를 textarea에 적용하기
    window.onload = function(){
      CKEDITOR.ClassicEditor.create(document.getElementById("content"),option);
    }
    function bbsFormCheck(){
        if(document.getElementById("subject").value==""){
        alert("글제목을 입력하세요.");
        return false;
    }
        if(document.getElementById("filename").value==""){
            alert("첨부파일이 반드시 있어야 합니다.");
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
	<div class="container">
	 <h1>자료 올리기폼</h1>
	 <!-- 
	 	첨부파일이 있는 경우 form태그에 enctype속성이 반드시 기술되어야 한다.
	  -->	
    <form method="post" action="<%=request.getContextPath()%>/data/dataWriteOk" 
    onsubmit="return bbsFormCheck()" enctype="multipart/form-data">
        <div>제목</div>
        <div><input type="text" name="subject" id="subject" style= width:100%;/></div>
        <div>글내용</div>
        <div><textarea name="content" id="content"></textarea></div>
        <div>첨부파일</div>
        <div>
        	<input type="file" name="mf" id="filename"/>
        </div>
        <div class="btn">
            <input type="submit" value="자료올리기"/>
        </div>
    </form>
</div>