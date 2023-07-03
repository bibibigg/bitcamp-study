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

    long createdDate = m.getCreatedDate(); // 등록일
    int inputDate = m.getPer(); // 입력한 개월 수,

    System.out.println(inputDate);

    int year = LocalDate.ofEpochDay(createdDate / (24 * 60 * 60 * 1000)).getYear();
    int month = LocalDate.ofEpochDay(createdDate / (24 * 60 * 60 * 1000)).getMonthValue();
    int day = LocalDate.ofEpochDay(createdDate / (24 * 60 * 60 * 1000)).getDayOfMonth();
    LocalDate createDate = LocalDate.of(year, month, day);

    // 등록일에서 입력한 개월 수를 더하여 종료일 계산
    LocalDate endDate = createDate.plusMonths(inputDate);

    System.out.printf("등록일: %tY-%<tm-%<td\n", createDate);
    System.out.printf("종료일: %tY-%<tm-%<td\n", endDate);


  }
}
