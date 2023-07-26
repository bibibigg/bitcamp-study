package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class GymBoardDeleteListener implements ActionListener {

  DataSource ds;
  BoardDao boardDao;

  public GymBoardDeleteListener(BoardDao boardDao, DataSource ds) {
    this.boardDao = boardDao;
    this.ds = ds;
  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    Board b = new Board();
    b.setNo(prompt.inputInt("번호? "));
    b.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      if (boardDao.delete(b) == 0) {
        prompt.println("해당 번호의 게시글이 없거나 암호가 맞지 않습니다!");
      } else {
        prompt.println("삭제했습니다.");
        ds.getConnection().commit();
      }
    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}
