<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

<link rel="stylesheet" href="/css/headfoot.css">

<div class="header-container">
    <img src='https://www.ncloud.com/public/img/logo-m.png' class="header-img">
    <a href='/member/list.jsp' class="header-link">회원</a>
    <a href='/board/list.jsp?category=1' class="header-link">게시글</a>
    <a href='/board/list.jsp?category=2' class="header-link">운동일지</a>

    <jsp:useBean id="loginUser" class="bitcamp.myapp.vo.Member" scope="session"/>
    <% if (loginUser.getNo() == 0) { %>
        <a href='/auth/form.jsp' class="header-link">로그인</a>
    <% } else {
        if (loginUser.getPhoto() == null) { %>
            <img src='/images/avatar.png' class="header-img">
        <% } else { %>
            <img src='http://mvsenqskbqzl19010704.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg' class="header-img">
        <% } %>
        ${loginUser.name} <a href='/auth/logout.jsp' class="header-link">로그아웃</a>
    <% } %>
</div>