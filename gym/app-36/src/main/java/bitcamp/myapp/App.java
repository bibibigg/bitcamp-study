package bitcamp.myapp;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardListDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MemberListDao;
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
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  MemberDao memberDao = new MemberListDao("member.json");
  BoardDao boardDao = new BoardListDao("board.json");

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().excute();
  }

  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }

  public void excute() {
    GymprintTitle();
    mainMenu.execute(prompt);
    prompt.close();

  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new GymMemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new GymMemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new GymMemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new GymMemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("남은 기간", new GymMemberDateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new GymMemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new GymBoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new GymBoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new GymBoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new GymBoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new GymBoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

  }
}
