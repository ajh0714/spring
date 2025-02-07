<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>VO객체 사용하기</h1>
	<ul>
		<li> 번호 : ${vo.num}</li>
		<li> 이름 : ${vo.name }</li>
		<li> 연락처 :${vo.tel } </li>
	</ul>
	<hr/>
	<div>
	<form method="post" action="${pageContext.request.contextPath}/order">
		상풍명 : <input type="text" name="productName"/><br/>
		가격 : <input type="text" name="price"/><br/>
		옵션 : <input type="text" name="opt"/><br/>
		<input type="submit" value="주문하기"/>
	</form>
	</div>
	<h2>${pName },${sales}</h2>
</body>
</html>