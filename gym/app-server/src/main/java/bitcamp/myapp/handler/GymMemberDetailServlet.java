package bitcamp.myapp.handler;

import java.io.PrintWriter;
import java.time.LocalDate;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.DateCalc;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/detail")
public class GymMemberDetailServlet implements Servlet {

  DateCalc dateCalc;
  MemberDao memberDao;

  public GymMemberDetailServlet(MemberDao memberDao, DateCalc dateCalc) {
    this.memberDao = memberDao;
    this.dateCalc = dateCalc;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member m = memberDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원</h1>");

    if (m == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");
    } else {
      out.println("<form action='/member/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          m.getNo());
      out.printf("<tr><th>이름</th>" + " <td><input type='text' name='name' value='%s'></td></tr>\n",
          m.getName());
      out.printf(
          "<tr><th>핸드폰번호</th>"
              + " <td><input type='tel' name='phone_number' value='%s'></td></tr>\n",
          m.getPhoneNumber());
      out.println("<tr><th>암호</th>" + " <td><input type='password' name='password'></td></tr>");
      out.printf(
          "<tr><th>등록개월</th>\n" + " <td><select name='per'>\n"
              + " <option value='1' %s>1개월</option>\n" + " <option value='3' %s>3개월</option>\n"
              + " <option value='6' %s>6개월</option></select></td></tr>\n",
          (m.getPer() == '1' ? "selected" : ""), (m.getPer() == '3' ? "selected" : ""),
          (m.getPer() == '6' ? "selected" : ""));
      out.println("<tr><th>종료일</th><td>" + dateCalc.calculateEndDate(m) + "</td></tr>");
      out.println("<tr><th>남은 기간</th><td>" + dateCalc.calculateRemainingDays(m) + "일</td></tr>");
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/member/delete?no=%d'>삭제</a>\n", m.getNo());
      out.println("<a href='/member/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }

  private void printEndDateAndRemainingDays(Member m, PrintWriter out) {
    LocalDate endDate = dateCalc.calculateEndDate(m);
    long remainingDays = dateCalc.calculateRemainingDays(m);

    out.println("<p>종료일: " + endDate + "</p>");
    out.println("<p>남은 기간: " + remainingDays + "일</p>");
  }
}
