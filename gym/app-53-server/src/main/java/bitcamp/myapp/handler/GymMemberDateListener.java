package bitcamp.myapp.handler;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberDateListener implements ActionListener {

  MemberDao memberDao;

  public GymMemberDateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 회원이 없습니다!");
      return;
    }

    int inputDate = m.getPer(); // 입력한 개월 수,

    // java.sql.Date를 java.time.LocalDate로 변환
    LocalDate localCreatedDate = m.getCreatedDate().toLocalDate();


    // 등록일에서 입력한 개월 수를 더하여 종료일 계산
    LocalDate endDate = localCreatedDate.plusMonths(inputDate);
    Date calculatedEndDate = Date.valueOf(endDate);
    // java.time.LocalDate를 다시 java.sql.Date로 변환
    LocalDate currentDate = LocalDate.now();

    // 남은 기간 계산
    long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);

    prompt.printf("가입일: %s\n", m.getCreatedDate());
    prompt.printf("종료일: %s\n", calculatedEndDate);
    prompt.printf("남은기간: %d일\n", remainingDays);

  }
}
