package bitcamp.myapp.handler;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;

  public GymMemberUpdateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(prompt.inputString("이름(%s) ", m.getName()));
    m.setAge(prompt.inputInt("나이(%d) ", m.getAge()));
    m.setPhoneNumber(prompt.inputString("핸드폰번호(%s) ", m.getPhoneNumber()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setPer(MemberActionListener.inputPer(m.getPer(), prompt));
    memberDao.update(m);
  }
}
