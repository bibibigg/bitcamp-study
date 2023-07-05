package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public abstract class AbstractMemberListener implements ActionListener {

  protected List<Member> list;

  public AbstractMemberListener(List<Member> list) {
    this.list = list;
  }

  protected static String toperString(int per) {
    return (per == 1) ? "1개월" : (per == 3) ? "3개월" : "6개월";
  }


  protected int inputPer(int per, BreadcrumbPrompt prompt) {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)\n", toperString(per));
    }
    while (true) {
      String perNo = prompt.inputString(label + " 1. 1개월\n" + " 2. 3개월\n" + " 3. 6개월\n" + "> ");

      switch (perNo) {
        case "1":
          return Member.ONE_MONTH;
        case "2":
          return Member.THREE_MONTH;
        case "3":
          return Member.SIX_MONTH;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  protected Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }



}
