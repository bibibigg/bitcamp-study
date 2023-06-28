package bitcamp.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {
  ArrayList<Member> memberList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();

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
    loadData();
    mainMenu.execute(prompt);
    saveData();
    prompt.close();

  }

  private void loadData() {
    loadMember();
    loadBoard("board.data", boardList);
  }

  private void saveData() {
    saveMember();
    saveBoard("board.data", boardList);
  }

  private void prepareMenu() {

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

  }

  private void loadMember() {
    try {
      FileInputStream in = new FileInputStream("member.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setName(new String(buf, 0, length, "UTF-8"));

        member.setAge(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setPhoneNumber(new String(buf, 0, length, "UTF-8"));

        member.setPer(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        memberList.add(member);
      }

      Member.userId = memberList.get(memberList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list) {
    try {
      FileInputStream in = new FileInputStream(filename);
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setTitle(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setContent(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setWriter(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setPassword(new String(buf, 0, length, "UTF-8"));

        board.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        board.setCreatedDate((long) in.read() << 54 | (long) in.read() << 48
            | (long) in.read() << 40 | (long) in.read() << 32 | (long) in.read() << 24
            | (long) in.read() << 16 | (long) in.read() << 8 | in.read());

        boardList.add(board);
      }

      Board.boardNo = Math.max(Board.boardNo, boardList.get(boardList.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println("게시글 정보를 읽는 중 오류 발생!");
    }
  }

  private void saveMember() {
    try {
      FileOutputStream out = new FileOutputStream("member.data");

      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member member : memberList) {
        int no = member.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = member.getName().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int Age = member.getAge();
        out.write(Age >> 24);
        out.write(Age >> 16);
        out.write(Age >> 8);
        out.write(Age);

        bytes = member.getPhoneNumber().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int Per = member.getPer();
        out.write(Per >> 24);
        out.write(Per >> 16);
        out.write(Per >> 8);
        out.write(Per);
      }
      out.close();
    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out = new FileOutputStream(filename);

      int size = boardList.size();
      out.write(size >> 8);
      out.write(size);

      for (Board board : boardList) {
        int no = board.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = board.getTitle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getContent().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getWriter().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int ViewCount = board.getViewCount();
        out.write(ViewCount >> 24);
        out.write(ViewCount >> 16);
        out.write(ViewCount >> 8);
        out.write(ViewCount);

        long CreatedDate = board.getCreatedDate();
        out.write((int) CreatedDate >> 56);
        out.write((int) CreatedDate >> 48);
        out.write((int) CreatedDate >> 40);
        out.write((int) CreatedDate >> 32);
        out.write((int) CreatedDate >> 24);
        out.write((int) CreatedDate >> 16);
        out.write((int) CreatedDate >> 8);
        out.write((int) CreatedDate);
      }
      out.close();
    } catch (Exception e) {
      System.out.println("게시글 정보를 저장하는 중 오류 발생!");
    }
  }
}
