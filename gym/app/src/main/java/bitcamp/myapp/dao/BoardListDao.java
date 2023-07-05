package bitcamp.myapp.dao;

import java.util.LinkedList;
import java.util.List;
import bitcamp.myapp.vo.Board;
import bitcamp.util.JsonDataHelper;

public class BoardListDao implements BoardDao {

  String filename;
  LinkedList<Board> list = new LinkedList<>();

  public BoardListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Board.class);
  }

  @Override
  public List<Board> list() {
    return this.list;
  }

  @Override
  public void insert(Board board) {
    board.setNo(Board.boardNo++);
    this.list.add(board);
    board.setCreatedDate(System.currentTimeMillis());
    JsonDataHelper.saveJson(filename, list);
  }

}
