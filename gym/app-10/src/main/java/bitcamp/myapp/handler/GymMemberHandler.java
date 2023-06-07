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
  

  public static void InputGymMember() {
    if(!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    name[length] = GymPrompt.inputString("이름 ");
    age[length] = GymPrompt.inputString("나이 ");
    phone_number[length] = GymPrompt.inputString("핸드폰번호 ");
    per[length] = inputPer((char)0);
    
    no[length] = userId++;
    length++;
  }

  public static void printGymMember() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 핸드폰번호, 등록개월");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
    System.out.printf("%d, %s, %s, %s, %s\n",
     no[i], name[i], age[i], phone_number[i],
      toperString(per[i]));
  }
}

  public static void viewGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("나이: %s\n", age[i]);
        System.out.printf("핸드폰번호: %s\n", phone_number[i]);
        System.out.printf("등록개월: %s\n", toperString(per[i]));
        return;
    }
  }
  System.out.println("해당 번호의 회원이 없습니다!");
}

  public static String toperString(char per) {
    if (per == '1') {
      return "1개월";
    } else if (per == '2') {
      return "3개월";
    } else {
      return "6개월";
    } 
  }

  public static void updateGymMember() {
    String memberNo = GymPrompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", name[i]);
        name[i] = GymPrompt.inputString("");
        System.out.printf("나이(%s)? ", age[i]);
        age[i] = GymPrompt.inputString("");
        System.out.printf("핸드폰번호? ");
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
      no[i] = no[i + 1];
      name[i] = name[i + 1];
      phone_number[i] = phone_number[i + 1];
      age[i] = age[i + 1];
      per[i] = per[i + 1];
    }

    no[length - 1] = 0;
    name[length - 1] = null;
    phone_number[length - 1] = null;
    age[length - 1] = null;
    per[length - 1] = (char) 0;

    length--;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      if (no[i] == memberNo) {
        return i;
      }
    }
    return -1;
  }


  public static boolean available() {
    return length < MAX_SIZE;
  }
}
