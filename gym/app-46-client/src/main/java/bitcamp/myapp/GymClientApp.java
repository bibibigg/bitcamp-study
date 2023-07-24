package bitcamp.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import bitcamp.Dao.MySQLBoardDao;
import bitcamp.Dao.MySQLMemberDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
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

public class GymClientApp {

  MemberDao memberDao;
  BoardDao boardDao;
  BoardDao readingDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");


  public GymClientApp(String ip, int port) throws Exception {

    Connection con = DriverManager.getConnection("jdbc:mysql://study:1111@localhost:3306/studydb" // JDBC
    // URL
    );

    this.memberDao = new MySQLMemberDao(con);
    this.boardDao = new MySQLBoardDao(con, 1);
    this.readingDao = new MySQLBoardDao(con, 2);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... bitcamp.myapp.ClientApp 서버주소 포트번호");
      return;
    }

    GymClientApp app = new GymClientApp(args[0], Integer.parseInt(args[1]));
    app.excute();
    app.close();
  }

  static void GymprintTitle() {
    System.out.println("헬스장 회원 정보 등록 시스템");
    System.out.println("----------------------------------");
  }

  public void excute() {
    GymprintTitle();
    mainMenu.execute(prompt);

  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new GymMemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new GymMemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new GymMemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new GymMemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("기간 조회", new GymMemberDateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new GymMemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new GymBoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new GymBoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new GymBoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new GymBoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new GymBoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("운동일지");
    readingMenu.add(new Menu("등록", new GymBoardAddListener(readingDao)));
    readingMenu.add(new Menu("목록", new GymBoardListListener(readingDao)));
    readingMenu.add(new Menu("조회", new GymBoardDetailListener(readingDao)));
    readingMenu.add(new Menu("변경", new GymBoardUpdateListener(readingDao)));
    readingMenu.add(new Menu("삭제", new GymBoardDeleteListener(readingDao)));
    mainMenu.add(readingMenu);

  }
}
