package bitcamp.myapp;

import java.util.ArrayList;
import java.util.LinkedList;
import bitcamp.myapp.handler.BoardAddListener;
import bitcamp.myapp.handler.BoardDeleteListener;
import bitcamp.myapp.handler.BoardDetailListener;
import bitcamp.myapp.handler.BoardListListener;
import bitcamp.myapp.handler.BoardUpdateListener;
import bitcamp.myapp.handler.FooterListener;
import bitcamp.myapp.handler.HeaderListener;
import bitcamp.myapp.handler.HelloListener;
import bitcamp.myapp.handler.MemberAddListener;
import bitcamp.myapp.handler.MemberDeleteListener;
import bitcamp.myapp.handler.MemberDetailListener;
import bitcamp.myapp.handler.MemberListListener;
import bitcamp.myapp.handler.MemberUpdateListener;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;


public class App {

  public static void main(String[] args) {

    ArrayList<Member> memberList = new ArrayList<>(); // new ArrayList<>에 Member는 생략가능
    LinkedList<Board> boardList = new LinkedList<>();
    LinkedList<Board> readingList = new LinkedList<>();

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다.
    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    // 모든 핸들러는 Handler 규칙에 따라 정의되었기 때문에
    // Handler 레퍼런스에 그 주소를 담을 수 있다.

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boradMenu = new MenuGroup("게시글");
    boradMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boradMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boradMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boradMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boradMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boradMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingList)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingList)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingList)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingList)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingList)));
    mainMenu.add(readingMenu);


    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }


  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }
}
