package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberListListener implements ActionListener {

  MemberDao memberDao;

  public GymMemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public void service(BreadcrumbPrompt prompt) {
    System.out.println("-----------------------------");
    System.out.println("번호, 이름, 나이, 등록개월");
    System.out.println("-----------------------------");

    List<Member> list = memberDao.list();

    for (Member m : list) {
      System.out.printf("%d, %s, %d, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getPer() == 1 ? "1개월" : m.getPer() == 3 ? "3개월" : "6개월");
    }
  }
}
