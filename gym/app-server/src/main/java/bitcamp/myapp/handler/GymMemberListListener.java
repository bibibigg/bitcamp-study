package bitcamp.myapp.handler;

import java.io.IOException;
import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/member/list")
public class GymMemberListListener implements ActionListener {

  MemberDao memberDao;

  public GymMemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("-----------------------------");
    prompt.println("번호, 이름, 나이, 등록개월");
    prompt.println("-----------------------------");

    List<Member> list = memberDao.findAll();

    for (Member m : list) {
      prompt.printf("%d, %s, %d, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getPer() == 1 ? "1개월" : m.getPer() == 3 ? "3개월" : "6개월");
    }
  }
}
