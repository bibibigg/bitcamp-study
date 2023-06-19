package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.GymPrompt;
import bitcamp.util.List;

public class GymMemberHandler implements Handler {

  private List list;
  private GymPrompt prompt;
  private String title;

  public GymMemberHandler(GymPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.InputGymMember();
      } else if (menuNo.equals("2")) {
        this.printGymMember();
      } else if (menuNo.equals("3")) {
        this.viewGymMember();
      } else if (menuNo.equals("4")) {
        this.updateGymMember();
      } else if (menuNo.equals("5")) {
        this.deleteGymMember();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

  }

  private static void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 메인");
  }

  private void InputGymMember() {
    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setAge(this.prompt.inputInt("나이? "));
    m.setPhoneNumber(this.prompt.inputString("핸드폰번호? "));
    m.setPer(inputPer(0));

    this.list.add(m);
  }

  private void printGymMember() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");


    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      System.out.printf("%d, %s, %d, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getPhoneNumber(), toperString(m.getPer()));
    }
  }



  private void viewGymMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("나이: %d\n", m.getAge());
    System.out.printf("핸드폰번호: %s\n", m.getPhoneNumber());
    System.out.printf("등록개월: %s\n", toperString(m.getPer()));
  }

  private static String toperString(int per) {
    if (per == 1) {
      return "1개월";
    } else if (per == 3) {
      return "3개월";
    } else {
      return "6개월";
    }
  }

  private void updateGymMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(this.prompt.inputString("이름(%s) ", m.getName()));
    m.setAge(this.prompt.inputInt("나이(%d) ", m.getAge()));
    m.setPhoneNumber(this.prompt.inputString("핸드폰번호 ", m.getPhoneNumber()));
    m.setPer(inputPer(m.getPer()));
  }

  private int inputPer(int per) {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)\n", toperString(per));
    }
    while (true) {
      String perNo =
          this.prompt.inputString(label + " 1. 1개월\n" + " 2. 3개월\n" + " 3. 6개월\n" + "> ");

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

  private void deleteGymMember() {
    if (!this.list.remove(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

  private Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

}
