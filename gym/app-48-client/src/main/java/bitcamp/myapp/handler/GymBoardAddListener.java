package bitcamp.myapp.handler;

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


  public void service(BreadcrumbPrompt prompt) {

    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));

    Member writer = new Member();
    writer.setNo(prompt.inputInt("작성자? "));
    board.setWriter(writer);

    board.setPassword(prompt.inputString("암호? "));
    boardDao.insert(board);
  }
}
