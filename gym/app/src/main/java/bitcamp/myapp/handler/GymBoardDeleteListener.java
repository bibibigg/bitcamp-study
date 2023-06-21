package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class GymBoardDeleteListener extends AbstractBoardListener {


  public GymBoardDeleteListener(List list) {
    super(list);
  }

  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Board(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}
