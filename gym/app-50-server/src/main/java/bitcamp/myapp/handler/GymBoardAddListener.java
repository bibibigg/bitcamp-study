package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class GymBoardAddListener implements ActionListener {

  BoardDao boardDao;

  public GymBoardAddListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  public void service(BreadcrumbPrompt prompt) throws IOException {

    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter((Member) prompt.getAttribute("loginUser"));

    boardDao.insert(board);
  }
}