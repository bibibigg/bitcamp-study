package bitcamp.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import bitcamp.myapp.vo.AutoIncrement;
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
    loadJson("member.json", memberList, Member.class);
    loadJson("board.json", boardList, Board.class);
  }

  private void saveData() {
    saveJson("member.json", memberList);
    saveJson("board.json", boardList);
  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new GymMemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new GymMemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new GymMemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new GymMemberUpdateListener(memberList)));
    memberMenu.add(new Menu("기간 조회", new GymMemberDateListener(memberList)));
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

  private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
    try {

      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0);

      StringBuilder strbuilder = new StringBuilder();
      String line = null;

      while ((line = in.readLine()) != null) {
        strbuilder.append(line);
      }

      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
      Collection<T> objects = gson.fromJson(strbuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> info : interfaces) {
        if (info == AutoIncrement.class) {
          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
          autoIncrement.updatekey();
          break;
        }
      }


    } catch (Exception e) {
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveJson(String filename, List<?> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0);

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

      out.write(gson.toJson(list));

      out.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}
