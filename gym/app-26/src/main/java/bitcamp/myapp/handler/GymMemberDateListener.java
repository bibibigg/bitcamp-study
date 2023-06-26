package bitcamp.myapp.handler;

import java.time.LocalDate;
import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberDateListener extends AbstractMemberListener {


  public GymMemberDateListener(List<Member> list) {
    super(list);
  }

  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    LocalDate currentDate = LocalDate.now(); // 현재 날짜 입력
    int inputDate = m.getPer(); // 입력한 개월 수,

    // 현재 날짜에서 입력한 개월 수를 더하여 종료일 계산
    // 이렇게 하면 매일마다 종료일이 늘어남
    // 수정해야함!!!!!
    LocalDate resultDate = currentDate.plusMonths(inputDate);

    System.out.println("현재 날짜: " + currentDate);
    System.out.println("종료일: " + resultDate);


  }
}
