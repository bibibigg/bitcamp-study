package bitcamp.myapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("viewUrl", "/WEB-INF/jsp/auth/form.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
    Member loginUser = memberDao.findByPhoneAndPassword(m);
    if (loginUser != null) {
      request.getSession().setAttribute("loginUser", loginUser);
      request.setAttribute("viewUrl", "redirect:/");
      return;
    }
    request.setAttribute("refresh", "2;url=/auth/login");
    request.setAttribute("exception", "회원 정보가 일치하지 않습니다.");
  }
}
