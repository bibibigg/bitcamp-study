package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface MemberActionListener extends ActionListener {

  static int inputPer(int per, BreadcrumbPrompt prompt) throws IOException {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)\n", per == 1 ? "1개월" : per == 3 ? "3개월" : "6개월");
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
          prompt.println("무효한 번호입니다.");
      }
    }
  }
}
