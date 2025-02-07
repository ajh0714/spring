<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.total{
		overflow:auto; padding:10px 0;
	}
	.total>div:first-child{
		float:left;
	}
	.total>div:last-child{
		float:right; 
	}
	.list{
		overflow:auto;
		padding: 30px 0;
	}
	.list>li{
		float:left; line-height:50px; border-bottom:1px solid #ddd; width:10%; text-align:center;
	}
	.list>li:nth-child(5n+2){
		width:60%; text-align:left;
		/*말줄임*/
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
	
	.search{
		text-align:center; padding: 30px;
	}
</style>
<script>
	function searchForm(){
		if(document.getElementById("searchWord").value==""){
			alert("검색어를 입력하세요..");
			return false;
		}
		return true;
	}
</script>
<div class="container">
	<h1>뉴스목록</h1>
	
	<c:if test="${logStatus=='Y'}">
	<div><a href="${pageContext.request.contextPath}/bbs/write">글쓰기</a></div>
	</c:if>
	<div class="total">
		<div>총레코드수 : ${pVO.totalRecord }개</div>
		<div>현재페이지 : ${pVO.nowPage}/${pVO.totalPage}</div>
	</div>
	<ul class="list">
		<li>번호</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>조회수</li>
		<li>등록일</li>
		
		<c:forEach var="vo" items="${list }">
		<li>${vo.news_no }</li>
		<li><a href="${pageContext.request.contextPath}/bbs/view?news_no=${vo.news_no}">${vo.subject}</a></li>
		<li>${vo.userid}</li>
		<li>${vo.hit}</li>
		<li>${vo.writedate}</li>
		</c:forEach>
	</ul>
	<!-- 페이지 -->
	 <ul class="pagination justify-content-center">
	 
	 	<c:if test="${pVO.nowPage==1}">
	 		<li class="page-item"><a class="page-link" href="#">Prev</a></li>
	 	</c:if>	
    	<c:if test="${pVO.nowPage>1}">
    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/bbs/list?nowPage=${pVO.nowPage-1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord}</c:if>">Prev</a></li>
    	</c:if>
    	<!--  페이지 번호 -->
    	<c:forEach var="p" begin="${pVO.startPageNum }" end="${pVO.startPageNum+pVO.onePageCount-1}">
    	<c:if test="${p==pVO.nowPage }">
    		<li class="page-item active">
    	</c:if>
    	<c:if test="${p!=pVO.nowPage }">
    		<li class="page-item">
    	</c:if>
    	<c:if test="${p<=pVO.totalPage }">
    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/bbs/list?nowPage=${p}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord}</c:if>">${p}</a></li>
    	</c:if>	
    	</c:forEach>
    	<!--  다음페이지 -->
    		<c:if test="${pVO.nowPage>=pVO.totalPage}">
	 		<li class="page-item"><a class="page-link" href="#">Next</a></li>
	 	</c:if>	
    	<c:if test="${pVO.nowPage<pVO.totalPage}">
    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/bbs/list?nowPage=${pVO.nowPage+1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey }&searchWord=${pVO.searchWord}</c:if>">Next</a></li>
    	</c:if>
  </ul>
	<!-- 검색어 -->
	<div class="search">
	<form action="${pageContext.request.contextPath }/bbs/list" onSubmit="return searchForm()">
		<select name="searchKey">
			<option value="subject">제목</option>
			<option value="userid">작성자</option>
			<option value="content">글내용</option>
		</select>
		<input type="text" name="searchWord" id="searchWord"/>
		<input type="submit" name="검색"/>
	</form>
	</div>
</div>