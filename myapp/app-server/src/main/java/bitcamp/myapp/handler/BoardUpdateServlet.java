package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;

import static java.lang.System.out;

@WebServlet("/board/update")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class BoardUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    try {
      Board board = new Board();
      board.setWriter(loginUser);
      board.setNo(Integer.parseInt(request.getParameter("no")));
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setCategory(Integer.parseInt(request.getParameter("category")));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = InitServlet.ncpObjectStorageService.uploadFile(
              "bitcamp-nc7-bucket-13", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      if (InitServlet.boardDao.update(board) == 0) {
        throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
      } else {
        if (attachedFiles.size() > 0) {
          InitServlet.boardDao.insertFiles(board);
        }
        InitServlet.sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list?category=" + board.getCategory());
      }

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("error",e);
      request.setAttribute("message", e.getMessage());
      request.setAttribute("refresh", "2;url=list?category=" + request.getParameter("category"));

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}











