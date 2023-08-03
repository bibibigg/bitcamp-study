package bitcamp.myapp.handler;

import java.io.PrintWriter;
import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/list")
public class GymMemberListServlet implements Servlet {

  MemberDao memberDao;

  public GymMemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/member/form.html'>새 회원</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>나이</th> <th>등록개월</th></tr>");
    out.println("</thead>");

    List<Member> list = memberDao.findAll();

    for (Member m : list) {
      out.printf(
          "<tr>" + " <td>%d</td>" + "<td><a href='/member/detail?no=%d'>%s</a></td>" + "<td>%d</td>"
              + "<td>%s</td></tr>\n",
          m.getNo(), m.getNo(), m.getName(), m.getAge(),
          m.getPer() == 1 ? "1개월" : m.getPer() == 3 ? "3개월" : "6개월");
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}
