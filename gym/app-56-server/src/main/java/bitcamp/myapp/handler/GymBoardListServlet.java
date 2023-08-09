package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Board;

@WebServlet("/board/list")
public class GymBoardListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int category = Integer.parseInt(request.getParameter("category"));
    String searchKeyword = request.getParameter("search"); // 검색어 가져오기



    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("<link rel=\"stylesheet\" href=\"/list.css\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<section class='notice'>");
    out.println("<div class='page-title'>");
    out.println("<div class='container'>");
    out.println("<h1><a href='/board/list?category=" + category
        + "' style='text-decoration: none; color: inherit;'>게시글 목록</a></h1>");
    out.println("</div>");
    out.println("</div>");

    // 검색
    out.println("<form action='/board/list' method='get'>");
    out.println("<div id='board-search'>");
    out.println("<div class='container'>");
    out.println("<div class='search-window'>");
    out.println("<div class='search-wrap'>");
    out.println("<label for='search' class='blind'>제목 검색</label>");
    out.println(
        "<input id='search' type='search' name='search' placeholder='검색어를 입력해주세요.' value=''>");
    out.println("<button type='submit' class='btn btn-dark'>검색</button>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("<input type='hidden' name='category' value='" + category + "'>");
    out.println("</form>");

    // 리스트
    out.println("<div id='board-list'>");
    out.println("<div class='container'>");
    out.println("<table class='board-table'>");
    out.println("<thead>");
    out.println("<tr>");
    out.println("<th scope='col' class='th-num'>번호</th>");
    out.println("<th scope='col' class='th-title'>제목</th>");
    out.println("<th scope='col' class='th-name'>작성자</th>");
    out.println("<th scope='col' class='th-view'>조회수</th>");
    out.println("<th scope='col' class='th-date'>등록일</th>");
    out.println("</tr>");
    out.println("</thead>");

    List<Board> list = InitServlet.boardDao.findAll(category);

    out.println("<tbody>");
    for (Board board : list) {
      // 검색어가 없거나 제목에 검색어가 포함되어 있다면 출력
      if (searchKeyword == null || board.getTitle().contains(searchKeyword)) {
        out.printf(
            "<tr>" + " <td>%d</td>" + " <td><a href='/board/detail?category=%d&no=%d'>%s</a></td>"
                + " <td>%s</td>" + " <td>%d</td>" + " <td>%s</td></tr>\n",
            board.getNo(), board.getCategory(), board.getNo(),
            (board.getTitle().length() > 0 ? board.getTitle() : "제목없음"),
            board.getWriter().getName(), board.getViewCount(),
            dateFormatter.format(board.getCreatedDate()));
      }
    }
    out.println("</tbody>");
    out.println("</table>");

    out.println("<div class='button-container'>");
    out.println("<a href='/'>메인</a>");
    out.printf("<a href='/board/form?category=%d'>새 글</a>\n", category);
    out.println("</div>");

    out.println("</div>");
    out.println("</div>");
    out.println("</body>");
    out.println("</section>");
    out.println("</html>");
  }

}


