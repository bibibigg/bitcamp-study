package bitcamp.myapp.handler;

import bitcamp.myapp.GymClientApp;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class GymBoardDeleteListener implements ActionListener {

  BoardDao boardDao;

  public GymBoardDeleteListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(BreadcrumbPrompt prompt) {
    Board b = new Board();
    b.setNo(prompt.inputInt("번호? "));
    b.setWriter(GymClientApp.loginUser);

    if (boardDao.delete(b) == 0) {
      System.out.println("해당 번호의 게시글이 없거나 암호가 맞지 않습니다!");
    } else {
      System.out.println("삭제했습니다.");
    }
  }
}
