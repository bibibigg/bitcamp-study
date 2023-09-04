<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="bitcamp.myapp.dao.MemberDao"%>
<%@ page import="bitcamp.myapp.vo.Member"%>

<%
request.setAttribute("refresh", "2;url=/auth/form.jsp");
    Member m = new Member();
    m.setPhoneNumber(request.getParameter("phone_number"));
    m.setPassword(request.getParameter("password"));

 if (request.getParameter("saveNumber") != null) {
      Cookie cookie = new Cookie("phone_number", m.getPhoneNumber());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone_number", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
%>
<jsp:useBean id ="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<%
    Member loginUser = memberDao.findByPhoneAndPassword(m);
    if (loginUser == null) {
     throw new Exception("회원 정보가 일치하지 않습니다.");
     }
    session.setAttribute("loginUser", loginUser);
        response.sendRedirect("/");
%>