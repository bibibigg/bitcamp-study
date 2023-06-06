package bitcamp.myapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

      Scanner scanner = new Scanner(System.in);

      final int MAX_SIZE = 3;
      int userId = 1;
      int length = 0;

      int[] no = new int[MAX_SIZE];
      String[] name = new String[MAX_SIZE];
      String[] phone_number = new String[MAX_SIZE];
      int[] age = new int[MAX_SIZE];
      int[] per = new int[MAX_SIZE];

      GymprintTitle();

      for (int i = 0; i < MAX_SIZE; i++) {
        InputGymMember(scanner, i, name, age, phone_number, per, no, userId++);
        length++;
        if(!promptContinue(scanner)) {
          break;
        }
    }

    printGymMember(length, no, name, age, phone_number, per);

    scanner.close();
  }

    static void GymprintTitle() {
      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");
    }

    static void InputGymMember(Scanner scanner, int i,
    String[] name, int[] age, String[] phone_number, int[] no, int[] per, int userId) {
      
      System.out.print("이름 ");
      name[i] = scanner.next();

      System.out.print("나이 ");
      age[i] = scanner.nextInt();

      System.out.print("핸드폰 번호 ");
      phone_number[i] = scanner.next();

      per_loop: while (true) {
        System.out.println("등록기간: ");
        System.out.println("  1. 1개월");
        System.out.println("  2. 3개월");
        System.out.println("  3. 6개월");
        System.out.println("  4. 12개월");
        System.out.print("> ");
        String perNo = scanner.next();
        scanner.nextLine();

        switch(perNo) {
          case "1":
          per[i] = 1;
          break per_loop;
          case "2":
          per[i] = 3;
          break per_loop;
          case "3":
          per[i] = 6;
          break per_loop;
          case "4":
          per[i] = 12;
          break per_loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
      no[i] = userId;
    }

    static boolean promptContinue(Scanner scanner) {
      System.out.print("계속 하시겠습니까?(Y/n) ");
      String response = scanner.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }

      static void printGymMember(int length, int[] no, String[] name, int[] age, String[] phone_number, int[] per) {
      System.out.println("---------------------------------------");
      System.out.println("번호, 이름, 나이, 핸드폰번호, 등록기간");
      System.out.println("---------------------------------------");

      for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %d, %s, %d\n", no[i], name[i], age[i], phone_number[i], per[i]);
    }
  }
}
  
  