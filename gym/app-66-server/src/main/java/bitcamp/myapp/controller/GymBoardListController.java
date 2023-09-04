package bitcamp.myapp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.BoardDao;

@WebServlet("/board/list")
public class GymBoardListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    request.setAttribute("list",boardDao.findAll(Integer.parseInt(request.getParameter("category"))));
      request.setAttribute("searchKeyword",request.getParameter("search"));
      //String searchKeyword = request.getParameter("search");

    request.setAttribute("viewUrl", "/WEB-INF/jsp/board/list.jsp");
    } catch (Exception e) {
      request.setAttribute("refresh", "1;url=/");
      request.setAttribute("exception", e);
    }
  }
}