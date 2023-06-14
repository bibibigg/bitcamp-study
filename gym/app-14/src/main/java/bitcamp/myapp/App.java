package bitcamp.myapp;

import bitcamp.myapp.handler.GymBoardHandler;
import bitcamp.myapp.handler.GymMemberHandler;
import bitcamp.util.GymPrompt;

public class App {

  public static void main(String[] args) {

    GymPrompt prompt = new GymPrompt();

    GymMemberHandler memberHandler = new GymMemberHandler(prompt);
    GymBoardHandler boardHandler = new GymBoardHandler(prompt);

    GymprintTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.InputGymMember();
      } else if (menuNo.equals("2")) {
        memberHandler.printGymMember();
      } else if (menuNo.equals("3")) {
        memberHandler.viewGymMember();
      } else if (menuNo.equals("4")) {
        memberHandler.updateGymMember();
      } else if (menuNo.equals("5")) {
        memberHandler.deleteGymMember();
      } else if (menuNo.equals("6")) {
        boardHandler.inputBoard();
      } else if (menuNo.equals("7")) {
        boardHandler.printBoard();
      } else if (menuNo.equals("8")) {
        boardHandler.viewBoard();
      } else if (menuNo.equals("9")) {
        boardHandler.updateBoard();
      } else if (menuNo.equals("10")) {
        boardHandler.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("6. 게시글등록");
    System.out.println("7. 게시글목록");
    System.out.println("8. 게시글조회");
    System.out.println("9. 게시글변경");
    System.out.println("10. 게시글삭제");
    System.out.println("9. 종료");
  }

  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }
}
