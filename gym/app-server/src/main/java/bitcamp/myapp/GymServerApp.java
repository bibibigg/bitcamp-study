package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bitcamp.dao.MySQLBoardDao;
import bitcamp.dao.MySQLMemberDao;
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
import bitcamp.myapp.handler.LoginListener;
import bitcamp.myapp.net.NetProtocol;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class GymServerApp {

  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  DataSource ds = new DataSource("jdbc:mysql://localhost:3306/studydb", "study", "1111");
  MemberDao memberDao;
  BoardDao boardDao;
  BoardDao readingDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public GymServerApp(int port) throws Exception {
    this.port = port;

    this.memberDao = new MySQLMemberDao(ds);
    this.boardDao = new MySQLBoardDao(ds, 1);
    this.readingDao = new MySQLBoardDao(ds, 2);

    prepareMenu();
  }

  public void close() throws Exception {}

  public static void main(String[] args) throws Exception {

    GymServerApp app = new GymServerApp(8888);
    app.excute();
    app.close();
  }

  public void excute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[Bitcamp Gym]\n" + "-----------------------------------------");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();
    } finally {
      ds.clean();
    }
  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new GymMemberAddListener(memberDao, ds)));
    memberMenu.add(new Menu("목록", new GymMemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new GymMemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new GymMemberUpdateListener(memberDao, ds)));
    memberMenu.add(new Menu("기간 조회", new GymMemberDateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new GymMemberDeleteListener(memberDao, ds)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new GymBoardAddListener(boardDao, ds)));
    boardMenu.add(new Menu("목록", new GymBoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new GymBoardDetailListener(boardDao, ds)));
    boardMenu.add(new Menu("변경", new GymBoardUpdateListener(boardDao, ds)));
    boardMenu.add(new Menu("삭제", new GymBoardDeleteListener(boardDao, ds)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("운동일지");
    readingMenu.add(new Menu("등록", new GymBoardAddListener(readingDao, ds)));
    readingMenu.add(new Menu("목록", new GymBoardListListener(readingDao)));
    readingMenu.add(new Menu("조회", new GymBoardDetailListener(readingDao, ds)));
    readingMenu.add(new Menu("변경", new GymBoardUpdateListener(readingDao, ds)));
    readingMenu.add(new Menu("삭제", new GymBoardDeleteListener(readingDao, ds)));
    mainMenu.add(readingMenu);

  }
}
