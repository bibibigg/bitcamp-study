package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberDetailListener extends AbstractMemberListener {

  public GymMemberDetailListener(List<Member> list) {
    super(list);
  }


  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("나이: %d\n", m.getAge());
    System.out.printf("핸드폰번호: %s\n", m.getPhoneNumber());
    System.out.printf("등록개월: %s\n", toperString(m.getPer()));
  }
}
