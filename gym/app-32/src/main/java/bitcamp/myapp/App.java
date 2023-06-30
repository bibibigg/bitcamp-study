package bitcamp.myapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    loadMember("member.data2", memberList);
    loadBoard("board.data2", boardList);
  }

  private void saveData() {
    saveMember("member.data2", memberList);
    saveBoard("board.data2", boardList);
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

  private void loadMember(String filename, List<Member> list) {
    try {
      FileInputStream in0 = new FileInputStream(filename);
      BufferedInputStream in1 = new BufferedInputStream(in0);
      ObjectInputStream in = new ObjectInputStream(in1);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        list.add((Member) in.readObject());
      }
      Member.userId = memberList.get(memberList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list) {
    try {
      FileInputStream in0 = new FileInputStream(filename);
      BufferedInputStream in1 = new BufferedInputStream(in0);
      ObjectInputStream in = new ObjectInputStream(in1);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {

        list.add((Board) in.readObject());
      }
      Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember(String filename, List<Member> list) {
    try {
      FileOutputStream out0 = new FileOutputStream(filename);
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      ObjectOutputStream out = new ObjectOutputStream(out1);

      out.writeShort(list.size());

      for (Member member : list) {
        out.writeObject(member);
      }
      out.close();
    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out0 = new FileOutputStream(filename);
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      ObjectOutputStream out = new ObjectOutputStream(out1);

      out.writeShort(list.size());

      for (Board board : list) {
        out.writeObject(board);
      }
      out.close();
    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}
