package bitcamp.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;


public class DateCalc {
  MemberDao memberDao;

  public DateCalc(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public LocalDate calculateEndDate(Member m) {
    int inputDate = m.getPer();
    LocalDate localCreatedDate = m.getCreatedDate().toLocalDate();
    return localCreatedDate.plusMonths(inputDate);
  }

  public long calculateRemainingDays(Member m) {
    LocalDate endDate = calculateEndDate(m);
    LocalDate currentDate = LocalDate.now();
    return ChronoUnit.DAYS.between(currentDate, endDate);
  }

}
