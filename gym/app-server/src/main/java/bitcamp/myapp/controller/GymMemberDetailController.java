package bitcamp.myapp.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

@WebServlet("/member/detail")
public class GymMemberDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
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

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/member/detail.jsp").include(request, response);
  }
}
