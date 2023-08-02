package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/member/detail")
public class GymMemberDetailListener implements ActionListener {

  MemberDao memberDao;

  public GymMemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");
    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 회원이 없습니다!");
      return;
    }
    prompt.printf("이름: %s\n", m.getName());
    prompt.printf("나이: %d\n", m.getAge());
    prompt.printf("핸드폰번호: %s\n", m.getPhoneNumber());
    prompt.printf("등록개월: %s\n", m.getPer() == 1 ? "1개월" : m.getPer() == 3 ? "3개월" : "6개월");
    prompt.printf("가입일: %s\n", m.getCreatedDate());
  }
}
