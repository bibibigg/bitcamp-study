package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Member;

@WebServlet("/member/detail")
public class GymMemberDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member m = InitServlet.memberDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("<link rel=\"stylesheet\" href=\"/detail.css\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원</h1>");

    if (m == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");
    } else {

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

      out.println("<form action='/member/update' method='post' enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>사진</th>"
              + " <td style='width:300px;'>"
              + (m.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>" :
                "<a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-13/member/%s'>"
                + "<img src='http://eptvhysxbghp19010745.cdn.ntruss.com/member/%1$s?type=f&w=60&h=80&faceopt=true&ttype=jpg'>"
                + "</a>")
              + " <input type='file' name='photo'>"
              + "</td></tr>\n", m.getPhoto());
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          m.getNo());
      out.printf("<tr><th>이름</th>" + " <td><input type='text' name='name' value='%s'></td></tr>\n",
          m.getName());
      out.printf("<tr><th>나이</th>" + " <td><input type='number' name='age' value='%s'></td></tr>\n",
          m.getAge());
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
      out.println("<tr><th>종료일</th><td>" + calculatedEndDate + "</td></tr>");
      out.println("<tr><th>남은 기간</th><td>" + remainingDays + "일</td></tr>");
      out.println("</table>");

      out.println("<div class='button-container'>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf(
          "<a href='/member/delete?no=%d' onclick='return confirm(\"정말로 삭제하시겠습니까?\")'>삭제</a>\n",
          m.getNo());
      out.println("<a href='/member/list'>목록</a>\n");
      out.println("</div>");

      out.println("</form>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
