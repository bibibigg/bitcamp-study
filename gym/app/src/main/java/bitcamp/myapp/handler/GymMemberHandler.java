package bitcamp.myapp.handler;

import bitcamp.util.GymPrompt;
import bitcamp.myapp.vo.Member;

public class GymMemberHandler {

  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final int ONE_MONTH = 1;
  static final int THREE_MONTH = 3;
  static final int SIX_MONTH = 6;
  

  public static void InputGymMember() {
    if(!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.name = GymPrompt.inputString("이름? ");
    m.age = GymPrompt.inputInt("나이? ");
    m.phone_number = GymPrompt.inputString("핸드폰번호? ");
    m.per = inputPer(0);
    m.no = userId++;
    members[length++] = m;
  }

  public static void printGymMember() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
    System.out.printf("%d, %s, %d, %s, %s\n",
     m.no, m.name, m.age, m.phone_number,
      toperString(m.per));
  }
}

  public static void viewGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", m.name);
        System.out.printf("나이: %d\n", m.age);
        System.out.printf("핸드폰번호: %s\n", m.phone_number);
        System.out.printf("등록개월: %s\n", toperString(m.per));
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

  public static void updateGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s) ", m.name);
        m.name = GymPrompt.inputString("");
        System.out.printf("나이(%d) ", m.age);
        m.age = GymPrompt.inputInt("");
        System.out.printf("핸드폰번호 ");
        m.phone_number = GymPrompt.inputString("");
        m.per = inputPer(m.per);
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static int inputPer(int per) {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)\n", toperString(per));
    }
    per_loop: while (true) {
      String perNo = GymPrompt.inputString(label +
      " 1. 1개월\n" +
      " 2. 3개월\n" +
      " 3. 6개월\n" +
      "> ");

      switch(perNo) {
        case "1":
        return ONE_MONTH;
        case "2":
        return THREE_MONTH;
        case "3":
        return SIX_MONTH;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteGymMember() {
    int memberNo = GymPrompt.inputInt("번호? ");
    int deletedIndex = indexOf(memberNo);

    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length -1; i++) {
      members[i] = members[i + 1];
    }
    members[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.no == memberNo) {
        return i;
      }
    }
    return -1;
  }


  public static boolean available() {
    return length < MAX_SIZE;
  }
}
