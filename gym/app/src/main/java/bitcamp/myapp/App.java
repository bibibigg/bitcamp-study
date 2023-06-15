package bitcamp.myapp;

import bitcamp.myapp.handler.GymBoardHandler;
import bitcamp.myapp.handler.GymMemberHandler;
import bitcamp.myapp.handler.Handler;
import bitcamp.util.GymPrompt;

public class App {

  public static void main(String[] args) {

    GymPrompt prompt = new GymPrompt();

    Handler memberHandler = new GymMemberHandler(prompt, "회원");
    Handler boardHandler = new GymBoardHandler(prompt, "게시글");

    GymprintTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.execute();
      } else if (menuNo.equals("2")) {
        boardHandler.execute();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원");
    System.out.println("2. 게시글");
    System.out.println("0. 종료");
  }

  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }
}
