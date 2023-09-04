package bitcamp.myapp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.dao.BoardDao;

public class GymBoardListController implements PageController {

  BoardDao boardDao;

  public GymBoardListController(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      request.setAttribute("list", boardDao.findAll(Integer.parseInt(request.getParameter("category"))));
      request.setAttribute("searchKeyword", request.getParameter("search"));
      return "/WEB-INF/jsp/board/list.jsp";

    } catch (Exception e) {
      request.setAttribute("refresh", "1;url=/");
      request.setAttribute("exception", e);
      throw e;
    }
  }
}