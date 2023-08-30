package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component("/member/detail")
public class GymMemberDetailController implements PageController {

  MemberDao memberDao;

  public GymMemberDetailController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Member m = memberDao.findBy(Integer.parseInt(request.getParameter("no")));

    int inputMonths = m.getPer();
    LocalDate localCreatedDate = m.getCreatedDate().toLocalDate();
    LocalDate endDate = localCreatedDate.plusMonths(inputMonths);
    Date calculatedEndDate = Date.valueOf(endDate);
    LocalDate currentDate = LocalDate.now();
    long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);

    request.setAttribute("member", memberDao.findBy(Integer.parseInt(request.getParameter("no"))));
    request.setAttribute("calculatedEndDate", calculatedEndDate);
    request.setAttribute("remainingDays", remainingDays);

    return "/WEB-INF/jsp/member/detail.jsp";
  }
}
