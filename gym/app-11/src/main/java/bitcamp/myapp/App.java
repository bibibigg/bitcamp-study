package bitcamp.myapp;

import bitcamp.myapp.handler.GymMemberHandler;
import bitcamp.util.GymPrompt;

public class App {

    public static void main(String[] args) {

      GymprintTitle();

      printMenu();

      while (true) {
        String menuNo = GymPrompt.inputString("메인> ");
        if (menuNo.equals("6")) {
          break;
        } else if (menuNo.equals("1")) {
          GymMemberHandler.InputGymMember();
        } else if (menuNo.equals("2")) {
          GymMemberHandler.printGymMember();
        } else if (menuNo.equals("3")) {
          GymMemberHandler.viewGymMember();
        } else if (menuNo.equals("4")) {
          GymMemberHandler.updateGymMember();
        } else if (menuNo.equals("5")) {
          GymMemberHandler.deleteGymMember();
        } else {
          System.out.println(menuNo);
        }
    }

    GymPrompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("6. 종료");
    }

    static void GymprintTitle() {
      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");
    }
}
  
  