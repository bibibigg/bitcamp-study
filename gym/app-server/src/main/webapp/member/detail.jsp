<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.myapp.vo.Member"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>

<%
    Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));
    pageContext.setAttribute("member", member);
%>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>회원</title>
    <link rel="stylesheet" href="/detail.css">
    </head>
    <body>
    <h1>회원</h1>


<%    if (member == null) { %>
      <p>해당 번호의 회원이 없습니다!</p>
<%    } else {

      int inputDate = member.getPer();


      LocalDate localCreatedDate = member.getCreatedDate().toLocalDate();

      LocalDate endDate = localCreatedDate.plusMonths(inputDate);
      Date calculatedEndDate = Date.valueOf(endDate);

      LocalDate currentDate = LocalDate.now();


      long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);
%>
      <form action='/member/update.jsp' method='post' enctype='multipart/form-data'>
      <table border='1'>
      <tr><th style='width:120px;'>사진</th>
      <td style='width:300px;'>
      <% if (member.getPhoto() == null) { %>
      <img style='height:80px' src='/images/avatar.png'>
      <%  } else { %>
      <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-13/member/${member.photo}'>
      <img src='http://eptvhysxbghp19010745.cdn.ntruss.com/member/${member.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
      </a>
    <% } %>
       <input type='file' name='photo'></td></tr>

      <tr><th style='width:120px;'>번호</th>
       <td style='width:300px;'><input type='text' name='no' value='${member.no}' readonly></td></tr>
     <tr><th>이름</th><td><input type='text' name='name' value='${member.name}'></td></tr>
      <tr><th>나이</th><td><input type='number' name='age' value='${member.age}'></td></tr>
      <tr><th>핸드폰번호</th>
      <td><input type='tel' name='phone_number' value='${member.phoneNumber}'></td></tr>
      <tr><th>암호</th><td><input type='password' name='password'></td></tr>
      <tr><th>등록개월</th>
      <td><select name='per'>
      <option value='1' ${member.getPer() == '1' ? "selected" : ""}>1개월</option>
      <option value='3' ${member.getPer() == '3' ? "selected" : ""}>3개월</option>
      <option value='6' ${member.getPer() == '6' ? "selected" : ""}>6개월</option></select></td></tr>
      <tr><th>종료일</th><td><%= calculatedEndDate %></td></tr>
      <tr><th>남은 기간</th><td><%= remainingDays %>일</td></tr>
      </table>

      <div class='button-container'>
      <button>변경</button>
      <button type='reset'>초기화</button>
      <a href='/member/delete.jsp?no=${member.no}' onclick='return confirm("정말로 삭제하시겠습니까?")'>삭제</a>
      <a href='/member/list.jsp'>목록</a>
      </div>
      </form>
    <% } %>
    </body>
    </html>

