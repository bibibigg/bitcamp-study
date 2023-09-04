package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/auth/login")
public class LoginController implements PageController {

  MemberDao memberDao;

  public LoginController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if(request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

    Member m = new Member();
    String phoneNumber = request.getParameter("phone_number");
    String password = request.getParameter("password");

    if (request.getParameter("saveNumber") != null) {
      Cookie cookie = new Cookie("phone_number",phoneNumber);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone_number", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberDao.findByPhoneAndPassword(phoneNumber, password);
    if (loginUser == null) {
    request.setAttribute("refresh", "2;url=/auth/login");
    request.setAttribute("exception", "회원 정보가 일치하지 않습니다.");
    }
      request.getSession().setAttribute("loginUser", loginUser);
      return "redirect:/";
  }
}
