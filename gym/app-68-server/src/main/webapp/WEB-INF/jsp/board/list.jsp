<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>게시글</title>
    <link rel="stylesheet" href="/css/list.css">
    </head>
    <body>
    <section class='notice'>
    <div class='page-title'>
    <div class='container'>
    <h1><a href='list?category=${param.category}' style='text-decoration: none; color: inherit;'>게시글 목록</a></h1>
    </div>
    </div>

    <form action='list' method='get'>
    <div id='board-search'>
    <div class='container'>
    <div class='search-window'>
    <div class='search-wrap'>
    <label for='search' class='blind'>제목 검색</label>
    <input id='search' type='search' name='search' placeholder='검색어를 입력해주세요.' value=''>
    <button type='submit' class='btn btn-dark'>검색</button>
    </div>
    </div>
    </div>
    </div>
    <input type='hidden' name='category' value='${param.category}'>
    </form>

    <div id='board-list'>
    <div class='container'>
    <table class='board-table'>
    <thead>
    <tr>
    <th scope='col' class='th-num'>번호</th>
    <th scope='col' class='th-title'>제목</th>
    <th scope='col' class='th-name'>작성자</th>
    <th scope='col' class='th-view'>조회수</th>
    <th scope='col' class='th-date'>등록일</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${list}" var="board">
    <c:if test="${searchKeyword == null || board.title.contains(searchKeyword)}">
          <tr><td>${board.no}</td>
          <td><a href='detail?category=${board.category}&no=${board.no}'>
          ${board.title.length() > 0 ? board.title : "제목없음"}</a></td>
          <td>${board.writer.name}</td>
          <td>${board.viewCount}</td>
           <td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/></td></tr>
           </c:if>
</c:forEach>
    </tbody>
    </table>

    <div class='button-container'>
    <a href='/'>메인</a>
    <a href='add?category=${param.category}'>새 글</a>
    </div>
    </div>
    </div>
    </body>
    </section>
    </html>
