package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.GymPrompt;

public class GymMemberHandler {

  private static final int MAX_SIZE = 100;
  private GymPrompt prompt;
  private Member[] members = new Member[MAX_SIZE];
  private int length;

  public GymMemberHandler(GymPrompt prompt) {
    this.prompt = prompt;
  }

  public void InputGymMember() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setAge(this.prompt.inputInt("나이? "));
    m.setPhone_number(this.prompt.inputString("핸드폰번호? "));
    m.setPer(inputPer(0));
    members[length++] = m;
  }

  public void printGymMember() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %d, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getPhone_number(), toperString(m.getPer()));
    }
  }

  public void viewGymMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("나이: %d\n", m.getAge());
        System.out.printf("핸드폰번호: %s\n", m.getPhone_number());
        System.out.printf("등록개월: %s\n", toperString(m.getPer()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toperString(int per) {
    if (per == 1) {
      return "1개월";
    } else if (per == 3) {
      return "3개월";
    } else {
      return "6개월";
    }
  }

  public void updateGymMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s) ", m.getName());
        m.setName(this.prompt.inputString(""));
        System.out.printf("나이(%d) ", m.getAge());
        m.setAge(this.prompt.inputInt(""));
        System.out.printf("핸드폰번호 ");
        m.setPhone_number(this.prompt.inputString(""));
        m.setPer(inputPer(m.getPer()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private int inputPer(int per) {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)\n", toperString(per));
    }
    while (true) {
      String perNo = this.prompt.inputString(label + " 1. 1개월\n" + " 2. 3개월\n" + " 3. 6개월\n" + "> ");

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

  public void deleteGymMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    int deletedIndex = indexOf(memberNo);

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      members[i] = members[i + 1];
    }
    members[--length] = null;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  public boolean available() {
    return this.length < MAX_SIZE;
  }
}
