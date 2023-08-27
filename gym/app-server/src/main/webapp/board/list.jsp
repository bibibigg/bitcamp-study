<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Board"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));
    int category = Integer.parseInt(request.getParameter("category"));
    String searchKeyword = request.getParameter("search"); // 검색어 가져오기
%>


    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>게시글</title>
    <link rel="stylesheet" href="/list.css">
    </head>
    <body>
    <section class='notice'>
    <div class='page-title'>
    <div class='container'>
    <h1><a href='/board/list?category=${param.category}>' style='text-decoration: none; color: inherit;'>게시글 목록</a></h1>
    </div>
    </div>

    <form action='/board/list?category=${param.category}>' method='get'>
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

<jsp:useBean id="boardDao" type="bitcamp.myapp.dao.BoardDao" scope="application"/>
<%
    List<Board> list = boardDao.findAll(category);
%>
    <tbody>
    <%
    for (Board board : list) {
    pageContext.setAttribute("board", board);
    %>
    <% if (searchKeyword == null || board.getTitle().contains(searchKeyword)) { %>
       <tr><td>${board.no}</td><td><a href='/board/detail.jsp?category=${board.category}&no=${board.no}'>
       ${board.title.length() > 0 ? board.title : "제목없음"}</a></td>
       <td>${board.writer.name}</td>
       <td>${board.viewCount}</td>
       <td>${simpleDateFormatter.format(board.createdDate)}</td></tr>
    <% } %>
    <% } %>
    </tbody>
    </table>

    <div class='button-container'>
    <a href='/'>메인</a>
    <a href='/board/form.jsp?category=${param.category}'>새 글</a>
    </div>

    </div>
    </div>
    </body>
    </section>
    </html>
