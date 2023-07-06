package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardNetworkDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MemberNetworkDao;
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
import bitcamp.myapp.net.RequestEntity;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class GymClientApp {

  Socket socket;
  DataOutputStream out;
  DataInputStream in;

  MemberDao memberDao;
  BoardDao boardDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public GymClientApp(String ip, int port) throws Exception {
    this.socket = new Socket(ip, port);
    this.out = new DataOutputStream(socket.getOutputStream());
    this.in = new DataInputStream(socket.getInputStream());

    this.memberDao = new MemberNetworkDao("member", in, out);
    this.boardDao = new BoardNetworkDao("board", in, out);
    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
    out.close();
    in.close();
    socket.close();
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

    try {
      out.writeUTF(new RequestEntity().command("quit").toJson());
    } catch (Exception e) {
      System.out.println("종료 오류!");
      e.printStackTrace();
    }

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
