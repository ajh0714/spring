<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<style>
	.btn{
		padding:30px;
		text-align:center;
		width:100%;
	}
	#fileSelect{
		display:none;
	}
</style>
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
        if(document.getElementById("filename").name!='' &&
        	document.getElementById("filename").value==""){
            alert("첨부파일이 반드시 있어야 합니다.");
            return false;
        }
    return true;
    }
    function newFileSelect(){
    	document.getElementById("fileView").style.display="none";
    	document.getElementById("fileSelect").style.display = "block";
    	document.getElementById("filename").name="mf"
    }
    
</script>
<div class="container">
	 <h1>자료 올리기 수정폼</h1>
	 <!-- 
	 	첨부파일이 있는 경우 form태그에 enctype속성이 반드시 기술되어야 한다.
	  -->	
    <form method="post" action="<%=request.getContextPath()%>/data/dataEditOk" 
   		 onsubmit="return bbsFormCheck()" enctype="multipart/form-data">
    	<input type="hidden" name="no" value="${vo.no }"/>
        <div>제목</div>
        <div><input type="text" name="subject" id="subject" value="${vo.subject }" style= width:100%;/></div>
        <div>글내용</div>
        <div><textarea name="content" id="content">${vo.content }</textarea></div>
        <div>첨부파일</div>
        <!-- 첨부파일이 있음 -->
        <div id="fileView">
        	${vo.filename }<b onclick="newFileSelect()">X</b>
        </div>
        <!-- 기존에 첨부된 파일을 삭제하면 보이도록 한다  -->
        <div id="fileSelect">
        	<input type="file" name="" id="filename"/>
        </div>
        <div class="btn">
            <input type="submit" value="자료실 수정"/>
        </div>
    </form>
</div>