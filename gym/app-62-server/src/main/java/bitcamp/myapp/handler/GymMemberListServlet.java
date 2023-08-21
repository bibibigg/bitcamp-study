package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Member;

@WebServlet("/member/list")
public class GymMemberListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String searchKeyword = request.getParameter("search"); // 검색어 가져오기

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("<link rel=\"stylesheet\" href=\"/list.css\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<section class='notice'>");
    out.println("<div class='page-title'>");
    out.println("<div class='container'>");
    out.println(
        "<h1><a href='/member/list' style='text-decoration: none; color: inherit;'>회원 목록</a></h1>");
    out.println("</div>");
    out.println("</div>");

    // 검색
    out.println("<form action='/member/list' method='get'>");
    out.println("<div id='board-search'>");
    out.println("<div class='container'>");
    out.println("<div class='search-window'>");
    out.println("<div class='search-wrap'>");
    out.println("<label for='search' class='blind'>제목 검색</label>");
    out.println(
        "<input id='search' type='search' name='search' placeholder='회원명을 입력해주세요.' value=''>");
    out.println("<button type='submit' class='btn btn-dark'>검색</button>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</form>");

    // 리스트
    out.println("<div id='board-list'>");
    out.println("<div class='container'>");
    out.println("<table class='board-table'>");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th scope='col' class='th-num'>번호</th>");
    out.println("<th scope='col' class='th-name'>이름</th>");
    out.println("<th scope='col' class='th-age'>나이</th>");
    out.println("<th scope='col' class='th-per'>등록개월</th>");
    out.println("</tr>");
    out.println("</thead>");

    List<Member> list = InitServlet.memberDao.findAll();

    for (Member m : list) {
      // 검색어가 없거나 이름에 검색어가 포함되어 있다면 출력
      if (searchKeyword == null || m.getName().contains(searchKeyword)) {
        out.printf(
            "<tr>"
        + " <td>%d</td>"
            		+ "<td>"
            		 + "<img src='http://eptvhysxbghp19010745.cdn.ntruss.com/member/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>"
            		+ "<a href='/member/detail?no=%d'>%s</a></td>"
                + "<td>%d</td>" + "<td>%s</td></tr>\n",
            m.getNo(), m.getPhoto(), m.getNo(), m.getName(), m.getAge(),
            m.getPer() == 1 ? "1개월" : m.getPer() == 3 ? "3개월" : "6개월");
      }
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<div class='button-container'>");
    out.println("<a href='/member/form.html'>새 회원</a>");
    out.println("<a href='/'>메인</a>");
    out.println("</div>");

    out.println("</div>");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }
}
