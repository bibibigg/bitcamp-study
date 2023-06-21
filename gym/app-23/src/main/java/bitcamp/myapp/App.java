package bitcamp.myapp;

import bitcamp.myapp.handler.GymBoardAddListener;
import bitcamp.myapp.handler.GymBoardDeleteListener;
import bitcamp.myapp.handler.GymBoardDetailListener;
import bitcamp.myapp.handler.GymBoardListListener;
import bitcamp.myapp.handler.GymBoardUpdateListener;
import bitcamp.myapp.handler.GymMemberAddListener;
import bitcamp.myapp.handler.GymMemberDateListener;
import bitcamp.myapp.handler.GymMemberDeleteListener;
import bitcamp.myapp.handler.GymMemberDetailListener;
import bitcamp.myapp.handler.GymMemberListListener;
import bitcamp.myapp.handler.GymMemberUpdateListener;
import bitcamp.util.ArrayList;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.LinkedList;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  public static void main(String[] args) {

    ArrayList memberList = new ArrayList();
    LinkedList boardList = new LinkedList();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new GymMemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new GymMemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new GymMemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new GymMemberUpdateListener(memberList)));
    memberMenu.add(new Menu("남은 기간", new GymMemberDateListener(memberList)));
    memberMenu.add(new Menu("삭제", new GymMemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new GymBoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new GymBoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new GymBoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new GymBoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new GymBoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    GymprintTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }



  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }
}
