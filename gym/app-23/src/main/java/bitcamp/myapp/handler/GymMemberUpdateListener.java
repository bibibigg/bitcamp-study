package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class GymMemberUpdateListener extends AbstractMemberListener {


  public GymMemberUpdateListener(List list) {
    super(list);
  }

  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(prompt.inputString("이름(%s) ", m.getName()));
    m.setAge(prompt.inputInt("나이(%d) ", m.getAge()));
    m.setPhoneNumber(prompt.inputString("핸드폰번호 ", m.getPhoneNumber()));
    m.setPer(inputPer(m.getPer(), prompt));
  }
}
