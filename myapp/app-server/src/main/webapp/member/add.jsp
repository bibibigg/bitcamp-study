<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
    <%@ page import="java.io.IOException"%>
    <%@ page import="bitcamp.myapp.dao.MemberDao"%>
    <%@ page import="bitcamp.myapp.vo.Member"%>
    <%@ page import="bitcamp.util.NcpObjectStorageService"%>
    <%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>

<%
    request.setAttribute("refresh", "2;url=list.jsp");
    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setEmail(request.getParameter("email"));
    m.setPassword(request.getParameter("password"));
    m.setGender(request.getParameter("gender").charAt(0));

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-13",
              "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list.jsp");
%>





