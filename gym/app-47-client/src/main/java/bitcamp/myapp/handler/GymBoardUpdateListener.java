package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class GymBoardUpdateListener implements ActionListener {

  BoardDao boardDao;

  public GymBoardUpdateListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");

    Board board = boardDao.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }
    board.setTitle(prompt.inputString("제목(%s)? ", board.getTitle()));
    board.setContent(prompt.inputString("내용(%s)? ", board.getContent()));
    board.setPassword(prompt.inputString("암호? "));;

    if (boardDao.update(board) == 0) {
      System.out.println("암호가 일치하지 않습니다!");
    } else {
      System.out.println("변경했습니다!");
    }

  }
}
