package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberAddListener extends AbstractMemberListener {

  public GymMemberAddListener(List<Member> list) {
    super(list);

  }

  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setNo(Member.userId++);
    m.setName(prompt.inputString("이름? "));
    m.setAge(prompt.inputInt("나이? "));
    m.setPhoneNumber(prompt.inputString("핸드폰번호? "));
    m.setPer(inputPer(0, prompt));
    m.setCreatedDate(System.currentTimeMillis());
    this.list.add(m);
  }
}
