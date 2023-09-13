package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  MemberService memberService;

  {
    System.out.println("AuthController 생성됨!");
  }

  @GetMapping("form")
  public void form() {

  }

  @PostMapping("login")
  public String login(
          String phoneNumber,
          String password,
          String saveNumber,
          HttpSession session,
          Model model,
          HttpServletResponse response) throws Exception {

    if (saveNumber != null) {
      Cookie cookie = new Cookie("phone_number", phoneNumber);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("phone_number", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberService.get(phoneNumber, password);
    if (loginUser == null) {

      model.addAttribute("refresh", "2;url=form");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }
    session.setAttribute("loginUser", loginUser);
    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:/";
  }

}

