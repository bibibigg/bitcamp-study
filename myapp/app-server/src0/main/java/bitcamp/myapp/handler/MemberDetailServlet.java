package bitcamp.myapp.handler;

import java.io.PrintWriter;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {


  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
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
          "<tr><th>이메일</th>" + " <td><input type='email' name='email' value='%s'></td></tr>\n",
          m.getEmail());
      out.println("<tr><th>암호</th>" + " <td><input type='password' name='password'></td></tr>");
      out.println("<tr><th>성별</th><td>");
      out.printf("<input type='radio' name='gender' value='M' %s>남성",
          (m.getGender() == 'M' ? "checked" : ""));
      out.printf("<input type='radio' name='gender' value='F' %s>여성",
          (m.getGender() == 'F' ? "checked" : ""));
      out.println("</td></tr>");
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
}
