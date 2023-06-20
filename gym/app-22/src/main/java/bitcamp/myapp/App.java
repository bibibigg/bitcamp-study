package bitcamp.myapp;

import bitcamp.myapp.handler.GymBoardHandler;
import bitcamp.myapp.handler.GymMemberHandler;
import bitcamp.myapp.handler.Handler;
import bitcamp.util.ArrayList;
import bitcamp.util.LinkedList;
import bitcamp.util.MenuPrompt;

public class App {

  public static void main(String[] args) {

    MenuPrompt prompt = new MenuPrompt();

    prompt.appendBreadcrumb("메인", getMenu());

    Handler memberHandler = new GymMemberHandler(prompt, "회원", new ArrayList());
    Handler boardHandler = new GymBoardHandler(prompt, "게시글", new LinkedList());

    GymprintTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          break loop;
        case "1":
          memberHandler.execute();
          break;
        case "2":
          boardHandler.execute();
          break;
      }
    }
    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원\n");
    menu.append("2. 게시글\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }
}
