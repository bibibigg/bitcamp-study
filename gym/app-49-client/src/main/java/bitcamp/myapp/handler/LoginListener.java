package bitcamp.myapp.handler;

import bitcamp.myapp.GymClientApp;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements MemberActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;

  }

  public void service(BreadcrumbPrompt prompt) {
    while (true) {
      Member m = new Member();
      m.setPhoneNumber(prompt.inputString("핸드폰번호? "));
      m.setPassword(prompt.inputString("암호? "));

      Member loginUser = memberDao.findByPhoneAndPassword(m);
      if (loginUser == null) {
        System.out.println("회원 정보가 일치하지 않습니다.");
      } else {
        GymClientApp.loginUser = loginUser;
        break;
      }
    }
  }
}
