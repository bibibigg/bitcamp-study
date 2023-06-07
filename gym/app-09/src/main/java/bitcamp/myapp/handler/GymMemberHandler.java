package bitcamp.myapp.handler;

import bitcamp.util.GymPrompt;

public class GymMemberHandler {

  static final int MAX_SIZE = 100;
  static int userId = 1;
  static int length = 0;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] phone_number = new String[MAX_SIZE];
  static String[] age = new String[MAX_SIZE];
  static char[] per = new char[MAX_SIZE];

  static final char ONE_MONTH = '1';
  static final char THREE_MONTH = '3';
  static final char SIX_MONTH = '6';
  static final char TWELVE_MONTH = "12";
  

  public static void InputGymMember() {
    name[length] = GymPrompt.inputString("이름 ");
    age[length] = GymPrompt.inputString("나이 ");
    phone_number[length] = GymPrompt.inputString("핸드폰번호 ");
    
    per_loop: while (true) {
      String perNo = GymPrompt.inputString("등록기간:\n" +
      " 1. 1개월\n" +
      " 2. 3개월\n" +
      " 3. 6개월\n" +
      " 4. 12개월\n" +
      "> ");


      switch(perNo) {
        case "1":
        per[length] = 1;
        break per_loop;
        case "2":
        per[length] = 3;
        break per_loop;
        case "3":
        per[length] = 6;
        break per_loop;
        case "4":
        per[length] = 12;
        break per_loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
    no[length] = userId++;
    length++;
  }

  public static void printGymMember() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
    System.out.printf("%d, %s, %s, %s, %d\n", no[i], name[i], age[i], phone_number[i], per[i]);
  }
}

  public static void viewGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.printf("이름: %s\n", name[i]);
        System.printf("나이: %s\n", age[i]);
        System.printf("핸드폰번호: %s\n", phone_number[i]);
        System.printf("등록개월: %d\n", toperString(per[i]));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toperString(char per) {
    if (per == '1') {
      return = "1개월"
    } else if (per == '2') {
      return = "3개월"
    } else if (per == '3') {
      return = "6개월"
    } else {
      return = "12개월"
    }
  }

  public static void updateGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.printf("이름(%)? ", name[i]);
        name[i] = GymPrompt.inputString("");
        System.printf("나이(%s)? ", age[i]);
        age[i] = GymPrompt.inputString("");
        System.printf("핸드폰번호? ");
        phone_number[i] = GymPrompt.inputString("");
        per[i] = inputPer(per[i]);
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputPer(char per) {
    String label;
    if (per == 0) {
      label = "등록개월?\n";
    } else {
      label = String.format("등록개월(%s)?\n", toperString(per));
    }
    per_loop: while (true) {
      String perNo = GymPrompt.inputString(label +
      " 1. 1개월\n" +
      " 2. 3개월\n" +
      " 3. 6개월\n" +
      " 4. 12개월\n" +
      "> ");

      switch(perNo) {
        case "1":
        return ONE_MONTH;
        case "2":
        return THREE_MONTH;
        case "3":
        return SIX_MONTH;
        case "4":
        return TWELVE_MONTH;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}
