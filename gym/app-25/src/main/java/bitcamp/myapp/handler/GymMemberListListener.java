package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Iterator;
import bitcamp.util.List;

public class GymMemberListListener extends AbstractMemberListener {

  public GymMemberListListener(List<Member> list) {
    super(list);
  }

  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");

    Iterator<Member> iterator = list.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%d, %s, %d, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getPhoneNumber(), toperString(m.getPer()));
    }
  }
}
