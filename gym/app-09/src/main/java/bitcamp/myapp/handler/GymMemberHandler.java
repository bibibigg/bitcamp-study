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
  static int[] per = new int[MAX_SIZE];

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

  public static boolean available() {
    return length < MAX_SIZE;
  }
}

