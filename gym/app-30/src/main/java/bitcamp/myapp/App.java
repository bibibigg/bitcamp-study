package bitcamp.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import bitcamp.io.BufferedOutputStream;
import bitcamp.io.DataInputStream;
import bitcamp.io.DataOutputStream;
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
      FileInputStream in0 = new FileInputStream("member.data");
      DataInputStream in = new DataInputStream(in0);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Member member = new Member();

        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setAge(in.readInt());
        member.setPhoneNumber(in.readUTF());
        member.setPer(in.readInt());
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
      FileInputStream in0 = new FileInputStream(filename);
      DataInputStream in = new DataInputStream(in0);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setPassword(in.readUTF());
        board.setViewCount(in.readInt());
        board.setCreatedDate(in.readLong());
        list.add(board);
      }
      Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember() {
    try {
      FileOutputStream out0 = new FileOutputStream("member.data");
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      DataOutputStream out = new DataOutputStream(out1);

      out.writeShort(memberList.size());

      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeInt(member.getAge());
        out.writeUTF(member.getPhoneNumber());
        out.writeInt(member.getPer());
      }
      out.close();
    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out0 = new FileOutputStream("member.data");
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      DataOutputStream out = new DataOutputStream(out1);

      out.writeShort(list.size());

      for (Board board : list) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeUTF(board.getPassword());
        out.writeInt(board.getViewCount());
        out.writeLong(board.getCreatedDate());
      }
      out.close();
    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}
