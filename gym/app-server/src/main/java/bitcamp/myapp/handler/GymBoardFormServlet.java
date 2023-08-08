package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/form")
public class GymBoardFormServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int category = Integer.parseInt(request.getParameter("category"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프</title>");
    out.println("<link rel=\"stylesheet\" href=\"../auth/stylesform.css\">");
    out.println("</head>");
    out.println("<body>");
    out.println("<div class='main'>");
    out.println("<h1 class='logo'>게시글</h1>");
    out.println("<div class='container'>");
    out.println("<form action='/board/add' method='post'>");
    out.println("<input type='text' name='title' placeholder='제목' class='add'><br>");
    out.println("<textarea name='content' placeholder='내용' class='contentadd'></textarea><br>");
    out.printf("<input type='hidden' name='category' value='%d'>\n", category);
    out.println("<button id='buttonform' class='add'>등록</button>");
    out.println("<a href='/board/list?category=" + category + "'>취소</a>"); // 취소 버튼 추가
    out.println("</form>");
    out.println("</div>");
    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
  }
}


