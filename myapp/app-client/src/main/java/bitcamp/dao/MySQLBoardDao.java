package bitcamp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

public class MySQLBoardDao implements BoardDao {

  Connection con;

  public MySQLBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Board> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT board_no, title, content, writer, view_count, UNIX_TIMESTAMP(create_date) AS create_date FROM myapp_board ORDER BY title ASC")) {

      List<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_no"));
        b.setTitle(rs.getString("title"));
        b.setWriter(rs.getString("writer"));
        b.setViewCount(rs.getInt("view_count"));
        b.setCreatedDate(rs.getLong("create_date"));


        list.add(b);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update(Board board) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) {
    // TODO Auto-generated method stub
    return 0;
  }

}
