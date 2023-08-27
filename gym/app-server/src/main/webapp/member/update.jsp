<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="bitcamp.myapp.vo.Member"%>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>

<jsp:useBean id="memberDao" type="bitcamp.myapp.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>

<%
    Member m = new Member();
    m.setNo(Integer.parseInt(request.getParameter("no")));
    m.setName(request.getParameter("name"));
    m.setAge(Integer.parseInt(request.getParameter("age")));
    m.setPhoneNumber(request.getParameter("phone_number"));
    m.setPassword(request.getParameter("password"));
    m.setPer(Integer.parseInt(request.getParameter("per")));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = InitServlet.ncpObjectStorageService.uploadFile(
          "bitcamp-nc7-bucket-13", "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }
      if (memberDao.update(m) == 0) {
        out.println("<p>회원이 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp");
      }
%>