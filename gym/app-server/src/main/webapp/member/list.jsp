<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Member"%>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>

<%
    String searchKeyword = request.getParameter("search");
%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>회원</title>
    <link rel="stylesheet" href="/css/list.css">
    </head>
    <body>
    <jsp:include page="../header.jsp"/>
    <section class='notice'>
    <div class='page-title'>
    <div class='container'>
    <h1><a href='/member/list.jsp' style='text-decoration: none; color: inherit;'>회원 목록</a></h1>
    </div>
    </div>

    <form action='/member/list.jsp' method='get'>
    <div id='board-search'>
    <div class='container'>
    <div class='search-window'>
    <div class='search-wrap'>
    <label for='search' class='blind'>제목 검색</label>
    <input id='search' type='search' name='search' placeholder='회원명을 입력해주세요.' value=''>
    <button type='submit' class='btn btn-dark'>검색</button>
    </div>
    </div>
    </div>
    </div>
    </form>

    <div id='board-list'>
    <div class='container'>
    <table class='board-table'>
    <thead>
    <tr>
    <th scope='col' class='th-num'>번호</th>
    <th scope='col' class='th-name'>이름</th>
    <th scope='col' class='th-age'>나이</th>
    <th scope='col' class='th-per'>등록개월</th>
    </tr>
    </thead>
<%
    List<Member> list = memberDao.findAll();

    for (Member member : list) {
    pageContext.setAttribute("member", member);
%>

<%
      if (searchKeyword == null || member.getName().contains(searchKeyword)) { %>
        <tr>
        <td>${member.no}</td>
        <td>
        <img src='http://eptvhysxbghp19010745.cdn.ntruss.com/member/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
        <a href='/member/detail.jsp?no=${member.no}'>${member.name}</a></td>
        <td>${member.age}</td>
        <td>${member.per == 1 ? "1개월" : member.per == 3 ? "3개월" : "6개월"}</td></tr>
    <% } %>
<% } %>
    </tbody>
    </table>
    <div class='button-container'>
    <a href='/member/form.jsp'>새 회원</a>
    <a href='/'>메인</a>
    </div>

    </div>
    </div>
    <jsp:include page="../footer.jsp"/>
    </body>
    </html>

