<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/headfoot.css">

<div class="header-container">
    <img src='https://www.ncloud.com/public/img/logo-m.png' class="header-img">
    <a href='/member/list' class="header-link">회원</a>
    <a href='/board/list?category=1' class="header-link">게시글</a>
    <a href='/board/list?category=2' class="header-link">운동일지</a>

    <c:choose>
        <c:when test="${empty sessionScope.loginUser}">
        <a href='/auth/form' class="header-link">로그인</a>
        </c:when>
        <c:otherwise>
        <c:if test="${empty sessionScope.loginUser.photo}">
            <img src='/images/avatar.png' class="header-img">
        </c:if>
        <c:if test="${not empty sessionScope.loginUser.photo}">
            <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg' class="header-img">
        </c:if>
        ${loginUser.name} <a href='/auth/logout' class="header-link">로그아웃</a>
        </c:otherwise>
    </c:choose>
</div>