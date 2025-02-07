<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <style>
        .container{
            width: 500px;
            margin: 100px auto;


        }
        #title{
            text-align: center;
        }
        .in-box, .log-btn{
            padding: 20px;
            margin: 10px 0px;
        }
        .in-box{
            width: 456px;

        }
        .log-btn{
            width: 100%;
            padding: 20px;
            background-color: blue;
            color: #fff;
            font-size: 1.5em;
        }
        .left, .right{
            float: left; width: 50%; margin: 30px 0;
        }
        .right{
            text-align: right;
        }
        a:link, a:visited, a:hover{
            color: black; text-decoration: none;
        }
    </style>
 <div class="container">
        <h1 id="title">로그인 폼</h1>
        <form method="post" action="<%=request.getContextPath()%>/member/loginOk">
            <input type="text" name="userid" class="in-box" value="goguma" placeholder="아이디(5~15의 영문,숫자만 가능)"minlength="5" maxlength="15"/>
            <input type="password" name="userpwd" class="in-box"value="12345678" placeholder="비밀번호(입력실패 5회시 잠금처리)" minlength="5" maxlength="15"/>
            <input type="submit" value="로그인" class="log-btn"/>
        </form>
        <div class="left">
            <input type="button" value="회원가입"/>
        </div>
        <div class="right">
            <a href="#">아이디찾기/비밀번호 찾기</a>
        </div>
