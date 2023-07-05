package bitcamp.myapp.handler;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberAddListener implements MemberActionListener {

  MemberDao memberDao;

  public GymMemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;

  }

  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setAge(prompt.inputInt("나이? "));
    m.setPhoneNumber(prompt.inputString("핸드폰번호? "));
    m.setPer(MemberActionListener.inputPer(0, prompt));
    memberDao.insert((m));
  }
}
