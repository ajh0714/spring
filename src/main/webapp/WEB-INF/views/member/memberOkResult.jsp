<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${result==1}">
	<script>
		alert("회원의 정상ㄷ등록되었습니다.\n로그인페이지로 이동합니다.")
		location.href="${pageContext.request.contextPath}/member/login"
	</script>
</c:if>
<c:if test="${result==0}">
	<script>
		alert("회원등록 실패하였습니다.");
		history.back();
	</script>
</c:if>