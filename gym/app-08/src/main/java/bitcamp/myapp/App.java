package bitcamp.myapp;

import java.util.Scanner;

public class App {

  static Scanner scanner = new Scanner(System.in);

  static final int MAX_SIZE = 100;
  static int userId = 1;
  static int length = 0;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] phone_number = new String[MAX_SIZE];
  static String[] age = new String[MAX_SIZE];
  static int[] per = new int[MAX_SIZE];

    public static void main(String[] args) {

      GymprintTitle();

      while (length < MAX_SIZE) {
        InputGymMember();
        if(!promptContinue()) {
          break;
        }
    }

    printGymMember();

    scanner.close();
  }

    static void GymprintTitle() {
      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");
    }

    static void InputGymMember() {
      name[length] = Prompt("이름 ");
      age[length] = Prompt("나이 ");
      phone_number[length] = Prompt("핸드폰번호 ");
      
      per_loop: while (true) {
        String perNo = Prompt("등록기간:\n" +
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

    static boolean promptContinue() {
      String response = Prompt("계속 하시겠습니까?(Y/n) ");
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }

      static void printGymMember() {
      System.out.println("---------------------------------------");
      System.out.println("번호, 이름, 나이, 핸드폰번호, 등록기간");
      System.out.println("---------------------------------------");

      for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %s, %d\n", no[i], name[i], age[i], phone_number[i], per[i]);
    }
  }

    static String Prompt(String title) {
      System.out.print(title);
    return scanner.nextLine();
    }
}
  
  